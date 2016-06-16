<%@ include file="head.jsp"%>

<body>
	<header id="wellcome">	
		<h1><span id="visitator"><a href="/visitator" ><span style="color:#AECFA2">V</span>isitator!</a></span>
		<span style="color: #E6E680">Please Login!</span></h1>
 	</header>
 	
 	<div id="login-box">
 		To apply changes to database login as <span style="color:blue; font-weight: bold;">admin:admin</span>
		<h2>Login with Username and Password</h2>
 
		<c:if test="${not empty error}">
			<div class="loginError">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
 
		<form name="loginForm" action="/visitator/login" method='POST'>
			  <table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>
						<input name="submit" type="submit"
						value="submit" />
					</td>
				</tr>
			  </table>
 
<%-- 		  <input type="hidden" name="${_csrf.parameterName}" --%>
<%-- 			value="${_csrf.token}" /> --%>
 
		</form>
	</div>
	<%@include file='footer.jsp' %>
</body>