package br.com.redhat.soap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jws.WebService;

import br.com.redhat.model.Order;
import br.com.redhat.service.OrderService;

@WebService(endpointInterface = "br.com.redhat.soap.OrderWS")
public class OrderWSImpl implements OrderWS {

	@Inject
	private Logger log;

	@Inject
	OrderService orderService;

	@Override
	public Order findOrderByOrderNumber(final Long orderNumber) {
		log.log(Level.INFO, "Order Number is :: ".concat(orderNumber.toString()));
		return orderService.findOrderByOderNumber(orderNumber);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderService.findAllOrders();
	}

}
