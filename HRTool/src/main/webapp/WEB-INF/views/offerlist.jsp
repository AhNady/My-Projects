<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Offers Data</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<%@include file="authheader.jsp"%>

		<form:form method="POST" modelAttribute="offer"
			class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-12">
					<label style="font-size: 20px"  class="col-lg-3 control-lable" for="offerName">Offer
						Name:</label>
					<div class="col-md-7">
						<form:input type="text" path="offerName" id="offerName"
							class="form-control input-sm" />
					</div>
					<div class="form-actions floatRight">
						<input type="submit" value="Search" class="btn btn-primary btn-sm" /> or <a
									href="<c:url value='/offers' />">Cancel</a>
					</div>
				</div>
			</div>

		</form:form>
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<div class="panel-heading">
				<span class="lead">Offers Info</span>
			</div>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Offer Name</th>
						<th>Offer Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${offers}" var="offer">
						<tr>
							<td>${offer.offerName}</td>
							<td>${offer.offerDescription}</td>

							<sec:authorize access="hasRole('HRADMIN')">
								<td><a
									href="<c:url value='/edit-offer-${offer.offerName}' />"
									class="btn btn-success custom-width">edit</a></td>

								<td><a
									href="<c:url value='/delete-offer-${offer.offerName}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('HRADMIN')">
			<div class="well">
				<a href="<c:url value='/offer' />">Add New Offer</a>
			</div>
		</sec:authorize>
	</div>
</body>
</html>