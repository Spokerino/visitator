<%@ include file="../head.jsp"%>

<body style="height: 94%">
	<div class="wrapper">
		<header>
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span>
					<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
						College: ${collegeName}
					</a>
			</span><br>
			<span style="font-size: 20">Subjects</span></h1>
		</header>

		<%@ include file="../logout.jsp"%><br>

		<table id="lw97">
			<tr>
				<th>Name</th>
			</tr>

			<c:forEach items="${subjectList}" var="subject">
				<tr>
					<td>${subject.name}</td>
				</tr>
			</c:forEach>
		</table>

		<div class="push"></div>
	</div><br>
	<%@include file='../stickyFooter.jsp' %>
</body>
