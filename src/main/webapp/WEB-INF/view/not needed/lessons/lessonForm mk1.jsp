<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form method="post">
		<input type="date" name="start">
		<input type="time"  name="end"><br>
		
		<select name="subjects">
			<c:forEach items="${subjectList}" var="subject">
				<option value="${subject.name}">${subject.name}</option>
			</c:forEach>
		</select><br/>
		
		<select name="teachers">
	    	<c:forEach items="${teacherList}" var="teacher">
	    		<option value="${teacher.fullName}">${teacher.fullName}</option>
	    	</c:forEach>
	    </select><br/>
		
		<input type="submit" value="Create">
	</form>


</body>
</html>