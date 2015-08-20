<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<head>
	<meta charset="utf-8" />
	<title>Visitator</title>
	<link rel="stylesheet" href="<c:url value="/resources/visitator.css" />" type="text/css">

</head>
<body style="height: 82%">
<div class="wrapper">
		<c:set var="groupName" value="${group.name}" scope="session" />

		<header id="wellcome">	
		
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
						<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${group.specialization.institution.name}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${group.specialization.name}
				</a>
			</span><br>
			<span id="groupHeader">Group: ${group.name}</span>
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
    
    
	<nav style="background-color: white">
		<ul id="menu">
		  <li><a href="/visitator/colleges">Colleges</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties">Faculties</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups">Groups</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}/lessons" >Lessons</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}/students" >Students</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}/edit" >Edit group</a></li>	
		</ul> 
	</nav><br>
	
	
	<div id="contentTable">
	<table id="lw97">
	<caption style="font-size:18"><b>Students</b></caption>
		<tr>
			<th style="width:35%">Name</th>
			<th style="width:5%">Course</th>
			<th style="width:25%">Gender</th>
		</tr>
		
		<c:forEach items="${studentList}" var="student">
	
			<tr>
				<td>
				<a id="link" href="/visitator/colleges/${collegeId}/faculties/${student.group.specialization.id}/groups/${student.group.id}/students/${student.id}" 
					style="text-decoration: none">
						${student.fullName}
					</a>
				</td>
	
				<td>
					${student.group.course}
				</td>
				
				<td>
					${student.genderToString}
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
	<div class="push"></div>
	<br>
	</div>
	<br>
	
	<div class="footer">Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</div>
</body>

</html>