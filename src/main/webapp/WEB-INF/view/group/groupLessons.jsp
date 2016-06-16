<%@ include file="../head.jsp"%>

<body>
	<div class="wrapper">
		<header id="wellcome">

				<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>

				</span>

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
				<span id="itemHeader">Lessons</span></h1>

		</header>

		<%@ include file="../logout.jsp"%><br>

		<div style="padding-left: 10px">
			<form method="post" action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons">
				Show lessons at particular date<br>
				<input type="date" name="date">
				<input type="submit" value="Find">
			</form>

			<form action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons/new" method="get">
				<input type="submit"  value="Create new lesson" style="">
			</form>
		</div>

		<table id="lw97">
			<tr>
				<th></th>
				<th>Date</th>
				<th>Lesson start</th>
				<th>Lesson end</th>
				<th>Type</th>
				<th>Subject</th>
				<th>Teacher</th>
				<th>Students</th>
			</tr>
			<c:forEach items="${lessonList}" var="lesson" >
				<tr>
					<td style="width:2%">
						<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/lessons/${lesson.id}" />">
							Select
						</a>
					</td>
					<td style="width:6%">${lesson.date}</td>
					<td style="width:6%">${lesson.startFormat}</td>
					<td style="width:6%">${lesson.endFormat}</td>
					<td style="width:6%">${lesson.type}</td>
					<td style="width:10%">${lesson.subject.name}</td>
					<td style="width:8%">${lesson.teacher.fullName}</td>

					<td style="width:10%">
						<c:set var="sum" value="0"/>
						<c:forEach items="${lesson.groups}" var="group">
							<c:set var="sum" value="${sum + fn:length(group.students)}"/>
						</c:forEach>
						${fn:length(lesson.students)}/${sum}
					</td>
				</tr>
			</c:forEach>
		</table>

	<%-- 		 	<c:if test="${fn:length(lessonList) gt 20}"> --%>
	<!-- 		 		<hr /> -->
	<%-- 		 		<s:url value="/lessons?count=${nextCount}" var="more_url" /> --%>
	<%-- 		 		<a href="${more_url}">More lessons</a> --%>
	<%-- 		 	</c:if> --%>

		 <div class="push"></div>
	 </div>
	<%@include file='../stickyFooter.jsp' %>
</body>