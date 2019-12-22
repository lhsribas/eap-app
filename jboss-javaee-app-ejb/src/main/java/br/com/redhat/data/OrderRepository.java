package br.com.redhat.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.redhat.model.Order;

@ApplicationScoped
public class OrderRepository {

	@Inject
	private EntityManager em;

	public void save(final Order order) {
		em.persist(order);
	}
	
	public Order saveOrUpdate(final Order order) {
		return em.merge(order);
	}

	public Order findOrderByOderNumber(final Long orderNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
		Root<Order> order = criteria.from(Order.class);

		criteria.select(order).where(cb.equal(order.get("orderNumber"), orderNumber));

		return em.createQuery(criteria).getSingleResult();
	}

	public List<Order> findAllOrder() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
		Root<Order> order = criteria.from(Order.class);

		criteria.select(order);

		return em.createQuery(criteria).getResultList();
	}

}
