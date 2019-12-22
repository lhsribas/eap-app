package br.com.redhat.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.redhat.data.ProducutOrderRepository;
import br.com.redhat.model.ProducutOrder;

@Stateless
public class ProductOrderService {
	
	@Inject
	private ProducutOrderRepository pOrderRepository;
	
	public void save(ProducutOrder po) {
		pOrderRepository.save(po);
	}

}
