<%@page import="com.formation.ecommerce.model.Produit"%>
<%@page import="java.util.List"%>
<jsp:useBean id="produitSoa" scope="request"
	class="com.formation.ecommerce.soa.ProduitSoa" />
<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Nom</th>
			<th>Prix unitaire</th>
			<th>Quntité</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<Produit> produits = produitSoa.getAllProduits();
			for (Produit produit : produits) {
		%>
		<tr>
			<td><%=produit.getId()%></td>
			<td><%=produit.getCode()%></td>
			<td><%=produit.getNom()%></td>
			<td><%=produit.getPrixUnitaire()%></td>
			<td><%=produit.getQuantite()%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>