<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>

<h1>hello!</h1><br/>
Data: ${data} <br>
str: ${string}<br>
predmet is : ${subj}

<br><br>

<% 
String s = request.getParameter("subjects");
String str = request.getParameter("stringField");
out.println("yo nigga " + s);
out.println("yo-yo " + str);
%>


<br><br>


<%-- ${test.str} --%>

<%-- StringResult is: <c:out value="${test.str}" /><br> --%>
<%-- DateResult is: <c:out value="${test.date}" /><br> --%>
<%-- LocalDateTimeResult is: <c:out value="${test.time}" /><br> --%>
<%-- or: ${date} --%>
<%-- <% request.getAttribute("")%> --%>
</body>
</html>