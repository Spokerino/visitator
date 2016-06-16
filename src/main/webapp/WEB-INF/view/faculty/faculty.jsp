<%@ page session="true" %>
<%@ include file="../head.jsp"%>

<body style="height: 84%">
	<div class="wrapper">
		<c:set var="facultyName" value="${faculty.name}" scope="session" />
			<header id="wellcome">
				<h1>
					<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
					<span>
						<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
							College: ${collegeName}
						</a>
					</span><br>
					<span style="font-size: 18; text-align: left;">Faculty: ${facultyName}</span>
				</h1>
		</header>

		<%@ include file="../logout.jsp"%>

		<nav style="background-color: white">
			<ul id="menu">
			  <li><a href="/visitator/colleges">Colleges</a></li>
			  <li><a href="/visitator/colleges/${collegeId}/faculties">Faculties</a></li>
			  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/groups">Groups</a></li>
			  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/subjects" >Subjects</a></li>
			  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/lessons" >Lessons</a></li>
			  <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/teachers">Teachers</a></li>
			   <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/students">Students</a></li>
			   <li><a href="/visitator/colleges/${collegeId}/faculties/${faculty.id}/edit">Edit faculty</a></li>
			</ul>
		</nav><br>

		<div id="contentTable">
			<table id="lw97">
			<caption style="font-size:18"><b>Groups</b></caption>
				<tr>
					<th style="width:25%">Name</th>
					<th style="width:25%">Course</th>
					<th style="width:25%">Students</th>
				</tr>

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
							<c:if test="${not empty group.students[0].firstName}">
								${fn:length(group.students)}
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="push"></div>
	</div>
	<%@include file='../stickyFooter.jsp' %>
</body>