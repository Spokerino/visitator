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
					College: ${group.specialization.institution.name}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${group.specialization.name}
				</a>
			</span><br>
			<span style="color: #E6E680; font-size:20;">Group Editor</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br><br>

	<sf:form method="post" commandName="group">
		<table style="text-align: left; width: 80%; padding: 10px">
			<tr>
				<th width="18%">Group Name: </th>
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
		<span style="position: relative; left: 20px">
			Delete this group: <input type="checkbox" id="checkGroup" name="check" style="margin-right: 20px;">
			<input type="submit" value="Accept changes" >
		</span>
	</sf:form>
	 
	 <hr width="97%">
	 <form style="float: left; padding-left: 20px; margin-top: 20px">
	 	<input type="submit" value="Add students" formaction="students/new">
<!-- 		<input type="submit" value="Add teachers" formaction="teachers/new" style="margin-left: 15px"> -->
	 </form>
	 
	 <br><br>
	 <h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	<%@include file='../footer.jsp' %>
</body>