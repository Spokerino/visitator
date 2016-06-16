<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span >
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/teachers" />" style="font-size: 17; color: #E6E680">
						Teachers
					</a>
				</span >
			</span>
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
			<span id="itemHeader" style="padding-left: 0%">Teacher Editor</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" modelAttribute="teacher">
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

				<td width="30%">
	<%-- 				<c:set var="facId" value="0" /> --%>
					<c:forEach items="${faculties}" var="faculty">
						<c:if test="${faculty.id == facultyId}">
							<input type="text" value="${faculty.name}" readonly="readonly">
						</c:if>
					</c:forEach>
				</td>
				<th style="width: 25%"><span style="color: blue">Move teacher to another Faculty</span></th>
				<td>
					<select name="faculties">
						<option value="-1" selected="selected">Select a faculty</option>
						<c:forEach items="${faculties}" var="faculty">
							<option value="${faculty.id}" >${faculty.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Subjects to add: </th>
				<td>
					<select name="subjectToAdd" multiple="multiple">
						<c:forEach items="${subjectsToAdd}" var="subject">
							<option value="${subject.id}">${subject.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th>Subjects to remove: </th>
				<td>
					<select name="subjectToRemove" multiple="multiple">
						<c:forEach items="${teacher.subjects}" var="subject">
							<option value="${subject.id}">${subject.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>E-mail: </th>
				<td><sf:input type="text" path="email" /></td>
				<td><sf:errors path="email" cssClass="error"/></td>
			</tr>

	   </table><br>

		<span style="position: relative; left: 20px">
			Delete this teacher: <input type="checkbox" id="checkTeacher" name="check" style="margin-right: 20px;">
			<input type="submit" value="Accept changes" >
		</span>

	</sf:form>
	 
	 <br><br>
	 <h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>

	<%@include file='../footer.jsp' %>
</body>