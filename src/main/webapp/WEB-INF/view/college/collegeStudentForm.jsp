<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visitator</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/visitator.css"/>">

<script 
	type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.11.3.js"></script>

<c:url var="ajax_groups" value="/ajax/groups" />
<script type="text/javascript">
	$(document).ready(function() {
	
		$('#fclty').change(function() {
			var id = $("select#fclty").val();
			getGroups(id);
						
		})
	})
	
function getGroups(data) {
		
		$.getJSON('${ajax_groups}', {
			facultyId : data,
			ajax : 'true'
			}, function(response) {
	
		var select = $('#groups');
		select.find('option').remove();
		$.each(response, function(index, value) {
		$('<option>').val(value).text(value).appendTo(select);
		})
		
	})
}	
</script>
</head>
<body>
<header style="height: 100">
	<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
	<hr>
		<span >
			<a href="<c:url value="/colleges/${collegeId}/students" />" style="font-size: 17; color: #E6E680">
				Students
			</a>
		</span >
	</span>
	<span>
		<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
				College: ${collegeName}
		</a>
	</span><br>
	Add a student</h1> 
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

<%-- 	<sf:errors path="student.*" /> --%>

	<sf:form method="post" modelAttribute="student">
	<table id="newItem">
		<tr>
			<th>First Name: </th>
			<td><sf:input type="text" path="firstName" /></td>
			<td><sf:errors path="firstName" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Last Name: </th>
			<td><sf:input type="text" path="lastName" /></td>
			<td><sf:errors path="lastName" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Gender: </th> 
			<td>
				<sf:select path="gender">
					<sf:option value="1">Male</sf:option>
					<sf:option value="0">Female</sf:option>
				</sf:select>
			</td>
		</tr>
		<tr>
			<th>Birthday: </th>
			<td><sf:input type="date" path="birthday" /></td>
			<td><sf:errors path="birthday" cssClass="error"/></td>
		</tr>
		<tr>
			<th>Faculty: </th>
			<td>
				<select name="faculties" id="fclty">
					<option value="" selected="selected">Select a faculty</option>
					<c:forEach items="${faculties}" var="faculty">
						<option value="${faculty.id}" >${faculty.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Group: </th> 
			<td>
				<select name="groups" id="groups">
					<option value="">Select a group</option>

				</select>
			</td>
			<td><span id="empty">${emptyGroup}</span></td>
		</tr>
		
		
   </table>
		    
		    <br>
		    <input type="submit" value="Add student" style="position: relative; left: 10px">
	    	
	</sf:form>
	 
	 <footer >Copyright © by me 2015<br>
			 Original idea of design was taken from w3.schools.com
	 </footer>

</body>
</html>