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
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${facultyName}
				</a>
			</span><br>
			<span style="color: #E6E680; font-size:20;">Faculty Editor</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br><br>

	<sf:form method="post" commandName="faculty">
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

		<span style="position: relative; left: 20px">
			Delete this faculty: <input type="checkbox" id="checkFaculty" name="check" style="margin-right: 20px;">
			<input type="submit" value="Accept changes" >

		</span>
	</sf:form>
	 
	 <hr width="97%">
	 <form style="float: left; padding-left: 20px; margin-top: 20px">
	 	<input type="submit" value="Add subjects" formaction="subjects/new">
		<input type="submit" value="Add teachers" formaction="teachers/new" style="margin-left: 15px">
		<input type="submit" value="Add groups" formaction="groups/new" style="margin-left: 15px">
	 </form>
	 
	 <br><br>
	 <h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>

	<%@include file='../footer.jsp' %>
</body>