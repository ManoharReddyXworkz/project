<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width-divice-width,initial-scale-1">
<title>Conform Password</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
body {
	background-image:
		url("https://media.istockphoto.com/id/1312766253/photo/businessman-using-mobile-smartphone-on-global-network-connection-on-investor-and-customer.jpg?b=1&s=170667a&w=0&k=20&c=Jfo_WTD3vLnEaZrFC_TpwxyhHFNSV_EdK45eyjq5HU0=");
	background-repeat: no-repeat;
	background-size: 100%;
	font-family: 'Roboto', sans-serif;
}

.main {
	width: 350px;
	height: 430px;
	margin: auto;
	background-color: white;
	border-radius: 3px;
}

h4 {
	text-align: center;
	padding-top: 20px;
}

form {
	width: 300px;
	margin-left: 20px;
}

form label {
	display: flex;
	margin-top: 10px;
	font-size: 10x;
}

form input {
	width: 100%;
	padding: 7px;
	border: none;
	border: 1px solid gray;
	border-radius: 6px;
	outline: none;
}

input[type="submit"] {
	width: 320px;
	height: 35px;
	margin-top: 40px;
	border: none;
	background-color: #49c1a2;
	color: white;
	font-size: 18px;
}

p {
	text-align: center;
	padding-top: 20x;
	font-size: 15px;
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

<body>
	<div class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-brand" class="nav-link">
				<img
					src="https://raw.githubusercontent.com/X-workzDev01/xworkzwebsite/master/src/main/webapp/assets/images/Logo.png"
					width="100" height="50">
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li class="nav-item-active"><a href="index.jsp"
						class="nav-link" style="color: white;">Home</a></li>
					<li class="nav-item-active"><a href="Signin.jsp"
						class="nav-link" style="color: white;">Sign in</a></li>
				</ul>
			</div>
		</div>
	</div>
	<h2></h2>
	<h4 style="text-align: center; font-style: oblique;">${message}</h4>
	<div class="main">
		<h4>Conform Password</h4>
		
		${Msg}
		<form action="conform" method="post">
			<br> <label>Email</label> <input type="email" name="userEmail"
				placeholder="Email" required> <label>Password</label> <input
				type="text" name="security" placeholder="Password" required>

			<label>OTP</label> <input type="text" name="otp" placeholder="OTP"
				required> <input type="submit" value="Submit">

			<p>
				Not have an account? <a href="SignUp.jsp">Sign up Here</a>
			<p>
		</form>
	</div>

	<footer class="page-footer font-small blue">
		<!-- Copyright -->
		<div class="footer-copyright text-center py-3">
			© 2022 Manohar: <a href="index.jsp">Home</a>
		</div>
		<!-- Copyright -->
	</footer>
	</div>
</body>
</html>