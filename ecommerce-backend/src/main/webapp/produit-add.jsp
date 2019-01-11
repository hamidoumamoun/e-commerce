<%@page import="com.formation.ecommerce.model.Produit"%>
<jsp:useBean id="produitSoa" scope="request"
	class="com.formation.ecommerce.soa.ProduitSoa" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>E-Commerce</title>
	</head>
	<body>
		<h2>Ajout d'un produit</h2>
		<hr>
		<% 
			if ("post".equalsIgnoreCase(request.getMethod())) {
				Produit produit = new Produit();
				produit.setCode(request.getParameter("code"));
				produit.setNom(request.getParameter("nom"));
				produit.setPrixUnitaire(Double.parseDouble(request.getParameter("prixUnitaire")));
				produit.setQuantite(Long.parseLong(request.getParameter("quantite")));
				try {
					produitSoa.create(produit);
					response.sendRedirect("index.jsp");
				} catch (Exception e) {
		%>
			<p style="color:red"><%= e.getMessage() %> </p>
		<%
				}
			}
		%>
		<form action="produit-add.jsp" method="POST">
			Code: <input name="code">
			Nom: <input name="nom">
			Prix Unitaire: <input name="prixUnitaire">
			Quantité: <input name="quantite">
			<button type="submit">Ajouter</button>
		</form>
	</body>
</html>