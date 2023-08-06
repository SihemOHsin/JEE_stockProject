<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="modele.domaine.CommandeAchat" %>
<%@ page import="modele.domaine.Article" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des Commandes d'Achat</title>
</head>
<body>
    <h1>Liste des Commandes d'Achat</h1>

    <%@include file="entete.jsp" %>

    <div>
        <form action="CommandeAchatController" method="GET">
            <label for="searchId">Rechercher par ID:</label>
            <input type="text" id="searchId" name="commandeAchatId">
            <button type="submit">Rechercher</button>
        </form>
    </div>

    <div>
        <form action="creerCommande.jsp" >
            <button type="submit">Créer une nouvelle commande</button>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom Fournisseur</th>
                <th>Date Commande</th>
                <th>Articles</th>
                <th>Voir plus</th>
            </tr>
        </thead>
        <tbody>
            <% List<CommandeAchat> commandeAchats = (List<CommandeAchat>) request.getAttribute("commandeAchats");
            if (commandeAchats != null && !commandeAchats.isEmpty()) {
                for (CommandeAchat commandeAchat : commandeAchats) { %>
                    <tr>
                        <td><%= commandeAchat.getId() %></td>
                        <td><%= commandeAchat.getNomFournisseur() %></td>
                        <td><%= commandeAchat.getDateCommande() %></td>
                        <td>
                            <% List<Article> articlesList = commandeAchat.getArticles();
                            if (articlesList != null && !articlesList.isEmpty()) {
                                for (Article article : articlesList) { %>
                                    <%= article.getNom() %><br>
                                <% }
                            } else { %>
                                Aucun article disponible.
                            <% } %>
                        </td>
                        <td>
                            <a href="CommandeAchatController?commandeAchatId=<%= commandeAchat.getId() %>">Details de commande</a><br>
                        </td>
                    </tr>
                <% }
            } else { %>
                <tr>
                    <td colspan="5">Aucune commande d'achat disponible....</td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
