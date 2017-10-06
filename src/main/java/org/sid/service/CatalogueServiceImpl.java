package org.sid.service;

import org.sid.dao.ICatalogueDao;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements ICatalogueService {

	@Autowired
	private ICatalogueDao dao;

	@Override
	public void addProduit(Produit p) {
		dao.addProduit(p);
	}

	@Override
	public Page<Produit> listProduits(Pageable pageable) {
		return dao.listProduits(pageable);
	}

	@Override
	public Produit getProduit(String ref) {
		return dao.getProduit(ref);
	}

	@Override
	public void deleteProduit(String ref) {
		dao.deleteProduit(ref);
	}

	@Override
	public void updateProduit(Produit p) {
		dao.updateProduit(p);
	}
}
