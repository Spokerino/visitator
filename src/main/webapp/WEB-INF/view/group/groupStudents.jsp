<%@ page session="true" %>
<%@ include file="../head.jsp"%>

<body>
	<div class="wrapper">
			<header id="wellcome">
				<h1>
					<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
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
					<span>
						<a id="groupHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}">
							Group: ${group.name}
						</a>
					</span><br>
					<span id="itemHeader">Students</span>
				</h1>
		</header>

		<%@ include file="../logout.jsp"%><br>

		<form style="float:right; padding-right: 30px" action="students/new">
			<input type="submit" value="Add a new student">
		</form>

		<table id="lw97">
			<tr>
				<th style="width:15%">Name</th>
				<th style="width:15%">Group</th>
				<th style="width:5%">Course</th>
				<th style="width:5%">Date of birth</th>
				<th style="width:5%">Gender</th>
			</tr>

			<c:forEach items="${group.students}" var="student">
				<tr>
					<td>
					<a id="link" href="/visitator/colleges/${collegeId}/faculties/${student.group.specialization.id}/groups/${student.group.id}/students/${student.id}"
						style="text-decoration: none">
							${student.fullName}
						</a>
					</td>
					<td>
						<a href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}"
						style="text-decoration: none">
							${group.name}
						</a>
					</td>

					<td>
						${group.course}
					</td>

					<td>
						${student.birthday}
					</td>

					<td>
						${student.genderToString}
					</td>
				</tr>
			</c:forEach>
		</table>

		<div class="push"></div>
	</div><br>
	<%@include file='../stickyFooter.jsp' %>
</body>