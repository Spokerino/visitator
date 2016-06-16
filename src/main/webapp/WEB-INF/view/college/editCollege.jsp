<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
			<hr>
			<span >
				<a href="<c:url value="/colleges" />" style="font-size: 17; color: #E6E680">
					Colleges
				</a>
			</span >
			</span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${collegeName}
				</a>
			</span><br>
			<span style="color: white; font-size:20">College Editor</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" commandName="college">
		<table id="newItem">
			<tr>
				<th>College Name: </th>
				<td><sf:input type="text" path="name" /></td>
				<td><sf:errors path="name" cssClass="error"/></td>
			</tr>
			<tr>
				<th>College Address: </th>
				<td><sf:input type="text" path="address" /></td>
				<td><sf:errors path="address" cssClass="error"/></td>
			</tr>
		</table><br>

		<span style="position: relative; left: 20px">
			Delete this college: <input type="checkbox" id="checkCollege" name="check" style="margin-right: 20px">
			<input type="submit" value="Accept changes" >
		</span>
	</sf:form>
	
	<hr width="97%">

	<form style="float: left; padding-left: 20px; margin-top: 20px">
		<input type="submit" value="Add faculties" formaction="faculties/new">
	</form><br>

	<h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	<%@include file='../footer.jsp' %>
</body>