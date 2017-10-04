package org.sid.dao;

import java.util.List;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICatalogueDao {
	public void addProduit(Produit p);
	public List<Produit> listProduits();
	public Page<Produit> listProduits(Pageable pageable);
	public Produit getProduit(String ref);
	public void deleteProduit(String ref);
	public void updateProduit(Produit p);
}
