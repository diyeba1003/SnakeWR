<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/styleIns.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"   />
   <!--  <script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script> --> 
    <style>
    .erreur{
    color:red;}
    </style>
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

 <h3 class="text-center text-white pt-5">Register form</h3>
        <div class="container">
        
        
            <div id="login-row" class="row justify-content-center align-items-center">
            
            
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                    
                    
                    
                        <form id="login-form" class="form" action="Inscription" method="post">
                           <h3 class="text-center text-info">Register form</h3> 
                            
        <div class="form-group">

  <label for="username" class="text-info">Login:</label><br>
  
<input type="text" name="login" id="username" placeholder="enter your login"  class="form-control">
                            </div>
                            <span  style="color:red;" class="erreur"></span>
                            <div class="form-group">
                           
                                <label for="password" class="text-info">Password:</label> <br>
                                <input type="password" name="password" id="password" placeholder="enter your password"class="form-control">
                            </div>
                             <span style="color:red;" class="erreur"></span>
                              <div class="form-group">
                           
                                <label for="repeat" class="text-info">repeatPass:</label> <br>
                                <input type="password" name="repeat" id="password" value="${utilisateur.nom }"placeholder="enter your password"class="form-control">
                            </div>
                             <span style="color:red;" class="erreur"></span>
                              
                              <div class="form-group">
                           
                                <label for="nom" class="text-info">nom:</label> <br>
                                <input type="text" name="nom" id="password" placeholder="enter your name"class="form-control">
                            </div>
                             <span style="color:red;" class="erreur"></span>
                              
                              <div>
                              <label for="prenom" class="text-info">prenom:</label> <br>
                                <input type="text" name="prenom" id="password" placeholder="enter your name"class="form-control">
                            </div>
                             <span style="color:red;" class="erreur"></span>
                              
                              
                              <label for="prenom" class="text-info"></label>
                              <div class="form-group">
                             <input type="submit" name="submit" class="btn btn-info btn-md" value="connexion">
                             </div>
                             
                             <p  style="color:red;" class="${empty erreur ? 'succes' : 'erreur'}"></p>
                             <div>
                                 veillez-vous connecter ici !!   <a href="<c:url value="/Connexion"/>"  >Se connecter </a>
                               </div>
                                <p> <c:out value="${sessionScope.sessionUser.login }"/></p>
                             <div>
	      <p style="color: red; font-size:30px;"> <c:out value="${ erreur}"/> </p>
	    </div>
	    
                             </form>
                             
                             
                   
                    </div>
                </div>
                
            </div>
            </div>
            </div>
        
        
        


















<!--  <form   class="form-horizontal"  method="post" action="Inscription">
 
               <div class="form-group">
                	<label class="control-label col-sm-2" for="email">Email </label>
                	<div class="col-sm-10">
                		<input class="form-control" type="text" id="email" name="login" value="<c:out value="${utilisateur.emailadress}"/>" size="20" maxlength="60" >
                	</div>
                	<span class="erreur"><c:out value="${form.erreurs['login']}"></c:out></span>
               </div>

				<div class="form-group">
               		<label class="control-label col-sm-2" for="motdepasse">Mot de passe</label>
               		<div class="col-sm-10">
                		<input  class="form-control" type="password" id="motdepasse" name="password" value="" size="20" maxlength="20" />
                	</div>
                	
                <span style="color:red;" class="erreur">${form.erreurs['password']}</span>
                </div>

				<div class="form-group">
               		<label  class="control-label col-sm-2" for="confirmation">Confirmation du mot de passe</label>
               		<div class="col-sm-10">
               	 		<input class="form-control"  type="password" id="confirmation" name="repeat" value="" size="20" maxlength="20" />
                		<span   class="erreur">${form.erreurs['repeat']}</span>
                	</div>
                </div>

				<div class="form-group">
                	<label class="control-label col-sm-2" class="control-label col-sm-2" for="nom">Nom</label>
                	<div class="col-sm-10">
                		<input class="form-control" type="text" id="nom" name="nom" value="<c:out value="${utilisateur.name}"/>" size="20" maxlength="20" />
                		<span class="erreur">${form.erreurs['nom']}</span>
                	</div>
                </div>
                
                <div class="form-group">
                	<label class="control-label col-sm-2" class="control-label col-sm-2" for="nom">Prenom</label>
                	<div class="col-sm-10">
                		<input class="form-control" type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.firstname}"/>" size="20" maxlength="20" />
                		<span class="erreur">${form.erreurs['prenom']}</span>
                	</div>
                </div>
               
               <div class="form-group">        
      				<div class="col-sm-offset-2 col-sm-10">
      	  				 <button type="submit" value="Inscription" class="btn btn-default"> Valider </button>
      				</div>
   			 </div>
   			 
               
             <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
</form>
-->
</body>
</html>