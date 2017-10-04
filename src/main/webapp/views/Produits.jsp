<%@ page isELIgnored="false"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<html:html>
<head>
<meta charset="UTF-8" />
<title>Produits</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<html:form action="/produitProcess" method="post">
			<input type="hidden" name="save" />
			<table>
				<tr>
					<td><bean:message key="reference.label" />:</td>
					<td>
						<logic:equal name="editMode" value="false">
							<html:text property="reference" value="${reference}" />
						</logic:equal>
						<logic:equal name="editMode" value="true">
							<html:hidden property="reference" value="${reference}" />
							${reference}
						</logic:equal>
					</td>
					<td><html:errors property="reference" /></td>
				</tr>
				<tr>
					<td><bean:message key="designation.label" />:</td>
					<td><html:text property="designation" value="${designation}" /></td>
					<td><html:errors property="designation" /></td>
				</tr>
				<tr>
					<td><bean:message key="prix.label" />:</td>
					<td><html:text property="prix" value="${prix}" /></td>
					<td><html:errors property="prix" /></td>
				</tr>
				<tr>
					<td><bean:message key="quantite.label" />:</td>
					<td><html:text property="quantite" value="${quantite}" /></td>
					<td><html:errors property="quantite" /></td>
				</tr>
				<tr>
					<td></td>
					<td><html:checkbox property="promo" value="${promo == false}"><bean:message key="promo.label" /></html:checkbox></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<div>
							<html:submit value="Save" />
						</div>
					</td>
				</tr>
			</table>
		</html:form>
	</div>
	<div class="row-fluid">
		<util:pagination thispage="${pages}"></util:pagination>
	</div>
	<div>
		<table class="table1">
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
							<html:form action="produitProcess" method="post">
								<input type="hidden" name="delete" />
								<html:hidden property="ref" value="${p.reference}" />
								<html:submit value="Delete" />
							</html:form>
						</td>
						<td>
							<html:form action="produitProcess" method="post">
								<input type="hidden" name="edit" />
								<html:hidden property="ref" value="${p.reference}" />
								<html:submit value="Edit" />
							</html:form>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
	</div>
</body>
</html:html>