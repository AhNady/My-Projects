
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="authbar">
	<span>Dear <a href="<c:url value="/userInfo" />"><strong>${loggedinuser}</strong></a>, Welcome to HR Tool
	</span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
	
	<br /> 
	
	<br /> 
	
	<span class="floatLeft"><a
		href="<c:url value="/offers" />">Employee Offers</a></span> <br />

	<sec:authorize access="hasRole('HRADMIN')">
		<span class="floatLeft"><a href="<c:url value="/list" />">Employees
				Data</a></span>
	</sec:authorize>
</div>
