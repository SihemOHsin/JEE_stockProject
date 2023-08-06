<%@ page language="java" import="java.util.ArrayList, modele.domaine.Article" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add/Edit Article</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styleAddArticle.css" />
</head>
<body>
    <h1>Add/Edit Article</h1>

    <% 
    ArrayList<String> erreurs = (ArrayList<String>) request.getAttribute("erreurs");
    if (erreurs != null && !erreurs.isEmpty()) {
    %>
    <div class="erreur">
        <ul>
            <% for (String erreur : erreurs) { %>
            <li><%=erreur%></li>
            <% } %>
        </ul>
    </div>
    <% } %>

    <% String successMessage = (String) request.getAttribute("successMessage");
       if (successMessage != null && !successMessage.isEmpty()) {
    %>
    <div class="success">
        <%= successMessage %>
    </div>
    <% } %>
  
    <form action="ArticleController" method="post">
        <label for="nom">Name:</label>
        <input type="text" id="nom" name="nom" value="<%= (request.getAttribute("nom") != null) ? request.getAttribute("nom") : "" %>"
            required><br><br>

        <label for="quantite">Quantity:</label>
        <input type="number" id="quantite" name="quantite" value="<%=request.getAttribute("quantite")%>"
            required><br><br>

        <input type="submit" value="Submit">
    </form>

    <br>

    <a href="ListArticleController">Voir List Article</a>
</body>
</html>
