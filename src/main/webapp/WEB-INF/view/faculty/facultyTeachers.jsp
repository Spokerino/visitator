<%@ include file="../head.jsp"%>

<body>
	<header>
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College:
					${collegeName}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty:
					 ${facultyName}
				</a>
			</span><br>
			<span id="itemHeader">Teachers</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<form style="float:right; padding-right: 30px" action="teachers/new">
		<input type="submit" value="Add a new teacher">
	</form>
	
	<table id="lw97">
		<tr>
			<th style="width:35%">Name</th>
			<th style="width:25%">Subject</th>
			<th style="width:25%">e-mail</th>
			<th style="width:8%">Birthday</th>
			<th style="width:7%">Gender</th>
		</tr>
		<c:forEach items="${teacherList}" var="teacher">
			<tr>
				<td>
					<a id="link" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/teachers/${teacher.id}/edit"
						style="text-decoration: none">
						${teacher.fullName}
					</a>
				</td>
				<td>
					<c:forEach items="${teacher.subjects}" var="subject">
						${subject.name}<br>
					</c:forEach>
				</td>
				<td>${teacher.email}</td>
				<td>${teacher.birthday}</td>
				<td>${teacher.genderToString}</td>
			</tr>
		</c:forEach>
	</table>
	<%@include file='../footer.jsp' %>
</body>