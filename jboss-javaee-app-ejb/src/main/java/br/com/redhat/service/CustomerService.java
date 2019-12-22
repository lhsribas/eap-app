package br.com.redhat.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.redhat.data.CustomerRepository;
import br.com.redhat.model.Customer;

@Stateless
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;

	public Customer findCustomerByEmailAndDocument(final String email, final String document) {
		return customerRepository.findByEmailAndDocument(email, document);
	}

	public List<Customer> findAllCustomers() {
		return customerRepository.findAllCustomer();
	}

	public void save(final Customer customer) {
		customerRepository.save(customer);
	}

	public Customer update(final Customer customer) {
		return customerRepository.update(customer);
	}
}
