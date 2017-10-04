<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>Index</title>
</head>
<body>
	<html:form action="produitProcess" method="post">
		<input type="hidden" name="index" />
		<html:submit value="Produits" />
	</html:form>
</body>
</html>
