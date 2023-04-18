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
<div class="row">
			<div class="col-md-4">
				
			</div>
			<div class="col-md-8">
				<h1>Mon Profil</h1>
				<p>Prenom:<c:out value="${sessionScope.sessionUser.prenom }"/></p>

 
 

<p>Nom : <c:out value="${sessionScope.sessionUser.nom }"/></p>
<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.login}</p>
			</div>
		</div>  

<div class="informations-bar">
<nav>
<ul>


<li class="active">
consulter Profil
</li>
<li><a href="<c:url value="ModifierProfil?id=${ sessionScope.sessionUser.id }"/>">modifier</a></li>
<li>
<a   onclick="return confirm('Êtes vous sûr de supprimer votre compte');"   href="<c:url value="SuppresionUser">  <c:param name="id" value="${ sessionScope.sessionUser.id}" /></c:url>">
SupprimerUser
</a>
</li>
<li><a  onclick="return confirm('Confirmez vous la déconnexion?');" href="<c:url value="/Deconnexion"/>"> deconnexion</a></li>

<li><a href="<c:url value="Profil"/>">Accueil</a></li>
</ul>

</nav>

<div class="profil">
		<img src="https://i.ibb.co/yVGxFPR/2.png"/>
	<p class="name"></p>
	
	</div>
	</div>
	</div>

	<c:forEach var="user" items="${utilisateurs }">
<li><a      href="<c:url value="#?id=${user.id }"/>"     onclick="return alert('nbGagner: <c:out value="${user.nbGagner }"/>  nbPartieJouer: <c:out value="${user.nbJouer }"/>  rang: <c:out value="${user.rang }"/>   ');" >
<c:out value="${user.login}"/>
</a></li>
</c:forEach>


<%-- <a   onclick="return confirm('Êtes vous sûr de supprimer votre compte');"   href="<c:url value="UserHistorique">  <c:param name="id" value="${user.id}" />
</c:url>">
<c:out value="${user.login}"/>
</a>


 --%>


</body>
</html>