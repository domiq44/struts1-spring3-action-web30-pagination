package org.sid.service;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICatalogueService {
	public void addProduit(Produit p);
	public Page<Produit> listProduits(Pageable pageable);
	public Produit getProduit(String ref);
	public void deleteProduit(String ref);
	public void updateProduit(Produit p);
}
