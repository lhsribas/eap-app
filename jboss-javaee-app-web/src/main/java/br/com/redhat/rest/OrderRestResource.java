package br.com.redhat.rest;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.redhat.model.Order;
import br.com.redhat.service.OrderService;

@Path("/orders")
@RequestScoped
public class OrderRestResource {

	@Inject
	private Logger log;

	@Inject
	OrderService orderService;

	@GET
	@Path("{orderNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order findOrderByOrderNumber(@PathParam("orderNumber") final Long orderNumber) {
		log.log(Level.INFO, "Order Number is :: ".concat(orderNumber.toString()));
		return orderService.findOrderByOderNumber(orderNumber);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> findAllOrders() {
		log.log(Level.INFO, "Find All Orders");
		return orderService.findAllOrders();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void saveOrder(final String body) {
		log.log(Level.INFO, body);

		try {
			ObjectMapper om = new ObjectMapper();
			final Order order = om.readValue(body, Order.class);

			orderService.save(order);
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
