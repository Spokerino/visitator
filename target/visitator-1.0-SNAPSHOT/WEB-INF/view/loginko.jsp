<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<style>
#login-box {
	width: 350px;
	padding: 30px;
	margin: 150px auto;
	background: #fff;
/* 	-webkit-border-radius: 2px; */
/* 	-moz-border-radius: 2px; */
	border: 1px solid #000;
	font-size: 14;	
}
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
 
.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css" />" type="text/css">
</head>
<body>
	<header id="wellcome">	
		
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span style="color: #E6E680">Please Login!</span></h1>
 	</header>
 	
 	<div id="login-box">
 	To apply changes to database login as <span style="color:blue; font-weight: bold;">admin:admin</span>
		<h2>Login with Username and Password</h2>
 
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
 
		<form name="loginForm" action="/visitator/login" method='POST'>
 
		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr><td> </td><td> </td></tr>
			<tr>
				<td ><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>
 
<%-- 		  <input type="hidden" name="${_csrf.parameterName}" --%>
<%-- 			value="${_csrf.token}" /> --%>
 
		</form>
	</div>
	
	<footer>Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</footer>
</body>
</html>