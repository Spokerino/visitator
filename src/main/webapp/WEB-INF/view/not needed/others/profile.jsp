<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Visitatr</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Your Profile</h1>
    <c:out value="${person.username}" /><br/>
    <c:out value="${person.firstName}" /> <c:out value="${person.lastName}" /><br/>
    <c:out value="${person.birthday}" /><br/>
    
<%--     <c:set var="isMale" value="${visitatr.male}" /> --%>
    <c:choose>
    	<c:when test="${person.male == 'true'}">Male</c:when>
    	<c:otherwise>Female</c:otherwise>
    </c:choose>
    
<%--     <c:set var="status" value="${visitatr.status}" /> --%>
    <c:choose>
    	<c:when test="${person.status == '1'}">Student</c:when>
    	<c:otherwise>Teacher</c:otherwise>
    </c:choose>
    <br/>

<%--     <c:out value="${visitatr.male}" /><br/> --%>
<%--     <c:out value="${visitatr.status}" /><br/> --%>
    <c:out value="${person.email}" />
  </body>
</html>
