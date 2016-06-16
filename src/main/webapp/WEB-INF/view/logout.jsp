<c:if test="${pageContext.request.userPrincipal.name != null}">
			<span id="reg">
				Currently logged as : <span>${pageContext.request.userPrincipal.name}</span>
				<c:url value="/logout" var="logoutUrl" />
			</span>
    <form id="reg" action="${logoutUrl} ">
        <input type="submit" name="submit" value="Logout" >
    </form>
</c:if><br>
