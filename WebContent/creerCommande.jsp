<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="modele.domaine.CommandeAchat" %>
<%@ page import="modele.domaine.Article" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="entete.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Créer une Commande</title>
</head>
<body>
    <h1>Créer une Commande</h1>

<%-- Error handling section --%>
	<%
		//gestion des erreurs
		ArrayList erreurs = (ArrayList) request.getAttribute("err");
		if (erreurs != null) {
	%>
	<div class="erreur">
		<%
			out.println("<ul>");
				for (int i = 0; i < erreurs.size(); i++) {
					out.println("<li> " + (String) erreurs.get(i) + "</li>");
				}
				out.println("</ul>");
		%>
	</div>
	<%
		}
       int id = 0;
       String nomFournisseur = null;
		Date dateCommande = new Date();
         CommandeAchat p = (CommandeAchat) request.getAttribute("commandeAchat");
		
		if (p != null) {
			id = p.getId();
			nomFournisseur = p.getNomFournisseur();
			dateCommande = p.getDateCommande();
		}
			if (nomFournisseur == null)
			nomFournisseur = "";
		
			if (dateCommande == null)
			dateCommande = new Date();
			
	%>		


    <form action="TraitementCommandeController" method="POST">
        <label for="commandeAchatId">ID de la commande:</label>
        <input type="text" id="commandeAchatId" name="commandeAchatId"><br><br>

        <label for="nomFournisseur">Nom du fournisseur:</label>
        <input type="text" id="nomFournisseur" name="nomFournisseur"><br><br>

        <label for="dateCommande">Date de la commande:</label>
        <input type="date" id="dateCommande" name="dateCommande" value="<%=dateCommande%> placeholder="yyyy-MM-dd"><br><br>

        <h2>Articles:</h2>
        <div id="articlesContainer">
            <div class="articleRow">
                <label for="article1">Article 1:</label>
                <input type="text" id="article1" name="article1"><br>
                <label for="quantite1">Quantité:</label>
                <input type="text" id="quantite1" name="quantite1"><br><br>
            </div>
        </div>

        <button type="button" onclick="addArticle()">Ajouter un article</button><br><br>

        <input type="submit" value="Créer Commande">
    </form>

    <script>
        var articleCount = 1;

        function addArticle() {
            articleCount++;

            var articlesContainer = document.getElementById("articlesContainer");

            var articleRow = document.createElement("div");
            articleRow.classList.add("articleRow");

            var articleLabel = document.createElement("label");
            articleLabel.htmlFor = "article" + articleCount;
            articleLabel.textContent = "Article " + articleCount + ":";

            var articleInput = document.createElement("input");
            articleInput.type = "text";
            articleInput.id = "article" + articleCount;
            articleInput.name = "article" + articleCount;

            var quantiteLabel = document.createElement("label");
            quantiteLabel.htmlFor = "quantite" + articleCount;
            quantiteLabel.textContent = "Quantité:";

            var quantiteInput = document.createElement("input");
            quantiteInput.type = "text";
            quantiteInput.id = "quantite" + articleCount;
            quantiteInput.name = "quantite" + articleCount;

            articleRow.appendChild(articleLabel);
            articleRow.appendChild(articleInput);
            articleRow.appendChild(document.createElement("br"));
            articleRow.appendChild(quantiteLabel);
            articleRow.appendChild(quantiteInput);
            articleRow.appendChild(document.createElement("br"));
            articleRow.appendChild(document.createElement("br"));

            articlesContainer.appendChild(articleRow);
        }
    </script>
</body>
</html>
