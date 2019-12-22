package br.com.redhat.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.redhat.model.Product;

@ApplicationScoped
public class ProductRepository {

	@Inject
	private EntityManager em;

	public void save(final Product product) {
		em.persist(product);
	}
	
	public Product update(final Product product) {
		return em.merge(product);
	}

	public Product findProductBySKU(final String sku) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
		Root<Product> product = criteria.from(Product.class);

		criteria.select(product).where(cb.equal(product.get("sku"), sku));

		return em.createQuery(criteria).getSingleResult();
	}

	public List<Product> findAllProduct() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
		Root<Product> product = criteria.from(Product.class);

		criteria.select(product);

		return em.createQuery(criteria).getResultList();
	}
}
