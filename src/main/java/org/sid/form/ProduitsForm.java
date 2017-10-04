package org.sid.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.sid.entities.Produit;
import org.springframework.data.domain.Page;

public class ProduitsForm extends ActionForm {
	private static final long serialVersionUID = -7676581345118986380L;

	private static final Log LOG = LogFactory.getLog(ProduitsForm.class);

	private String reference;
	private String designation;
	private double prix;
	private int quantite;
	private boolean promo;

	// private List<Produit> produits;
	private Page<Produit> pages;

	private boolean editMode = false;

	public ProduitsForm() {
		LOG.debug("creating new ProduitsForm: " + this);
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isPromo() {
		return promo;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

//	public List<Produit> getProduits() {
//		return produits;
//	}

//	public void setProduits(List<Produit> produits) {
//		this.produits = produits;
//	}

	public Page<Produit> getPages() {
		return pages;
	}

	public void setPages(Page<Produit> pages) {
		this.pages = pages;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Produit getProduit() {
		Produit produit = new Produit();

		produit.setReference(reference);
		produit.setDesignation(designation);
		produit.setPrix(prix);
		produit.setQuantite(quantite);
		produit.setPromo(promo);

		return produit;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if (reference == null || reference.length() == 0) {
			errors.add("reference", new ActionMessage("error.reference.required"));
		} else if (reference.length() < 4 || reference.length() > 10) {
			errors.add("reference", new ActionMessage("error.reference.invalid", 4, 10));
		}

		if (designation == null || designation.length() == 0) {
			errors.add("designation", new ActionMessage("error.designation.required"));
		}

		if (prix < 1.0 || prix > 1000.0) {
			errors.add("prix", new ActionMessage("error.prix.invalid", 1.0, 1000.0));
		}

		if (quantite < 1 || quantite > 500) {
			errors.add("quantite", new ActionMessage("error.quantite.invalid", 1, 500));
		}

		return errors;
	}
}
