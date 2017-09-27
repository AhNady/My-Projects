<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
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
				<span class="lead">Your Info</span>
			</div>
			<table class="table table-hover">
				<tr>
					<td>First Name :</td>
					<td>${currentUser.firstName}</td>
				</tr>
				<tr>
					<td>Last Name :</td>
					<td>${currentUser.lastName}</td>
				</tr>
				<tr>
					<td>Email :</td>
					<td>${currentUser.email}</td>
				</tr>
				<tr>
					<td>Department :</td>
					<td>${currentUser.department}</td>
				</tr>
				<tr>
					<td>Grade :</td>
					<td>${currentUser.grade}</td>
				</tr>
				<tr>
					<td>Direct Manager :</td>
					<td>${currentUser.directManager}</td>
				</tr>

			</table>
		</div>
	</div>
	<div class="row">
		<div class="form-actions floatRight">
			<a href="<c:url value='/edit-user-${currentUser.userName}' />"
				class="btn btn-success">Edit Profile Data</a>
		</div>
	</div>

</body>
</html>