<%@ include file="../head.jsp"%>

<body style="height: 94%">
	<div class="wrapper">
		<header>
			<h1>
				<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
				<span>
					<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
						College: ${faculty.institution.name}
					</a>
				</span><br>
				<span>
					<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
						Faculty: ${faculty.name}
					</a>
				</span><br>
				 <span id="itemHeader">Groups</span>
			</h1>
		</header>

		<%@ include file="../logout.jsp"%><br>

		<form class="groupForm" style="" action="groups/new">
			<input type="submit" value="Add a new group">
		</form>

		<table id="lw97">
			<tr>
				<th style="width:35%">Name</th>
				<th style="width:25%">Course</th>
				<th style="width:25%">Students</th>
			</tr>

			<c:set var="groupName" value=""/>
			<c:forEach items="${educationGroupList}" var="group">
				<tr>
					<td>
						<a id="link" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${group.id}">
							${group.name}
						</a>
					</td>

					<td>
						${group.course}
					</td>

					<td>
						<c:set var="student" value="${group.students[0]}" />
						<c:if test="${not empty student.firstName}">
							${fn:length(group.students)}
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="push"></div>
	</div><br>
	<%@include file='../stickyFooter.jsp' %>
	<%--<script>--%>
		<%--function getSelected() {--%>
			<%--var data = document.getElementById("selector").value();--%>
			<%--return data;--%>
		<%--}--%>
	<%--</script>--%>
</body>