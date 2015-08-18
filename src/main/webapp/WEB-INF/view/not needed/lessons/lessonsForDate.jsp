<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/visitator.css"/>" type="text/css">
</head>
<body>
	<table>
 		<tr>
 			<th>Date</th>
 			<th>Lesson start</th>
 			<th>Lesson end</th>
 			<th>Subject</th>
 			<th>Teacher</th>
 		</tr>
 		<c:forEach items="${lessonList}" var="lesson" >
 			<tr>
 				<td>${lesson.date}</td>
 				<td>${lesson.startFormat}</td>
 				<td>${lesson.endFormat}</td>
 				<td>${lesson.subject.name}</td>
 				<td>${lesson.teacher.fullName}</td>
 			</tr>
 		</c:forEach>
 	</table>
</body>
</html>