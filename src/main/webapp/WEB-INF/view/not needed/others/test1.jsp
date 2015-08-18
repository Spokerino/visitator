<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>${greet}</h1><br>
	<form method="post" action="/visitator/test/result">
		<input type="text" name="stringField"><br>
		
		 Subject: <select name="subjects" >
		 				<option value="NONE"> --SELECT--</option>
		     		<c:forEach items="${data}" var="subject">	
		     			<option value="${subject.name}">${subject.name}</option>
		    		</c:forEach>
	    			</select>
		
		<input type="submit" value="OK">
	</form>
</body>
</html>