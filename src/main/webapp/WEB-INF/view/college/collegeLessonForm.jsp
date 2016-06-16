<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
		<hr>
			<span >
				<a href="<c:url value="/colleges/${collegeId}/lessons" />" style="font-size: 17; color: #E6E680">
					Lessons
				</a>
			</span ><br>
		</span> Create new lesson
			</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<sf:form method="post" action="" commandName="lesson">
		<table style="text-align: left; width: 95%; padding: 10px">

			<tr>
				<th>Date:</th>
				<th>Lesson begins at:</th>
				<th>Lesson ends at:</th>
				<th>Type:</th>
				<th>Faculty:</th>
				<th>Group:</th>
				<th><label for="subjects">Subject:</label></th>
				<th>Teacher:</th>
			</tr>
			<tr>
				<td>
					<sf:input id="date" type="date" path="date" /><br>

				</td>

				<td>
					<sf:input id="lStart" type="time" oninput="setLessonEnd(${duration})" path="start" />

				</td>

				<td>
					<sf:input id="lEnd" type="time" path="end" />

				</td>

				<td>
					<select id="types" name="types">
						<option value="2">Practicum</option>
						<option value="1">Lecture</option>
						<option value="3">Labarotory Practicum</option>
						<option value="4">Consultation</option>
					</select>
				</td>

				<td>
					<sf:select path="faculty" id="fclty">
						<sf:option value="0" selected="selected">Select a faculty</sf:option>
						<sf:options items="${faculties}" />
					</sf:select>
				</td>

				<td>
					<sf:select path="group" id="groups">
						<sf:option value="" selected="selected" disabled="">Select a group</sf:option>
						<sf:options items="${groups}" />
					</sf:select>
				</td>

				<td>
					<sf:select path="subject" id="subjects">
						<sf:option value="">Select a subject</sf:option>
						<sf:options items="${subjects}" />
					</sf:select>
				</td>

				<td>
					<sf:select path="teacher" >
						<sf:option value="" selected="selected">Select a teacher</sf:option>
						<sf:options items="${teachers}" />
					</sf:select>
				</td>
			</tr>

			<tr>
				<td><sf:errors path="date" cssClass="error"/></td>
				<td><sf:errors path="start" cssClass="error"/></td>
				<td><sf:errors path="end" cssClass="error"/></td>
				<td></td>
				<td><sf:errors path="faculty" cssClass="error"/></td>
				<td><sf:errors path="group" cssClass="error"/></td>
				<td><sf:errors path="subject" cssClass="error"/></td>
				<td><sf:errors path="teacher" cssClass="error"/></td>
			</tr>

	   </table>

	<br><br>
	<input type="submit" value="Add new lesson" style="position: relative; left: 20px">

	</sf:form>

	<%@include file='../footer.jsp' %>
</body>
