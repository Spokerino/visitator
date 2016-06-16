<%@ include file="../head.jsp"%>

<body>
	<header style="height: 115">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span >
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/students" />" style="font-size: 17; color: #E6E680">
						Students
					</a>
				</span >
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
					Group: ${group.name}
				</a>
			</span><br>
			<span id="itemHeader" style="padding-left: 0%">Student Editor</span>
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
			<tr>
				<th>Current Faculty: </th>
				<td width="30%"><input type="text" value="${student.group.specialization.name}" readonly="readonly"></td>
				<th style="width: 25%"><span style="color: blue">Move student to another Faculty</span></th>
				<td>
					<select name="faculties" id="fclty">
						<option value="0" selected="selected">Select a faculty</option>
						<c:forEach items="${faculties}" var="faculty">
							<option value="${faculty.id}" >${faculty.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Current Group: </th>
				<td width="30%"><input type="text" value="${student.group.name}" readonly="readonly"></td>
				<th style="width: 30%"><span style="color: blue">Move student to another Group</span></th>
				<td>
					<select name="groups" id="groups">
						<option value="">Select a group</option>
					</select>
				</td>
			</tr>
		</table><br>

		<span style="position: relative; left: 20px">
			Delete this student: <input type="checkbox" id="checkStudent" name="check" style="margin-right: 20px;">
			<input type="submit" value="Accept changes" >
		</span>
	</sf:form><br><br>
	 <h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	<%@include file='../footer.jsp' %>
</body>