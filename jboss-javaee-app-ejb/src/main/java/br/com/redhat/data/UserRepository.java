package br.com.redhat.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.redhat.model.User;

@ApplicationScoped
public class UserRepository {

	@Inject
	private EntityManager em;

	public void save(final User user) {
		em.persist(user);
	}

	public User findByEmail(final String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> user = criteria.from(User.class);

		criteria.select(user).where(cb.equal(user.get("email"), email));

		return em.createQuery(criteria).getSingleResult();
	}
}
