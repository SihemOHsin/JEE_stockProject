<%@ page language="java" import="java.util.List, modele.domaine.Article" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Article details</title>
    <style>

        
        th, td {
            text-align: left;
            padding: 8px;
        }
        
    </style>
</head>
<body>
    <h1>Article details</h1>
    
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
      <form action="ArticleController?action=view&id=" method="post">
      <%
        Article article = (Article) request.getAttribute("article");
        if (article != null ) {%>
    <table>
        <tr>
            <th>ID</th>
            <td><%= article.getId() %></td>
            </tr>
           <tr> <th>Name</th>
             <td><%= article.getNom() %></td>
               </tr>
             <tr>
            <th>Quantity</th>
             <td><%= article.getQuantite() %></td>
            </tr>
            
   
        </table>
    <% } else { %>
        <p>No article details available</p>
        <a href="article.jsp">Back to list</a>
        
    <% } %>
</body>
</html>
