<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- CDN Bootstrap --%>
<link rel="stylesheet" type="text/css" href="./css/stylePage.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
    <%-- fichier style.css --%>
</head>
<body>
<header>
<h3>Jeu Snake </h3>
</header>

<img class="user" src="https://i.ibb.co/yVGxFPR/2.png" height="100px" width="100px">
<h3>
Connexion
</h3>
<div id="divform">
<form method="post" action ="">
<table>
<tr>
<td><input type="email" name="login"  required="required"  value="<c:out value="${utilisateur.login}"/>"  /></td>
</tr>
<tr>
<td>
<input type="text" name="password" required="required" />
</td>
</tr>
</table>
<input type="submit" value="connexion" />

<c:if test="${!empty erreur}">
<p> <c:out value="${erreur }"></c:out> </p>
</c:if>

</form>
</div>

<ul>
<c:forEach var="utilisateur" items="${utilisateurs }">
<li>
<c:out value="${utilisateur.prenom}" />
</li>
</c:forEach>
</ul>
</body>
</html>