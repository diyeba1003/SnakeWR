<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/styleProfile.css" />
<%-- CDN Bootstrap --%>

    <link rel="stylesheet" href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />"/>
    <%-- fichier style.css --%>
</head>
<body>

<div class="container">

<div class="parametre"></div>
<div id="d">
<p>Prenom:<c:out value="${sessionScope.sessionUser.prenom }"/></p><br>
<c:if test="${not empty userGame}">

<p>  Rang: <c:out value="${userGame.rang }"/>
 </p>
 </c:if>
 
 
 <c:if test="${empty userGame}">
        <p>Aucune <c:out value="${userGame.login }"/> n'a été passée par l'utilisateur connecté.</p>
    </c:if>

<p>Nom : <c:out value="${sessionScope.sessionUser.nom }"/></p><br>
<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.login}</p><br>
  </div>  

<div class="informations-bar">
<nav>
<ul>


<li>
<a href="ProfilUser">Consulter Profil</a>
</li>
<li><a href="<c:url value="ModifierProfil?id=${ sessionScope.sessionUser.id }"/>">modifier</a></li>
<li>
<a   onclick="return confirm('Êtes vous sûr de supprimer votre compte');"   href="<c:url value="SuppresionUser">  <c:param name="id" value="${ sessionScope.sessionUser.id}" /></c:url>">
SupprimerUser
</a>
</li>
<li><a  onclick="return confirm('Confirmez vous la déconnexion?');" href="<c:url value="/Deconnexion"/>"> deconnexion</a></li>

<li class="active" >Active</li>
</ul>

</nav>



<div class="profil">
		<img src="https://i.ibb.co/yVGxFPR/2.png"/>
	<p class="name"></p>
	
	</div>
</div>
</div>









</body>
</html>