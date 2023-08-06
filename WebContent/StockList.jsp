<%@page language="java" import="java.util.List, modele.domaine.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="entete.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Stock List</title>
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


.add-button {
  text-decoration: none;
  color: #333;
  font-weight: bold;
}

.buttons a {
    display: inline-block;
    padding: 5px 10px;
    background-color: #f2f2f2;
    color: #000;
    text-decoration: none;
    margin-right: 10px;
    
}

</style>
</head>
<body>
    <h1>Stock List</h1>

    <div class="buttons">
        
        <a href="addEditArticle.jsp" class="add-button">Add</a>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
        </tr>
        <%
        List<Article> articles = (List<Article>) session.getAttribute("articles");
        if (articles != null) {
            for (Article article : articles) {
        %>
        <tr>
            <td><%= article.getId() %></td>
            <td><%= article.getNom() %></td>
            <td><%= article.getQuantite() %></td>
        </tr>
        <%
            }
        }
        %>
    </table>
</body>
</html>
