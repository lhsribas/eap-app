package br.com.redhat.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.redhat.model.Customer;

@ApplicationScoped
public class CustomerRepository {

	@Inject
	private EntityManager em;

	public Customer findByEmailAndDocument(final String email, final String document) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);

		criteria.select(customer).where(cb.equal(customer.get("document"), document));

		return em.createQuery(criteria).getSingleResult();
	}

	public List<Customer> findAllCustomer() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);

		criteria.select(customer);

		return em.createQuery(criteria).getResultList();
	}

	public void save(Customer customer) {
		em.persist(customer);
	}

	public Customer update(Customer customer) {
		return em.merge(customer);
	}
}
