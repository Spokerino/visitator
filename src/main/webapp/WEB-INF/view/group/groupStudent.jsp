<%@ include file="../head.jsp"%>

<body>
	<header style="height: 115">
		<h1>
			<span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a>
				<hr>
				<span>
					<a href="<c:url value="/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/students" />" style="font-size: 17; color: #E6E680">
						Students
					</a>
				</span>
			</span>
			<span>
				<a id="collegeHeader" href="/visitator/colleges/${collegeId}">
					College: ${student.group.specialization.institution.name}
				</a>
			</span><br>
			<span>
				<a id="facultyHeader" href="/visitator/colleges/${collegeId}/faculties/${facultyId}">
					Faculty: ${student.group.specialization.name}
				</a>
			</span><br>
			<span>
				<a id="groupHeader" style="padding-left: 0%" href="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}">
					Group: ${student.group.name}
				</a>
			</span><br>
			<span id="itemHeader" style="padding-left: 0%">${student.fullName}</span>
		</h1>
	</header>

	<%@ include file="../logout.jsp"%><br>

	<div style="float: left; padding-left: 10px">
		<form method="post" action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/students/${student.id}">
			Show lessons at particular date<br>
			<input type="date" name="date">
		 	<input type="submit" value="Find">				
	 	</form>
    </div><br>

    <form action="/visitator/colleges/${collegeId}/faculties/${facultyId}/groups/${groupId}/students/${student.id}/edit" method="get">
	 	<input type="submit"  value="Edit student" style="margin-right: 30px; float: right">
 	</form>
    
    <table id="lw97">
    <caption style="font-size:18"><b>${student.lastName}`s lessons </b>(red color means that student was absent)</caption>
    	<tr>
   			<th>Date</th>
 			<th>Lesson start</th>
 			<th>Lesson end</th>
 			<th>Type</th>
 			<th>Subject</th>
 			<th>Teacher</th>
			<th width="6%">Mark</th>
    	</tr>
    	
    	<c:forEach items="${lessons}" var="lesson">
    	<c:set var="absent" value="1" />
			<c:forEach items="${lesson.students}" var="st">
				<c:if test="${st.id == student.id}">
					<c:set var="absent" value="0" />
				</c:if>
			</c:forEach>
 			<c:choose>
 				<c:when test="${absent == 1}">
	    			<tr id="absent">
	    				<td style="width:6%">${lesson.date}</td>
		 				<td style="width:6%">${lesson.startFormat}</td>
		 				<td style="width:6%">${lesson.endFormat}</td>
		 				<td style="width:6%">${lesson.type}</td>
		 				<td style="width:10%">${lesson.subject.name}</td>
		 				<td style="width:8%">${lesson.teacher.fullName}</td>
		 				<c:if test="${lesson.type ne 'Lecture' and lesson.type ne 'Consultation'}">
			 				<td style="width:3%">
				 				<c:forEach items="${lesson.marks}" var="mark">
				 					<c:if test="${mark.student.id == student.id}">
				 						${mark.mark}
				 					</c:if>
				 				</c:forEach>
			 				</td>
		 				</c:if>
			    	</tr>
	    		</c:when>
	    		<c:otherwise>
	    			<tr>
		    			<td style="width:6%">${lesson.date}</td>
		 				<td style="width:6%">${lesson.startFormat}</td>
		 				<td style="width:6%">${lesson.endFormat}</td>
		 				<td style="width:6%">${lesson.type}</td>
		 				<td style="width:10%">${lesson.subject.name}</td>
		 				<td style="width:8%">${lesson.teacher.fullName}</td>
		 				<c:if test="${lesson.type ne 'Lecture' and lesson.type ne 'Consultation'}">
			 				<td style="width:3%">
				 				<c:forEach items="${lesson.marks}" var="mark">
				 					<c:if test="${mark.student.id == student.id}">
				 						${mark.mark}
				 					</c:if>
				 				</c:forEach>
			 				</td>
		 				</c:if>
			    	</tr>
	    		</c:otherwise>
	    	</c:choose>
    	</c:forEach>
	</table><br>
	<%@include file='../footer.jsp' %>
</body>