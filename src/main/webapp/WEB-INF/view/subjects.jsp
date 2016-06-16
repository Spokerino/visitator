<%@ include file="head.jsp"%>

<body style="height: 94%">
	<div class="wrapper">
		<header style="height: 8%">
			<h1>
				<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
				<span style="font-size: 20">Subjects</span>
			</h1>
		</header>

		<%@ include file="logout.jsp"%><br>

		<form style="float:right; padding-right: 30px" action="subjects/new">
			<input type="submit" value="Add a new subject">
		</form>

		<table id="lw97">
			<tr>
				<th>Name</th>
			</tr>

			<c:forEach items="${subjectList}" var="subject">
				<tr>
					<td>
						<a href="subjects/${subject.id}/edit" style="text-decoration: none">
							${subject.name}
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<div class="push"></div>
	</div><br>
	<%@include file='stickyFooter.jsp' %>
</body>