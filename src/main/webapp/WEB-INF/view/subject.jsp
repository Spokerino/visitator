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
			</span> Subject Editor
		</h1>
	</header>

	<%@ include file="logout.jsp"%><br>

	<sf:form method="post" action="" commandName="subject">
		<table style="text-align: left; width: 80%; padding: 10px">
			<tr>
				<th width="14%">Subject Name: </th>
				<td><sf:input type="text" path="name" value="${subject.name}"/></td>
				<td><sf:errors path="name" cssClass="error"/></td>
			</tr>
		</table><br>

		<span style="position: relative; left: 20px">
			Delete this subject: <input type="checkbox" id="checkSubject" name="check" style="margin-right: 20px">
			<input type="submit" value="Accept changes" >
		</span>
		<h2 id="alert" style="text-align: center; color: red; padding-top: 5px"></h2>
	</sf:form>
	<%@include file='footer.jsp' %>
</body>