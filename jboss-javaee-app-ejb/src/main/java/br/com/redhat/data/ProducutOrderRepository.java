package br.com.redhat.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.redhat.model.ProducutOrder;

@ApplicationScoped
public class ProducutOrderRepository {

	@Inject
	private EntityManager em;

	public ProducutOrder saveOrUpdate(ProducutOrder producutOrder) {
		return em.merge(producutOrder);
	}

	public void save(ProducutOrder producutOrder) {
		em.persist(producutOrder);
	}
}
