<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Mon Profil</title>
	<link rel="stylesheet" type="text/css" href="./css/styleProfile.css" />
	<!-- Importer les fichiers CSS de Bootstrap -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<!-- Importer les fichiers JavaScript de Bootstrap -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNVQ8" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>


<header>
<h3> <!--  <img alt=""  src="https://thumbs.dreamstime.com/b/serpent-51205237.jpg" height="25px;">-->
Jeu Snake

 <!-- <img alt=""  src="https://thumbs.dreamstime.com/b/serpent-51205237.jpg" height="25px;">-->
 </h3>
</header>
<marquee scrollamount="10" scrolldelay="5">
<b>Bienvenue veillez vous identifier ou vous inscrire !!!  </b>
</marquee>

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<img src="https://via.placeholder.com/150" class="img-fluid rounded-circle">
			</div>
			<div class="col-md-8">
				<h1>Mon Profil</h1>
				<p>Rang: <c:out value="${rank}" /></p>
				<p>Nom: <c:out value="${nom}" /></p>
				<p>Prénom: <c:out value="${prenom}" /></p>
				<p>Login: <c:out value="${login}" /></p>
				<p>Profil: <c:out value="${profil}" /></p>
			</div>
		</div>
		<nav>
			<ul class="nav">
				<li class="nav-item">
					<a class="nav-link active" href="#">Accueil</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Notifications</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Messages</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Paramètres</a>
				</li>
				<li class="nav-item">
					

  <a class="nav-link" href="#">Déconnexion</a>
</ul>
</nav>
</div>

</body>
</html>