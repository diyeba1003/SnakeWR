<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

 <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"   />
</head>
<body>
<header>

</header>




 <div class="main">

        <section class="signup" >
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                
                <div>
	      
	       <p style="color: red; font-size:30px;"> <c:out value="${ erreur}"/> </p>
	       <p style="color: green; font-size:30px;"> <c:out value="${ f}"/> </p>
	    </div>
                
                 <p class="loginhere">
                      retour au profil <a href="Profil" >Profil</a>
                    </p>
                    <form method="POST" action="ModifierProfil" id="signup-form" class="signup-form">
                        <h2 class="form-title">Modifier profil</h2>
                        <div class="form-group">
                            <input type="hidden" name="id"  value="<c:out value="${ sessionScope.sessionUser.id }"/>" class="form-input"/>
                        </div>
                        <div class="form-group">
                            <label for="agree-term" class="label-agree-term"> Nom:</label><input type="text" class="form-input" name="nom"  value="<c:out value="${ sessionScope.sessionUser.nom }"/>" disabled style="background-color:#F0F0F2;" />
                        </div>
                        <div class="form-group">
                             <label for="agree-term" class="label-agree-term"> Prenom:</label><input type="text" class="form-input" name="prenom"  value="<c:out value="${ sessionScope.sessionUser.prenom}"/>" disabled    style="background-color:#F0F0F2;"  />
                        </div>
                        
                        <div class="form-group">
                            <label for="agree-term" class="label-agree-term"> Login:</label><input type="text" class="form-input" name="login"  value="<c:out value="${ sessionScope.sessionUser.login }"/>" />
                        </div>
                        <div class="form-group">
                          <label for="agree-term" class="label-agree-term"> Password:</label> <input type="password" class="form-input" name="password" id="pass" required />
                          <i class="far fa-eye" id="togglePassword" style="margin-left: -30px; cursor: pointer;"></i>
                        </div>
                       
                        <div class="form-group">
                         <label for="agree-term" class="label-agree-term"> RepeatPassword:</label>  <input type="password" class="form-input" id="reppass" name="repeatpass" />
                          <i class="far fa-eye" id="reptogglePassword" style="margin-left: -30px; cursor: pointer;"></i>
                        </div>
                        
                        <div class="form-group">
                            <input type="submit" name="modif" id="submit" class="form-submit" value="valider"/>
                        </div>
                    </form>
                    
                </div>
            </div>
        </section>
    </div>
<script type="text/javascript">

//permet d'afficher le mot de passe

const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#pass');

togglePassword.addEventListener('click', function (e) {
  // toggle the type attribute
  const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
  password.setAttribute('type', type);
  // toggle the eye slash icon
  this.classList.toggle('fa-eye-slash');
});

</script>



</body>
</html>