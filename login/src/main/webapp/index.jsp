<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login index Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Including the bootstrap CDN -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
body{
background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3oTA5iFZZVMpRLPe3lcHNxSvoHABSUhHX9vPWkGlpSSyoRqdVGSCjg5-3YuXjs8Hwp-Q&usqp=CAU");
background-repeat:no-repeat;
width: 100%;
height: 100%;
background-size: 100%;
}
.foot {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #1b1b1b;
	color: white;
}
</style>
</head>

<body style="background-color: #7e9a9a;">
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container">
			<div class="navabar-brand">
				<img src="https://raw.githubusercontent.com/X-workzDev01/xworkzwebsite/master/src/main/webapp/assets/images/Logo.png" width="100" height="50">
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">

					<li class="nav-item-active"><a href="SignUp.jsp"
						class="nav-link" style="color: white;">Sign up</a></li>

					<li class="nav-item-active"><a href="Signin.jsp"
						class="nav-link" style="color: white;">Sign in</a></li>
				</ul>

			</div>

		</div>

	</nav>
	<br></br>
	<h6 style="text-align: center; color: Yellow" >${message}</h6>
	<br>

	<div class="main">
		<h2 style="text-align: center;">Description of Project</h2>

		<h6 style="text-align: center;">Display Page</h6>
		<h6 style="text-align: center;">SingUp</h6>
		<h6 style="text-align: center;">Sing in</h6>
		<h6 style="text-align: center;">Password</h6>
		<h6 style="text-align: center;">Forgot Password</h6>
		<h6 style="text-align: center;">Reset Password</h6>
	</div>
	<div>
		<footer class="page-footer font-small blue">
			<!-- Copyright -->
			<div class="footer-copyright text-center py-3">
				?? 2022 Manohar: <a href="index.jsp">Home</a>
			</div>
			<!-- Copyright -->
		</footer>
	</div>
</body>
</html>