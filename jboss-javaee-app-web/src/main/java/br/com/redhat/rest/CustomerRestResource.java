package br.com.redhat.rest;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.redhat.model.Customer;
import br.com.redhat.service.CustomerService;

@Path("/customers")
@RequestScoped
public class CustomerRestResource {

	@Inject
	private Logger log;

	@Inject
	CustomerService customerService;

	@GET
	@Path("{email}/{document}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findCustomerByEmailAndDocument(@PathParam("email") final String email,
			@PathParam("document") final String document) {
		log.log(Level.INFO, "User email :: ".concat(email).concat(":: document ::").concat(document));
		return customerService.findCustomerByEmailAndDocument(email, document);
	}

	public List<Customer> findAllCustomers() {
		log.log(Level.INFO, "Find all Users");
		return customerService.findAllCustomers();
	}

	public void saveCustomer(final String body) {
		log.log(Level.INFO, body);

		try {
			ObjectMapper om = new ObjectMapper();
			final Customer customer = om.readValue(body, Customer.class);

			customerService.save(customer);
		} catch (JsonParseException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}
}
