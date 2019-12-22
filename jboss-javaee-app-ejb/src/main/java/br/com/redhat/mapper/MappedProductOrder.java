package br.com.redhat.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.redhat.model.Product;
import br.com.redhat.model.ProducutOrder;

public class MappedProductOrder {
	
	public List<ProducutOrder> map(final Map<Integer, Product> products) {
		
		List<ProducutOrder> arrayPO = new ArrayList<ProducutOrder>();
		for(Map.Entry<Integer, Product> m : products.entrySet()) {
			ProducutOrder po = new ProducutOrder();
			po.setQuantity(m.getKey());
			po.setProduct(m.getValue());
			
			arrayPO.add(po);
		}

		return arrayPO;
	}

}
