<%@ page session="true" %>
<%@ include file="../head.jsp"%>

<body>
	<div class="wrapper">
		<header id="wellcome">
			<h1>
				<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
				<span>
					<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
						College: ${faculty.institution.name}
					</a>
				</span><br>
				<span>
					<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${faculty.id}">
						Faculty: ${faculty.name}
					</a>
				</span><br>
				<span style="color: #eeeeee; font-size: 18; padding-left: 6%">Students</span>
			</h1>
		</header>

		<%@ include file="../logout.jsp"%><br>

		<form style="float:left; padding-left: 10px" action="" method="post">
			<input type="text" name="name" placeholder="Search by Last name">
			<input type="submit" value="Search">
		</form>

		<form style="float:right; padding-right: 30px" action="students/new">
			<input type="submit" value="Add a new student">
		</form>

		<table id="lw97">
			<tr>
				<th style="width:15%">Name</th>
				<th style="width:15%">Faculty</th>
				<th style="width:15%">Group</th>
				<th style="width:5%">Course</th>
				<th style="width:5%">Date of birth</th>
				<th style="width:5%">Gender</th>
			</tr>

			<c:forEach items="${students}" var="student">
				<c:set var="studentFacultyId" value="0" />
				<tr>
					<td>
						<a id="link" href="/visitator/colleges/${collegeId}/faculties/${student.group.specialization.id}/groups/${student.group.id}/students/${student.id}"
						style="text-decoration: none">
							${student.fullName}
						</a>
					</td>

					<td>
						<a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}" style="text-decoration: none">
							${faculty.name}
						</a>
					</td>

					<td>
						<a href="/visitator/colleges/${collegeId}/faculties/${studentFacultyId}/groups/${student.group.id}"
						style="text-decoration: none">
							${student.group.name}
						</a>
					</td>

					<td>
						${student.group.course}
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