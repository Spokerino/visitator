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
			<span id="itemHeader" style="padding-left: 0%">Add a Teacher</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" modelAttribute="teacher">
		<table style="text-align: left; width: 80%; padding: 10px">
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
				<th>Subjects: </th>
				<td>
					<select name="subject" multiple="multiple">
						<c:forEach items="${subjects}" var="subject">
							<option value="${subject.id}">${subject.name}</option>
						</c:forEach>
					</select>
				</td>
				<td><span id="empty">${message}</span></td>
			</tr>
			<tr>
				<th>E-mail: </th>
				<td><sf:input type="text" path="email" /></td>
				<td><sf:errors path="email" cssClass="error"/></td>
			</tr>
	   </table><br>
		<input type="submit" value="Add Teacher" style="position: relative; left: 20px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>