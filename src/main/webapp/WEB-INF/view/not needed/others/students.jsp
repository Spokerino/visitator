<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<head>
	<meta charset="utf-8" />
	<title>Visitator</title>
	<link rel="stylesheet" href="<c:url value="/resources/visitator.css" />" type="text/css">

</head>
<body>
<div class="wrapper">
		<header id="wellcome">	
		
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span style="color: #E6E680; font-size: 18">College: ${college.name}</span><br>
			<span style="font-size: 18">Students</span><br>
			
			</h1>
			
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
			<th style="width:15%">Name</th>
			<th style="width:15%">Faculty</th>
			<th style="width:15%">Group</th>
			<th style="width:5%">Course</th>
			<th style="width:5%">Date of birth</th>
			<th style="width:5%">Gender</th>
		</tr>
		
		<c:forEach items="${students}" var="student">
			<c:set var="studentFacultyId" value="0" />
			<tr>
				<td>
<%-- 					<a id="link" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}"> --%>
						${student.fullName}
<!-- 					</a> -->
				</td>
	
				<td>
					<c:forEach items="${college.specializations}" var="faculty">
						<c:forEach items="${faculty.groups}" var="group">
							<c:if test="${student.group.id == group.id}">
								<a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}" style="text-decoration: none">
									${faculty.name}
								</a>
								<c:set var="studentFacultyId" value="${faculty.id}" />
							</c:if>
						</c:forEach>
					</c:forEach>
				</td>
	
				<td>
					<a href="/visitator/colleges/${collegeId}/faculties/${studentFacultyId}/groups/${student.group.id}" 
					style="text-decoration: none">
						${student.group.name}
					</a>	
				</td>
	
				<td>
					${student.group.course}
				</td>
				
				<td>
					${student.birthday}
				</td>
				
				<td>
					${student.gender}
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="push"></div>
	
	</div>
	<br>
	
	<div class="footer">
<!-- 	<footer> -->
	Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
<!-- 	</footer> -->
	</div>
</body>

</html>