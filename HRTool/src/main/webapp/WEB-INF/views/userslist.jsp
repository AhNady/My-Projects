<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Data</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<%@include file="authheader.jsp"%>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Employees Data</span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Firstname</th>
						<th>Lastname</th>
						<th>Email</th>
						<th>Department</th>
						<th>Grade</th>
						<th>Direct Manager</th>
						<sec:authorize access="hasRole('EMPLOYEE') or hasRole('HRADMIN')">
							<th width="100"></th>
						</sec:authorize>
						<sec:authorize access="hasRole('HRADMIN')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.department}</td>
							<td>${user.grade}</td>
							<td>${user.directManager}</td>

							<td><a href="<c:url value='/edit-user-${user.userName}' />"
								class="btn btn-success custom-width">edit</a></td>
							<c:choose>
								<c:when test="${user.userProfile.type=='HRADMIN'}">
									<td></td>
								</c:when>
								<c:otherwise>
									<td><a
										href="<c:url value='/delete-user-${user.userName}' />"
										class="btn btn-danger custom-width">delete</a></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('HRADMIN')">
			<div class="well">
				<a href="<c:url value='/newuser' />">Add New User</a>
			</div>
		</sec:authorize>
	</div>
</body>
</html>