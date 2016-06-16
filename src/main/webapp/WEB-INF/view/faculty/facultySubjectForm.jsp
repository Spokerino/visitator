<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span >
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/subjects" />" style="font-size: 17; color: #E6E680">
						Subjects
					</a>
				</span >
			</span>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${facultyName}
				</a>
			</span><br>Add a Subject
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<form method="post" action="">
		<table style="text-align: left; width: 80%; padding: 10px">
			<tr>
				<th width="24%">Subjects to add: </th>
				<td>
					<select name="subjectToAdd" multiple="multiple">
						<c:forEach items="${subjectsToAdd}" var="subject">
							<option value="${subject.id}">${subject.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th width="17%">Subjects to remove: </th>
				<td>
					<select name="subjectToRemove" multiple="multiple">
						<c:forEach items="${subjectsToRemove}" var="subject">
							<option value="${subject.id}">${subject.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>

	   </table><br>
		<input type="submit" value="Accept changes" style="position: relative; left: 20px">
	</form>
	<%@include file='../footer.jsp' %>
</body>