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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"   />
   <!--  <script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script> --> 
    
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

  <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
        
        
            <div id="login-row" class="row justify-content-center align-items-center">
            
            
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                    
                    
                    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
                        <form id="login-form" class="form" action="Connexion" method="post">
                            <h3 class="text-center text-info">Login  
                            <i class="bi bi-person"></i>
                           
                           <!-- <img class="user" src="https://i.ibb.co/yVGxFPR/2.png" height="80px" width="80px"> -->
                           </h3>
                            
                            
<div class="form-group">

  <label for="username" class="text-info">Login:</label><br>
  
<input type="text" name="login" id="username" placeholder="enter your login"  class="form-control">
                            </div>
                            <span  style="color:red;" class="erreur">${form.erreurs['login']}</span>
                            <div class="form-group">
                           
                                <label for="password" class="text-info">Password:</label> <br>
                                <input type="text" name="password" id="password" placeholder="enter your password"class="form-control">
                            </div>
                             <span style="color:red;" class="erreur">${form.erreurs['password']}</span>
                              <label for="prenom" class="text-info"></label>
                              <div class="form-group">
                             <input type="submit" name="submit" class="btn btn-info btn-md" value="connexion">
                             </div>
                             
                             <p  style="color:red;" class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                             <div>
                                Vous n'avez pas de compte veillez-vous inscrire ici !!   <a href="<c:url value="/Inscription"/>"  >s'inscrire  </a>
                               </div>
                                <%--  <c:if test="${!empty sessionScope.sessionUser}">
			                    Si l'utilisateur existe en session, alors on affiche son adresse email.
			                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.login}</p>
			             </c:if> --%>
                             </form>
                             
                             
                   
                             
                             <%-- <p> <c:out value="${sessionScope.sessionUser.login }"/></p> --%>
                             <div>
	      <p style="color: red; font-size:30px;"> ${ erreurMsg } </p>
	    </div>
	  
	    
                    </div>
                </div>
                
            </div>
        </div>
    </div>
       

      
                 
</body>
</html>