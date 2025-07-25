<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html ng-app="EDMSApp">
<!--<![endif]-->
<head>

	<meta charset="utf-8" />
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
	<title>Login Page</title>
 
	<!-- ================== END BASE JS @dev================== -->
<style type="text/css">
html, body { 
  height: 100%; 
  margin: 0; 
  overflow: hidden;
  overflow: clip; 
  contain: content;
}
body {
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url('assets/img/login-bg/aldd.jpg'); 
  
 background-repeat: no-repeat;
 background-size: cover;
 
    
}



.signin {
  background-color:#e3e8e8;						/* #c9c5ab 	#d3d3d3 */
  font-family: 'Montserrat', sans-serif;
  color: ;
  font-size: 14px;
  letter-spacing: 1px;
}

.login {
  position:relative;
  height: 380px;
  width: 405px;
  margin: auto;
  padding: 10px 10px;
  background:hsla(30%, 60%, 70%, 0.3);
  /*  background-image: url('assets/img/login-bg/bg1.jpg'); */  
  
 
 
  background-size: cover;
  box-shadow: 0px 30px 60px -5px #002;
  border-radius: 30px;
/*   background-image: url('assets/img/login-bg/bg-7.jpg');
   position: absolute;  */
   bottom: 85px;
 /*   left: 800px; */
   /*  margin-bottom: 5px; */
   margin-top: 105px;
   
   
}

form {
  padding-top: 50px;
}

/* .active {
  border-bottom: 2px solid #1161ed;
} */

 .nonactive {
  color: rgba(255, 255, 255, 0.2);
  
} 

h2 {
 /*  padding-left: 12px; */
  font-size: 28px;
  color:#d1c5d9;
  
 /*  text-transform: uppercase; */
 /*  padding-bottom: 5px; */
 /*  letter-spacing: 2px; */
  display: inline-block;
  font-weight: 100;
}

h2:first-child {
  padding-left: 0px;
  
}

::placeholder {
  color: #DCD8B6;
  opacity: 1; /* Firefox */
}
::-ms-input-placeholder { /* Edge 12-18 */
  color: red;
}





span {
  text-transform: uppercase;
  font-size: 12px;
  color:#e2dce6;
 /*  opacity: 0.4;  */
  display: inline-block;
  position: relative;
  top: -65px;
  transition: all 0.5s ease-in-out;
}

.text {
  border: none;
  width: 89%;
  padding: 10px 20px;
  display: block;
  height: 15px;
  border-radius: 20px;
  /* background: hsla(0%, 100%, 50%, 0.3); */
  background:rgba(20%, 60%, 20%, 0.3);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  transition: all 0.5s ease-in-out;
  
}

.text:focus {
  outline: 0;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  background: rgba(0, 0, 0, 0);
  
}

.text:focus + span {
  opacity: 0.6;
}

input[type="text"],
input[type="password"] {
  font-family: 'Montserrat', sans-serif;
  color: #fff;
 
}



input {
  display: inline-block;
  padding-top: 20px;
  font-size: 14px;
  
}

h2,
span,
.custom-checkbox {
  margin-left: 20px;
}

.custom-checkbox {
  -webkit-appearance: none;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 8px;
  border-radius: 2px;
  display: inline-block;
  position: relative;
  top: 6px;
}

.custom-checkbox:checked {
  background-color: rgba(17, 97, 237, 1);
}

.custom-checkbox:checked:after {
  content: '\2714';
  font-size: 10px;
  position: absolute;
  top: 1px;
  left: 4px;
  color: #fff;
}

.custom-checkbox:focus {
  outline: none;
}

label {
  display: inline-block;
  padding-top: 10px;
  padding-left: 5px;
}

.signin {
  background-color: #1161ed;
  color: #FFF;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 39px;
  border-radius: 20px;
  margin-top: 30px;
  transition: all 0.5s ease-in-out;
  border: none;
  text-transform: uppercase;
}

.signin:hover {
  background: #4082f5;
  box-shadow: 0px 4px 35px -5px #4082f5;
  cursor: pointer;
}

.signin:focus {
  outline: none;
}



hr {
  border: 1px solid rgba(255, 255, 255, 0.1);
  top: 40px;
  position: relative;
}

a {
  text-align: center;
  display: block;
  top: 90px;
  position: relative;
  text-decoration: none;
  color: rgba(255, 255, 255, 0.2);
}

/* keyboard */
/* body {
  margin: 0;
  height: 100vh;
  background: radial-gradient(circle at top, skyblue, steelblue);
  overflow: hidden;
} */

textarea {
  display: block;
  margin: 1em auto;
  padding: 0.4em;
  width: 90%;
  height: 30%;
  resize: none;
  font-size: 1.2em;
  font-family: system-ui, monospace;
  border-radius: 5px;
}

.keyboard {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  padding: 5px 0;
  background: #004134;
  box-shadow: 0 0 50px rgba(0, 0, 0, 0.5);
  user-select: none;
  transition: bottom 0.4s;
}

.keyboard-hidden {
  bottom: -100%;
}

.keyboard-keys {
  text-align: center;
}

.keyboard-key {
  height: 45px;
  width: 6%;
  max-width: 90px;
  margin: 3px;
  border-radius: 4px;
  border: none;
  background: rgba(255, 255, 255, 0.2);
  color: #fff;
  font-size: 1.05rem;
  outline: none;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  vertical-align: top;
  padding: 0;
  -webkit-tap-highlight-color: transparent;
  position: relative;
}

.keyboard-key:active {
  background: rgba(255, 255, 255, 0.12);
}

.keyboard-wide {
  width: 12%;
}

.keyboard-extrawide {
  width: 36%;
  max-width: 500px;
}

.keyboard-active::after {
  content: "";
  top: 10px;
  right: 10px;
  position: absolute;
  width: 8px;
  height: 8px;
  background: rgba(0, 0, 0, 0.4);
  border-radius: 50%;
}

.keyboard-check::after {
  background: #08ff00;
}

.keyboard-dark {
  background: rgba(0, 0, 0, 0.25);
}
/* keyboard */

</style>
</head>

<body>
<!-- <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'> -->
<div id="page-container" class="fade" ng-controller="loginController">
<div class="login">

<h2 class="active"> <em style="color: black"><b style="color: blue">e</b>-Court Allahabad High Court</em> </h2><br/><br/>
<!-- <h2 class="active"> <em style="color: black">Allahabad High Court</em> </h2> -->
<button align="right" class="fa fa-keyboard-o keyboard-open" style="font-size:28px;color:blue"></button><br/>

  <form>
   <span class="msg_div"></span>
   
   <div class="form-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
   <input type="text" class="text keyboard-input" id="username" name="username" ng-model="loginform.username" required placeholder="User Name/Roll No." />
   
 
   <!-- <input type="text" class="text glyphicon glyphicon-pencil" id="username" name="username" ng-model="loginform.username" required placeholder="User Name" />
    -->  <span style="color: purple"><b>username</b></span>
     </div>
    <br>
   
    <div class="form-group m-b-15" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
   <!-- <input type="password" class="text keyboard-input"  id="password" name="password"  ng-model="loginform.password" required placeholder="Password" /> -->
   <input type="password" class="text keyboard-input"  id="password" name="password"  ng-model="loginform.password" required placeholder="Password" />
     <span style="color: purple"><b>password</b></span>
     </div>
    <br>
    <!-- <input type="checkbox" id="checkbox-1-1" class="custom-checkbox" />
    <label for="checkbox-1-1">Keep me Signed in</label> -->
    
    
     <div class="login-buttons">
    <button class="signin" type="submit" ng-click="login()">Login In </button>
    </div>
   <!--  <hr> -->
    <!--  <a href="#">Forgot Password?</a> -->
     
  </form>
</div>
</div>

<!-- ====================onscreenboard======================= -->
	

<!-- ================== BEGIN BASE JS ================== -->
<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- ================== END BASE JS ================== -->

<script  src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/loginController.js"></script>
<script>
		$(document).ready(function() {
			App.init();
		});
	</script>

</body>
</html>