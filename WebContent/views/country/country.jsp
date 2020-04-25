<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
			<h3 class="text-center">List of Country</h3>
			<hr>
			<div class="container text-left">
        <div class="container text-left">

				<a href="<%=request.getContextPath()%>/newCountry" class="btn btn-success">Add
					New User</a>
			</div>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
					
						<th>Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Actions</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="continent" items="${listCountry}">

						<tr>
							<td><c:out value="${country.name}" /></td>
							<td><c:out value="${country.confirmed}" /></td>
							<td><c:out value="${country.recovered}" /></td>
							<td><c:out value="${country.deaths}" /></td>
							
							<td><a href="editContinent?id=<c:out value='${country.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteContinent?id=<c:out value='${country.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	<jsp:include page ="../../common/footer.jsp"></jsp:include>
</body>
</html>