package org.sid.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogueDaoImpl implements ICatalogueDao {

	private static final Log LOG = LogFactory.getLog(CatalogueDaoImpl.class);

	private Map<String, Produit> produits = new HashMap<String, Produit>();

	@Override
	public void addProduit(Produit p) {
		produits.put(p.getReference(), p);
	}

	@Override
	public List<Produit> listProduits() {
		return new ArrayList<Produit>(produits.values());
	}

	@Override
	public Page<Produit> listProduits(Pageable pageable) {
		List<Produit> list = listProduits();
		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
		Page<Produit> pages = new PageImpl<Produit>(list.subList(start, end), pageable, list.size());
		return pages;
	}

	@Override
	public Produit getProduit(String ref) {
		return produits.get(ref);
	}

	@Override
	public void deleteProduit(String ref) {
		produits.remove(ref);
	}

	@Override
	public void updateProduit(Produit p) {
		produits.put(p.getReference(), p);
	}

	@PostConstruct
	public void initialisation() {
		addProduit(new Produit("ABCD", "ABCD", 9800, 5, true));
		addProduit(new Produit("EFGH", "POUY", 7600, 4, true));
		addProduit(new Produit("IJKL", "SDERK", 3466, 7, true));
		addProduit(new Produit("MNOP", "ABCD", 9800, 5, true));
		addProduit(new Produit("QRST", "POUY", 7600, 4, true));
		addProduit(new Produit("UVWX", "SDERK", 3466, 7, true));
		addProduit(new Produit("YZ23", "ABCD", 9800, 5, true));
		addProduit(new Produit("FTHB", "POUY", 7600, 4, true));
		addProduit(new Produit("ZERT", "SDERK", 3466, 7, true));
		addProduit(new Produit("ZABCD", "ABCD", 9800, 5, true));
		addProduit(new Produit("ZEFGH", "POUY", 7600, 4, true));
		addProduit(new Produit("ZIJKL", "SDERK", 3466, 7, true));
		addProduit(new Produit("ZMNOP", "ABCD", 9800, 5, true));
		addProduit(new Produit("ZQRST", "POUY", 7600, 4, true));
		addProduit(new Produit("ZUVWX", "SDERK", 3466, 7, true));
		addProduit(new Produit("ZYZ23", "ABCD", 9800, 5, true));
		addProduit(new Produit("ZFTHB", "POUY", 7600, 4, true));
		addProduit(new Produit("ZZERT", "SDERK", 3466, 7, true));
		LOG.info("Initialisation des produits");
	}
}
