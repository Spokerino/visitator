<%@ include file="head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator"><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span>
					<a href="<c:url value="/subjects" />" style="font-size: 17; color: #E6E680">
						Subjects
					</a>
				</span>
			</span> Add a Subject
		</h1>
	</header>

	<%@ include file="logout.jsp"%><br>

	<sf:form method="post" action="" commandName="subject">
		<table style="text-align: left; width: 80%; padding: 10px">
			<tr>
				<th>Subject Name: </th>
				<td><sf:input type="text" path="name" /></td>
				<td><sf:errors path="name" cssClass="error"/></td>
			</tr>
		</table><br>
		<input type="submit" value="Add subject" style="position: relative; left: 20px">
	</sf:form>
	<%@include file='footer.jsp' %>
</body>