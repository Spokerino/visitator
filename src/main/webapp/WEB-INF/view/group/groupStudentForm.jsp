<%@ include file="../head.jsp"%>

<body>
	<header style="height: 13%">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span>
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/students" />" style="font-size: 17; color: #E6E680">
						Students
					</a>
				</span>
			</span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${group.specialization.institution.name}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${group.specialization.name}
				</a>
			</span><br>
			<span>
				<a id="groupHeader" style="padding-left: 0%" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}">
					Group: ${groupName}
				</a>
			</span><br>
			<span id="itemHeader" style="padding-left: 0%">Add a student</span>
		</h1>
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
	   </table><br>
		<input type="submit" value="Add student" style="position: relative; left: 10px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>