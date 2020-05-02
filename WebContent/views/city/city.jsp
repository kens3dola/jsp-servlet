<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>User Management Application</title>

</head>
<body>
	<jsp:include page="../../common/header.jsp"></jsp:include>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of city</h3>
			<hr>
			<div class="container text-left">
				<div class="container text-left">

					<a href="<%=request.getContextPath()%>/city?action=new"
						class="btn btn-success">Add New city</a>
				</div>

			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>

						<th>Name</th>
						<th>Confirmed</th>
						<th>recovered</th>
						<th>deaths</th>
						<th>Actions</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="city" items="${listCity}">

						<tr>
							<td><c:out value="${city.name}" /></td>
							<td><c:out value="${city.confirmed}" /></td>
							<td><c:out value="${city.recovered}" /></td>
							<td><c:out value="${city.deaths}" /></td>

							<td><a
								href="city?action=update&id=<c:out value='${city.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="city?action=delete&id=<c:out value='${city.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>