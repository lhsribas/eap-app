package br.com.redhat.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.redhat.model.Order;

@WebService
@SOAPBinding(style = Style.RPC)
public interface OrderWS {

	@WebMethod
	Order findOrderByOrderNumber(final Long orderNumber);
	
	@WebMethod
	List<Order> findAllOrders();

}
