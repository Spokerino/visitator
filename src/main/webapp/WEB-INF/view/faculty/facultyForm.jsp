<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span>
					<a href="<c:url value="/colleges/${collegeId}/faculties" />" style="font-size: 17; color: #E6E680">
						Faculties
					</a>
				</span >
			</span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${collegeName}
				</a>
			</span><br>
			<span style="color: white; font-size:20">Faculties</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" action="" commandName="collegeFaculty">
		<table id="newItem">
			<tr>
				<th>Faculty Name: </th>
				<td><sf:input type="text" path="name" /></td>
				<td><sf:errors path="name" cssClass="error"/></td>
			</tr>
			<tr>
				<th>Years to become Bachelor: </th>
				<td><sf:input type="text" path="yearsToBecomeBachelor" /></td>
				<td><sf:errors path="yearsToBecomeBachelor" cssClass="error"/></td>
			</tr>
			<tr>
				<th>Years to become Master: </th>
				<td><sf:input type="text" path="yearsToBecomeMaster" /></td>
				<td><sf:errors path="yearsToBecomeMaster" cssClass="error"/></td>
			</tr>
		</table><br>
		<input type="submit" value="Add faculty" style="position: relative; left: 20px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>