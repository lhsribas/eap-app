package br.com.redhat.mapper;

import java.util.Date;

import br.com.redhat.dto.OrderDTO;
import br.com.redhat.model.Customer;
import br.com.redhat.model.Order;

public class MappedOrder {

	public Order map(final OrderDTO orderDTO, final Customer customer) {
		Order order = new Order();

		order.setCreated(new Date(System.currentTimeMillis()));
		order.setUpdated(new Date(System.currentTimeMillis()));
		order.setOrderNumber(orderDTO.getOrderNumber());
		order.setPriority(orderDTO.getPriority());
		
		order.setCustomer(customer);
		
		return order;
	}

}
