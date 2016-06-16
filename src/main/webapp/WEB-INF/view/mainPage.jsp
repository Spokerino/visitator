<%@ page session="false" %>
<%@ include file="head.jsp"%>

<body>
	<header id="wellcome">
		<h1>
			<b>Wellcome to <span style="color:#AECFA2">V</span>isitator!</b>

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

	<%@ include file="logout.jsp"%><br>

	<nav>
		<ul id="menu">
		  <li><a href="/visitator/colleges">Colleges</a></li>
		  <li><a href="/visitator/subjects" >Subjects</a></li>
		</ul>
	</nav>
	
	<img src="<c:url value="/resources/university.jpg" />" alt="Students near university"
	 style="width: 75%; height: auto; padding-top: 3%">

	<%@include file='footer.jsp' %>
</body>