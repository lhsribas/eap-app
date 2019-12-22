package br.com.redhat.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.redhat.data.ProductRepository;
import br.com.redhat.model.Product;

@Stateless
public class ProductService {

	@Inject
	private ProductRepository productRepository;
	
	public Product findProductBySKU(final String sku) {
		return productRepository.findProductBySKU(sku);
	}

	public List<Product> findAllProducts() {
		return productRepository.findAllProduct();
	}

	public void save(final Product product) {
		productRepository.save(product);
	}
	
	public Product update(final Product product) {
		return productRepository.update(product);
	}
	
}
