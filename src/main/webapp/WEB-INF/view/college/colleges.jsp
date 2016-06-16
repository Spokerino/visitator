<%@ include file="../head.jsp"%>

<body style="height: 94% ">
	<div class="wrapper">
		<header id="wellcome">
			<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
			<span style="color: #E6E680">Colleges</span></h1>
		</header>

		<%@ include file="../logout.jsp"%>

		<form style="float:right; padding-right: 30px" action="colleges/new">
			<input type="submit" value="Add a new college">
		</form>

		<table id="lw97">
			<tr>
				<th style="width:25%">Name</th>
				<th style="width:25%">Address</th>
				<th style="width:25%">Faculties</th>
			</tr>

			<c:forEach items="${colleges}" var="inst">
				<tr>
					<td><a href="/visitator/colleges/${inst.id}">${inst.name}</a></td>
					<td>${inst.address}</td>
					<td>
						<c:forEach items="${inst.specializations}" var="spec">
							${spec.name}<br>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>

		<div class="push"></div>
	</div>

	<div class="footer">Copyright © by Ivaniuk E.G. 2015<br>
		Original idea of design was taken from w3.schools.com
	</div>

</body>
