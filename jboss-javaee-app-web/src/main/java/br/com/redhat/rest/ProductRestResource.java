package br.com.redhat.rest;

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

import br.com.redhat.model.Product;
import br.com.redhat.service.ProductService;

@Path("/products")
@RequestScoped
public class ProductRestResource {
	
	@Inject
	private Logger log;
	
	@Inject
	ProductService productService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> findAllProducts(){
		log.log(Level.INFO, "Find all products :: ");
		return productService.findAllProducts();
	}
	
	@GET
	@Path("{sku}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product findAllProductBySKU(@PathParam("sku") final String sku){
		log.log(Level.INFO, "Find product by SKU :: ");
		return productService.findProductBySKU(sku);
	}
}
