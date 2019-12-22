package br.com.redhat.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.redhat.data.OrderRepository;
import br.com.redhat.model.Order;

@Stateless
public class OrderService {

	@Inject
	private OrderRepository orderRepository;

	public void save(Order order) {
		orderRepository.save(order);
	}
	
	public Order saveOrUpdate(Order order) {
		return orderRepository.saveOrUpdate(order);
	}

	public Order findOrderByOderNumber(final Long orderNumber) {
		return orderRepository.findOrderByOderNumber(orderNumber);
	}

	public List<Order> findAllOrders() {
		return orderRepository.findAllOrder();
	}
}
