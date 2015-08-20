<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<head>
	<meta charset="utf-8" />
	<title>Visitator</title>
	<link rel="stylesheet" href="<c:url value="/resources/visitator.css" />" type="text/css">

</head>
<body style="height: 84%">
<div class="wrapper">
	<c:set var="facultyName" value="${faculty.name}" scope="session" />

		<header id="wellcome">	
		
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${collegeName}
				</a>
			</span><br>
			<span style="font-size: 18; text-align: left;">Faculty: ${facultyName}</span>
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
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/groups">Groups</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/subjects" >Subjects</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/lessons" >Lessons</a></li>
		  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/teachers">Teachers</a></li>
		   <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/students">Students</a></li>
		   <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/edit">Edit faculty</a></li>
		</ul> 
	</nav><br>
	
	
	<div id="contentTable">
	<table id="lw97">
	<caption style="font-size:18"><b>Groups</b></caption>
		<tr>
			<th style="width:25%">Name</th>
			<th style="width:25%">Course</th>
			<th style="width:25%">Students</th>
		</tr>
		
		
<%-- 		<c:set var="groupName" value=""/> --%>
		<c:forEach items="${educationGroupList}" var="group">
	
			<tr>
				<td>
					<a id="link" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}">
						${group.name}
					</a>
				</td>
	
				<td>
					${group.course}
				</td>
				
				<td>
					${fn:length(group.students)}
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<div class="push"></div>
	
	</div>
	
	<div class="footer">Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</div>
</body>

</html>