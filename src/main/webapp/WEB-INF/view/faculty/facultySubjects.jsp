<%@ include file="../head.jsp"%>

<body>
	<header>
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${collegeName}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${facultyName}
				</a>
			</span><br>
			<span id="itemHeader">Subjects</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<form style="float:right; padding-right: 30px" action="subjects/new">
		<input type="submit" value="Add/Remove subjects">
	</form>
	
	<table id="lw97">
		<tr>
			<th>Name</th>
		</tr>
			<c:forEach items="${subjectList}" var="subject">
		<tr>
			<td>${subject.name}</td>
		</tr>
		</c:forEach>
	</table>

	<%@include file='../footer.jsp' %>
</body>