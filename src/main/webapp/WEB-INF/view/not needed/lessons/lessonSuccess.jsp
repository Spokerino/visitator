<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="">
<title>Lesson</title>
</head>
<body>
<h1>Hi!</h1>
	<c:out value="${lesson.date}" /><br>
	<c:out value="${lesson.start}" /><br>
	<c:out value="${lesson.end}" /><br>
	<c:out value="${lesson.subject.name}" /><br>
<%-- 	<c:out value="${lesson.teacher}" /><br> --%>
<%-- 	${less.start} <br> --%>
<%-- 	${less.end} <br> --%>
	${lesson.teacher.fullName} <br>
</body>
</html>