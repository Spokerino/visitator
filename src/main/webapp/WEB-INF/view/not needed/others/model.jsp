<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- 	<sform:errors path="testo.*" /> --%>
	<form method="post" >
			String: <input type="text" name="str" /><br/>
		    Date: <input type="date" name="date" /><br/>
		    Time: <input type="time" name="time" /><br/>
		    Subject:
		    <select name="subjects" >
		    	<c:forEach items="${subjects}" var="subject">		    
					<option value="${subject.name}" >${subject.name}</option>
		    	</c:forEach>
	    	</select><br/>
	    	Teacher:
	    	<select name="teachers">
	    		<c:forEach items="${teachers}" var="teacher">
	    			<option value="${teacher.fullName}">${teacher.fullName}</option>
	    		</c:forEach>
	    	</select><br/>
			
		    
		    

		    	

<!-- 		    DateTime: <input type="datetime-local" name="dateTime" /><br/> -->
<!-- 		    LocalDateTime: <input type="text" name="ldt" /><br/> -->
			<input type="submit" value="Register" />
		</form>
</body>
</html>