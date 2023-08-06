<%@ page import="modele.domaine.CommandeAchat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CommandeAchat Details</title>
</head>
<body>
    <h1>CommandeAchat Details</h1>

    <% CommandeAchat commandeAchat = (CommandeAchat) request.getAttribute("commandeAchat");
    if (commandeAchat != null) { %>
    <form action="CommandeAchatController" method="post">
    
        <table>
            <tr>
                <th>ID:</th>
                <td><%= commandeAchat.getId() %></td>
            </tr>
            <tr>
                <th>Nom Fournisseur:</th>
                <td><%= commandeAchat.getNomFournisseur() %></td>
            </tr>
            <tr>
                <th>Date Commande:</th>
                <td><%= commandeAchat.getDateCommande() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(commandeAchat.getDateCommande()) : "" %></td>
            </tr>
        </table>
    <% } else { %>
        <p>No Cmd achat details available</p>
        <a href="commandeAchat.jsp">Back to CommandeAchats</a>
        
    <% } %>
</body>
</html>
