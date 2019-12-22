package br.com.redhat.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.redhat.data.UserRepository;
import br.com.redhat.model.User;

@Stateless
public class UserService {

	@Inject
	private UserRepository userRepository;

	public void save(final User user) {
		userRepository.save(user);
	}

	public User findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}
}
