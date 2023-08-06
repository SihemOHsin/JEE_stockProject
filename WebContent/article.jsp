<%@ page language="java" import="java.util.List, modele.domaine.Article" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Article List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        
        th, td {
            text-align: left;
            padding: 8px;
        }
        
        th {
            background-color: #f2f2f2;
        }
        
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        
        .success {
            color: green;
        }
        
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Article List</h1>
    
    <% String successMessage = (String) request.getAttribute("successMessage");
       if (successMessage != null && !successMessage.isEmpty()) {
    %>
    <div class="success">
        <%= successMessage %>
    </div>
    <% } %>
    
    <%-- Display error messages if available --%>
    <% List<String> errors = (List<String>) request.getAttribute("errors");
       if (errors != null && !errors.isEmpty()) {
    %>
    <div class="error">
        <ul>
            <% for (String error : errors) { %>
            <li><%= error %></li>
            <% } %>
        </ul>
    </div>
    <% } %>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        <%
        List<Article> articles = (List<Article>) request.getAttribute("articles");
        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
        %>
        <tr>
            <td><%= article.getId() %></td>
            <td><%= article.getNom() %></td>
            <td><%= article.getQuantite() %></td>
            <td>
                <a href="ArticleController?action=view&id=<%= article.getId() %>">View</a>
                <a href="ArticleController?action=update&id=<%= article.getId() %>">Update</a>
                <a href="ArticleController?action=delete&id=<%= article.getId() %>">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4">No articles found.</td>
        </tr>
        <% } %>
    </table>
    
    <h2>Add Article</h2>
    <form action="ArticleController?action=add" method="post">
        <label for="nom">Name:</label>
        <input type="text" id="nom" name="nom" required><br><br>
        
        <label for="quantite">Quantity:</label>
        <input type="number" id="quantite" name="quantite" required><br><br>
        
        <input type="submit" value="Add">
    </form>
</body>
</html>
