<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SignUp Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Including the bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
body{
background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxjYlGtpi6g0r-Z60aq3G-MmPmwndTgs9Qhg&usqp=CAU");
background-repeat:no-repeat;
width: 100%;
height: 100%;
background-size: 100%;
</style>

<script type = "text/javascript">
function change_button(checkbx,button_id) {
    var btn = document.getElementById(button_id);
    if (checkbx.checked == true) {
        btn.disabled = "";
    } else {
        btn.disabled = "disabled";
    }
}
</script>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
<div class="container">
<div class= "navabar-brand" class="nav-link" style="text-transform:uppercase;">
<img src="https://raw.githubusercontent.com/X-workzDev01/xworkzwebsite/master/src/main/webapp/assets/images/Logo.png" width="200" height="100">
</div>
<div>
<ul class="nav navbar-nav navbar-right">
<li class = "nav-item-active"><a href="Signin.jsp" class="nav-link">Sign-In</a></li>
<li class = "nav-item-active"><a href="index.jsp" class="nav-link">Home</a></li>
</ul>
</div>
</div>
</nav>
${msg}
${DTO}

<div>
 
<%-- ${mesg}<br> --%>
<h2>Enter User Details Below</h2><br>
<%-- The Enter UserName is:${DTO.userName} --%>
<form action="login" method="post">
<pre>
UserName:<input type="text" name="userName"/><br>
UserEmail:<input type="text" name="userEmail"/><br>
ContactInfo:<input type="text" name="contactInfo"/><br>
<input type="checkbox" id="termsChkbx " onclick="change_button(this,'sub1')"/>
<br>
<input type="submit" name="submit" value="submit" id="sub1" disabled="disabled"/>
</pre>
</form>
</div>
<div>
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