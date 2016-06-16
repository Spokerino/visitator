<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span>
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons" />" style="font-size: 17; color: #E6E680">
						Lessons
					</a>
				</span ><br>
			</span>
			 Create new lesson<br>
			 <span id="groupHeader" style="padding-left: 0%">
					Group: ${groupName}
			 </span>
		 </h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" action="" commandName="lesson">
		<table style="text-align: left; width: 80%">
			<tr>
				<th>Date:</th>
				<th>Lesson begins at:</th>
				<th>Lesson ends at:</th>
				<th>Type:</th>
				<th>Subject:</th>
				<th>Teacher:</th>
			</tr>
			<tr>
				<td><sf:input id="date" type="date" path="date" /></td>
				<td><sf:input id="lStart" type="time" oninput="setLessonEnd(${duration})" path="start" /></td>
				<td><sf:input id="lEnd" type="time" path="end" /></td>

				<td>
					<select name="types">
						<option value="2" >Practicum</option>
						<option value="3" >Labarotory Practicum</option>
						<option value="4" >Consultation</option>
					</select>
				</td>

				<td>
					<sf:select path="subject" >
						<%--<c:forEach items="${faculty.subjects}" var="subject">--%>
							<%--<option value="${subject.id}" >${subject.name}</option>--%>
						<%--</c:forEach>--%>
						<sf:option value="">Select a subject</sf:option>
						<sf:options items="${subjects}" />
					</sf:select>
				</td>

				<td>
					<sf:select path="teacher">
						<sf:option value="">Select a teacher</sf:option>
						<sf:options items="${teachers}" />
					</sf:select>
				</td>
			</tr>

			<tr>
				<td><sf:errors path="date" cssClass="error"/></td>
				<td><sf:errors path="start" cssClass="error"/></td>
				<td><sf:errors path="end" cssClass="error"/></td>
				<td></td>
				<td><sf:errors path="subject" cssClass="error"/></td>
				<td><sf:errors path="teacher" cssClass="error"/></td>
			</tr>
	   </table><br>
		<input type="submit" value="Add new lesson" style="position: relative; left: 10px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>