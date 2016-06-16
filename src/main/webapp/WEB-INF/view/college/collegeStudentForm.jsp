<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
		<hr>
			<span >
				<a href="<c:url value="/colleges/${collegeId}/students" />" style="font-size: 17; color: #E6E680">
					Students
				</a>
			</span >
		</span>
		<span>
			<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${collegeName}
			</a>
		</span><br>
		Add a student</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" modelAttribute="student">
		<table id="newItem">
			<tr>
				<th>First Name: </th>
				<td><sf:input type="text" path="firstName" /></td>
				<td><sf:errors path="firstName" cssClass="error"/></td>
			</tr>
			<tr>
				<th>Last Name: </th>
				<td><sf:input type="text" path="lastName" /></td>
				<td><sf:errors path="lastName" cssClass="error"/></td>
			</tr>
			<tr>
				<th>Gender: </th>
				<td>
					<sf:select path="gender">
						<sf:option value="1">Male</sf:option>
						<sf:option value="0">Female</sf:option>
					</sf:select>
				</td>
			</tr>
			<tr>
				<th>Birthday: </th>
				<td><sf:input type="date" path="birthday" /></td>
				<td><sf:errors path="birthday" cssClass="error"/></td>
			</tr>
			<tr>
				<th>Faculty: </th>
				<td>
					<select name="faculties" id="fclty">
						<option value="" selected="selected">Select a faculty</option>
						<c:forEach items="${faculties}" var="faculty">
							<option value="${faculty.id}" >${faculty.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Group: </th>
				<td>
					<select name="groups" id="groups">
						<option value="">Select a group</option>

					</select>
				</td>
				<td><span id="empty">${emptyGroup}</span></td>
			</tr>
	   	</table><br>
		<input type="submit" value="Add student" style="position: relative; left: 10px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>