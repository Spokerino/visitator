<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<head>
	<meta charset="utf-8" />
	<title>Visitator</title>
	<link rel="stylesheet" href="<c:url value="/resources/visitator.css" />" type="text/css">
	<style>
		img {
			width: 1020px;
			height: 720px;
			position: absolute;
			padding-top:10px;
		}
	</style>

</head>
<body>
	<header id="wellcome">
			<h1><b>Wellcome to <span style="color:#AECFA2">V</span>isitator!</b>
			 
            <c:if test="${pageContext.request.userPrincipal.name == null}">
			<form method="post" action="/visitator/login">
				<input type="text" name="username" placeholder="Login">
				<input type="password" name="password" placeholder="Password">	
				<input type="submit" name="submit" value="Submit" >
			</form>
			</c:if>
			
			</h1>
			
	</header>
	
<%-- 	<c:if test="${pageContext.request.userPrincipal.name == null}">	 --%>
<!-- 		<div id="reg"> -->
<!-- 			Not registered yet? <span style="padding-left: 53px"><a href="/visitator/register" style="color:#AECFA2">Go to registration</a></span> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
	
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<span id="reg">
		    Currently logged as : <span>${pageContext.request.userPrincipal.name}</span> 
	        <c:url value="/logout" var="logoutUrl" />
	    </span>
	    <form id="reg" action="${logoutUrl} ">
				<input type="submit" name="submit" value="Logout" >
		</form>
	</c:if>
    
    
	<nav>
		<ul id="menu">
		  <li><a href="/visitator/colleges">Colleges</a></li>
		 
<!-- 		  <li><a href="/visitator/faculties">Faculties</a></li> -->
		  <li><a href="/visitator/subjects" >Subjects</a></li>
<!-- 		  <li><a href="/visitator/lessons" >Lessons</a></li> -->
<!-- 		  <li><a href="/visitator/teachers">Teachers</a></li> -->
		</ul> 
	</nav>
	
	 	<img src="<c:url value="/resources/university.jpg" />" alt="Students near university"
	 	 style="width: 75%; height: auto; padding-top: 3%">
	
	
	<footer>Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</footer>
</body>

</html>