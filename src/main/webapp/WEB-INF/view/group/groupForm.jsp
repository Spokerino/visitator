<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span >
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups" />" style="font-size: 17; color: #E6E680">
						Groups
					</a>
				</span >
			</span>
			<span>
					<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
						College: ${faculty.institution.name}
					</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${faculty.name}
				</a>
			</span><br>
			<span style="color: #AECFA2; font-size:20">Add a group</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" modelAttribute="collegeGroup">
		<table id="newItem">
			<tr>
				<th>Group Name: </th>
				<td><sf:input type="text" path="name" /></td>
				<td><sf:errors path="name" cssClass="error"/></td>
			</tr>
			<tr>
				<th>Group Course: </th>
				<td>
					<sf:select path="course">
						<sf:options  items="${courses}"/>
					</sf:select>
				</td>
			</tr>
		</table><br>
		<input type="submit" value="Add group" style="position: relative; left: 20px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>