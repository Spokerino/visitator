<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- 	<sf:form commandName="test" method="post"> --%>
<%-- 		<sf:input type="date" path="date" cssClass="error" value="2012-02-02"/> --%>
<%-- 		<sf:errors path="date" cssClass="error" /> --%>
<!-- 		<input type="submit"> -->
<%-- 	</sf:form> --%>

<sf:form method="post" modelAttribute="test">
	<sf:input type="date" path="date" /><br>
	<sf:input type="datetime-local" path="str"/>
	<input type="submit">
</sf:form>
</body>
</html>