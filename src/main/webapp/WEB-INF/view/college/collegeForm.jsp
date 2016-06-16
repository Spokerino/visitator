<%@ include file="../head.jsp"%>

<body>
	<header style="height: 100">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span>
					<a href="<c:url value="/colleges" />" style="font-size: 17; color: #E6E680">
						Colleges
					</a>
				</span>
			</span> Add a College
		</h1>
	</header>

	<%@ include file="../logout.jsp"%>

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
		<input type="submit" value="Add college" style="left: 20px">
	</sf:form>
	<%@include file='../footer.jsp' %>
</body>
