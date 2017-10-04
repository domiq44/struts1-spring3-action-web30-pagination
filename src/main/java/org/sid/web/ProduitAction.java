package org.sid.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.sid.entities.Produit;
import org.sid.form.ProduitsForm;
import org.sid.service.ICatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ProduitAction extends Action {

	private static final Log LOG = LogFactory.getLog(ProduitAction.class);

	private static final String PAGES = "pages";
	private static final String SUCCESS = "success";

	private Produit produit = new Produit();
	private Page<Produit> pages;
	private boolean editMode = false;

	@Autowired
	private ICatalogueService service;

	public ProduitAction() {
		LOG.debug("creating new ProduitAction: " + this);
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward actionForward = null;

		if (null != request.getParameter("index")) {
			actionForward = index(mapping, form, request, response);
		} else if (null != request.getParameter("save")) {
			actionForward = save(mapping, form, request, response);
		} else if (null != request.getParameter("delete")) {
			actionForward = delete(mapping, form, request, response);
		} else if (null != request.getParameter("edit")) {
			actionForward = edit(mapping, form, request, response);
		} else {
			actionForward = paginate(mapping, form, request, response);
		}

		return actionForward;
	}

	public ActionForward paginate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("paginate");

		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		int page = Integer.parseInt((null == strPage) ? "0" : strPage);
		int size = Integer.parseInt((null == strSize) ? "5" : strSize);
		pages = service.listProduits(new PageRequest(page, size));

		setAttributes(request);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("index");

		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		int page = Integer.parseInt((null == strPage) ? "0" : strPage);
		int size = Integer.parseInt((null == strSize) ? "5" : strSize);
		pages = service.listProduits(new PageRequest(page, size));

		setAttributes(request);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("save");

		produit = ((ProduitsForm) form).getProduit();

		ActionErrors actionErrors = form.validate(mapping, request);
		if (null != actionErrors && !actionErrors.isEmpty()) {
			saveErrors(request, actionErrors);
			setAttributes(request);
			return mapping.getInputForward();
		}

		if (editMode == false) {
			service.updateProduit(produit);
		} else {
			service.addProduit(produit);
			editMode = false;
		}
		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		int page = Integer.parseInt((null == strPage) ? "0" : strPage);
		int size = Integer.parseInt((null == strSize) ? "5" : strSize);
		produit = new Produit();
		pages = service.listProduits(new PageRequest(page, size));

		setAttributes(request);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("delete");

		String ref = request.getParameter("ref");
		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		int page = Integer.parseInt((null == strPage) ? "0" : strPage);
		int size = Integer.parseInt((null == strSize) ? "5" : strSize);
		service.deleteProduit(ref);
		pages = service.listProduits(new PageRequest(page, size));

		setAttributes(request);

		return mapping.findForward(SUCCESS);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("edit");

		editMode = true;
		String ref = request.getParameter("ref");
		String strPage = request.getParameter("page");
		String strSize = request.getParameter("size");
		int page = Integer.parseInt((null == strPage) ? "0" : strPage);
		int size = Integer.parseInt((null == strSize) ? "5" : strSize);
		produit = service.getProduit(ref);
		pages = service.listProduits(new PageRequest(page, size));

		setAttributes(request);

		return mapping.findForward(SUCCESS);
	}

	private void setAttributes(HttpServletRequest request) {
		request.setAttribute("reference", produit.getReference());
		request.setAttribute("designation", produit.getDesignation());
		request.setAttribute("prix", produit.getPrix());
		request.setAttribute("quantite", produit.getQuantite());
		request.setAttribute("promo", (true == produit.isPromo()) ? "true" : "false");

		request.setAttribute("editMode", editMode);

		request.setAttribute(PAGES, pages);
	}
}
