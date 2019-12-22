package br.com.redhat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.redhat.data.CustomerRepository;
import br.com.redhat.data.ProductRepository;
import br.com.redhat.dto.OrderDTO;
import br.com.redhat.dto.ProductDTO;
import br.com.redhat.mapper.MappedOrder;
import br.com.redhat.mapper.MappedProductOrder;
import br.com.redhat.model.Customer;
import br.com.redhat.model.Order;
import br.com.redhat.model.Product;
import br.com.redhat.model.ProducutOrder;



 
@MessageDriven(activationConfig = { 
		@ActivationConfigProperty(propertyName = "clientID", propertyValue = "jboss-queue-order"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "order"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") 
})
@ResourceAdapter("activemq-ra-remote")
public class OrderMessageBean implements MessageListener {

	@Inject
	private Logger log;

	@Resource
	private MessageDrivenContext mdctx;

	@EJB
	private OrderService orderService;
	
	@EJB
	private ProductOrderService pOrderService;

	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private ProductRepository productRepository;
	

	@Override
	public void onMessage(Message message) {
		try {
			
			final BytesMessage byteMessage = ((BytesMessage) message);
			
			byte[] data = new byte[(int) byteMessage.getBodyLength()];
			byteMessage.readBytes(data);
			
		    final String body = new String(data);

			if (body != null && !body.equals("")) {
				log.log(Level.INFO, "Data Consumed :: ".concat(body));

				OrderDTO orderDTO = unmarshall(body.toString());

				Customer customer = customerRepository.findByEmailAndDocument(orderDTO.getCustomer().getEmail(),
						orderDTO.getCustomer().getDocument());

				if (customer == null) {
					throw new RuntimeException("Customer does not existis in the system");
				}

				Map<Integer, Product> mapProduct = new HashMap<>();

				for (ProductDTO pDTO : orderDTO.getProducts()) {
					Product product = new Product();
					product = productRepository.findProductBySKU(pDTO.getSku());

					if (product == null) {
						throw new RuntimeException("Product does not existis in the system");
					}
					
					mapProduct.put(pDTO.getQuantity(), product);
				}

				/*
				 * Mapped the Order with yours objects
				 */
				Order order = new MappedOrder().map(orderDTO, customer);

				/*
				 * Persist order in the system
				 */
				order = orderService.saveOrUpdate(order);
				
				List<ProducutOrder> pOrders = new MappedProductOrder().map(mapProduct);
				
				for(ProducutOrder oo : pOrders) {
					oo.setOrder(order);
					pOrderService.save(oo);
				}
			}
		} catch (Exception ex) {
			mdctx.setRollbackOnly();
		}
	}

	public OrderDTO unmarshall(final String message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(message, OrderDTO.class);
	}

}
