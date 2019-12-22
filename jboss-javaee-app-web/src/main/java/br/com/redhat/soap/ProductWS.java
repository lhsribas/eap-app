package br.com.redhat.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.redhat.model.Product;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ProductWS {

	@WebMethod
	List<Product> findAllProducts();
	
	@WebMethod
	Product findProductBySku(final String sku);
}
