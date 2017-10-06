<%@ page isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<html:html>
<head>
<meta charset="UTF-8" />
<title>Produits</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<div class="panel panel-primary">
			<div class="panel-heading">Add product</div>
			<div class="panel-body">
				<html:form styleClass="form-horizontal" action="/produitProcess" method="post">
					<input type="hidden" name="save" />
					<div class="form-group">
						<label class="col-sm-2 control-label">
							<bean:message key="reference.label" />
						</label>
						<div class="col-sm-10">
							<logic:equal name="editMode" value="false">
								<html:text styleClass="form-control" property="reference" value="${reference}" />
								<html:errors property="reference" />
							</logic:equal>
							<logic:equal name="editMode" value="true">
								<html:hidden styleClass="form-control" property="reference" value="${reference}" />
								<label class="form-control">${reference}</label>
							</logic:equal>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">
							<bean:message key="designation.label" />
						</label>
						<div class="col-sm-10">
							<html:text styleClass="form-control" property="designation" value="${designation}" />
							<html:errors property="designation" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">
							<bean:message key="prix.label" />
						</label>
						<div class="col-sm-10">
							<html:text styleClass="form-control" property="prix" value="${prix}" />
							<html:errors property="prix" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">
							<bean:message key="quantite.label" />
						</label>
						<div class="col-sm-10">
							<html:text styleClass="form-control" property="quantite" value="${quantite}" />
							<html:errors property="quantite" />
						</div>
					</div>
					<div class="form-check">
						<div class="col-sm-offset-2 col-sm-10">
							<label class="form-check-label">
								<html:checkbox property="promo" value="${promo == false}" />
								<bean:message key="promo.label" />
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<html:submit styleClass="btn btn-primary" value="Save" />
						</div>
					</div>
				</html:form>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">List of products</div>
			<div class="panel-body">
				<div class="row-fluid">
					<util:pagination thispage="${pages}"></util:pagination>
				</div>

				<table class="table table-sm table-striped">
					<tr>
						<th><bean:message key="reference.th" /></th>
						<th><bean:message key="designation.th" /></th>
						<th><bean:message key="prix.th" /></th>
						<th><bean:message key="quantite.th" /></th>
						<th><bean:message key="promo.th" /></th>
					</tr>
					<logic:present name="pages" property="content">
						<logic:iterate name="pages" property="content" id="p">
							<tr>
								<td>${p.reference}</td>
								<td>${p.designation}</td>
								<td>${p.prix}</td>
								<td>${p.quantite}</td>
								<td>${p.promo}</td>
								<td>
									<html:form action="/produitProcess" method="post">
										<input type="hidden" name="delete" />
										<html:hidden property="ref" value="${p.reference}" />
										<button class="btn btn-default btn-sm" type="submit" value="Delete">
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>Delete
										</button>
									</html:form>
								</td>
								<td>
									<html:form action="/produitProcess" method="post">
										<input type="hidden" name="edit" />
										<html:hidden property="ref" value="${p.reference}" />
										<button class="btn btn-default btn-sm" type="submit" value="Edit">
											<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>Edit
										</button>
									</html:form>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</table>
			</div>
		</div>
	</div>
</body>
</html:html>
