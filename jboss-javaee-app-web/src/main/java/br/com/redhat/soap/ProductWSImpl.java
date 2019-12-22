package br.com.redhat.soap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jws.WebService;

import br.com.redhat.model.Product;
import br.com.redhat.service.ProductService;

@WebService(endpointInterface = "br.com.redhat.soap.ProductWS")
public class ProductWSImpl implements ProductWS{
	
	@Inject
	private Logger log;
	
	@Inject
	ProductService productService;

	@Override
	public List<Product> findAllProducts() {
		log.log(Level.INFO, "Find All Products");
		return productService.findAllProducts();
	}

	@Override
	public Product findProductBySku(final String sku) {
		return productService.findProductBySKU(sku);
	}

}
