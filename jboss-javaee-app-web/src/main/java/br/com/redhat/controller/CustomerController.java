package br.com.redhat.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.redhat.model.Customer;
import br.com.redhat.service.CustomerService;

//The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
//EL name
//Read more about the @Model stereotype in this FAQ:
//http://www.cdi-spec.org/faq/#accordion6
@Model
public class CustomerController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private CustomerService customerService;

	private Customer newCustomer;
	
	private List<Customer> customers;
	
	@Produces
	@Named
	public List<Customer> getCustomers() {
		return customers;
	}

	@Produces
	@Named
	public Customer getNewCustomer() {
		return newCustomer;
	}

	public void register() throws Exception {
		try {
			customerService.save(newCustomer);
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
			initNewCustomer();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	@PostConstruct
	public void initNewCustomer() {
		newCustomer = new Customer();
		findAllCustomers();
	}
	
	public void findAllCustomers() {
		customers = customerService.findAllCustomers();
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

}
