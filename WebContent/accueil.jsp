<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@include file="entete.jsp" %>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Connexion</title>
<link rel ="stylesheet" type ="text/css"  href="<%=request.getContextPath()%>/css/style.css"/>
</head>
<body>
<div>
<div class="sidebar">
      <ul class="menu">
        <li><a href="Utilisateur.jsp">Gérer les utilisateurs</a></li>
        
        <li><a href="ListArticleController">Liste des articles de stocks</a></li>
        <li><a href="ArticleController">Ajouter/Modifier un article</a></li>
        
        <li><a href="creerCommande.jsp">Traitement des commandes</a></li>
        
        <li><a href="CommandeAchatController">Les Commandes Achat</a></li>
        
        
       
        
        <li><a href="#">Commandes Vente</a></li>
        <li><a href="#">Mouvements de stocks</a></li>
        <li><a href="#">Rapports</a></li>
        <li><a href="#">Paramètres</a></li>
      </ul>
    </div>


</body>
</html>