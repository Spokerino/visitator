<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css"/>" type="text/css">
<style>
	form {
		padding-top: 5px;
		padding-bottom: 0px;
		position: relative;
	}
</style>
</head>
<body>
	
	
	
	<header>
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span> Groups</h1>
	</header>
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<span id="reg">
		    Currently logged as : <span>${pageContext.request.userPrincipal.name}</span> 
	        <c:url value="/logout" var="logoutUrl" />
	    </span>
	    <form id="reg" action="${logoutUrl} ">
				<input type="submit" name="submit" value="Logout" >
		</form>
	</c:if>
	
	<br>
	
	
	<table id="lw97">
		<tr>
			<th style="width:35%">Name and Course</th>

		</tr>
		
		
		
	
			<tr>
					
					<td>
						<form method="post" action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}" >
						
						<select name="gName">
							<c:set var="groupName" value=""/>
							<c:forEach items="${educationGroupList}" var="group">				
								<c:if test="${groupName != group.name}">
									<option value="${group.name}">${group.name}</option>
								</c:if>
							<c:set var="groupName" value="${group.name}"/>
							</c:forEach>
									
						</select>

						<select id="selector" name="course" >
									<c:set var="count" value="0" />
									<c:forEach items="${educationGroupList}" var="group">
										
<%-- 										<c:forEach items="${group.courses}" var="course"> --%>
										
											<c:if test="${count < group.yearsToFinish}">
												<option value="${group.course}">
													${group.course}
												</option>
											</c:if>
											
											<c:set var="count" value="${count + 1}" />
<%-- 										</c:forEach> --%>
									</c:forEach>
							
						</select>
						
						<input type="submit" value="select">
							</form>
					</td>
				
				
				
				
				
			</tr>
		
	</table>
	
	
	<footer>Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</footer>

</body>
</html>