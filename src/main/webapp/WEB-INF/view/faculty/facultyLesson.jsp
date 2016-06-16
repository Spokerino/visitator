<%@ include file="../head.jsp"%>

<body>
	<div class="wrapper">
		<header id="wellcome">
			<h1>
				<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
					<hr>
					<span>
						<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/lessons" />" style="font-size: 17; color: #E6E680">
							Lessons
						</a>
					</span>
				</span>
				<span>
					<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
						College: ${collegeName}
					</a>
				</span><br>
				<span>
					<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
						Faculty: ${facultyName}
					</a>
				</span><br>
				<span id="itemHeader" style="padding-left: 0%">Lesson</span>
			</h1>
		</header>

		<%@ include file="../logout.jsp"%><br>

		<table id="vertHeaders">
			<tr>
				<th>Date</th><td>${lesson.date}</td>
			</tr>
			<tr>
				<th>Start and End</th><td>${lesson.startFormat} - ${lesson.endFormat}</td>
			</tr>
			<tr>
				<th>Type</th><td>${lesson.type}</td>
			</tr>
			<tr>
				<th>Subject</th><td>${lesson.subject.name}</td>
			</tr>
			<tr>
				<th>Teacher</th><td>${lesson.teacher.fullName}</td>
			</tr>
		</table>

		<form action="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/lessons/${lesson.id}/edit" />">
			<span style="padding: 10px; float:left">
				<input type="submit" value="Edit lesson">
			</span>
		</form>

		<table id="lw97">
			<tr>
				<th>Group</th>
				<th>Student</th>

				<c:if test="${lesson.type ne 'Lecture' and lesson.type ne 'Consultation'}">
					<th>Mark</th>
				</c:if>
			</tr>
			<c:forEach items="${lesson.groups}" var="group" >
				<c:forEach items="${group.students}" var="student" >
					<tr>
						<td>${group.name}</td>


						<td>
							<c:set var="abs" value="1" />
							<c:forEach items="${lesson.students}" var="absent">

									<c:if test="${absent.id == student.id}">
										<c:set var="abs" value="0" />
									</c:if>
							</c:forEach>

								<c:choose>
									<c:when test="${abs == 0}">
										${student.fullName}
									</c:when>
									<c:otherwise>
										<span style="color: red">${student.fullName} (Absent)</span>
									</c:otherwise>
								</c:choose>

						</td>

						<c:if test="${lesson.type ne 'Lecture' and lesson.type ne 'Consultation'}">
							<td>
								<c:forEach items="${marks}" var="mark">
									<c:if test="${mark.student.id == student.id}">
										${mark.mark}
									</c:if>
								</c:forEach>
							</td>
						</c:if>

					</tr>
				</c:forEach>
			</c:forEach>
		</table>

		 <div class="push"></div>
	 </div>
	<%@include file='../stickyFooter.jsp' %>
</body>