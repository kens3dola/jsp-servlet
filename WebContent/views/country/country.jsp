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

				<a href="<%=request.getContextPath()%>/country?action=new" class="btn btn-success">Add
					New Country</a>
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
                                                <th>continent id</th>
                                                <th>Actions</th>
						
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="country" items="${listCountry}">

						<tr>
							<td><c:out value="${country.name}" /></td>
							<td><c:out value="${country.confirmed}" /></td>
							<td><c:out value="${country.recovered}" /></td>
							<td><c:out value="${country.deaths}" /></td>
							<td><c:out value="${country.continent_id}" /></td>

							<td><a href="country?action=update&id=<c:out value='${country.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="country?action=delete&id=<c:out value='${country.id}' />">Delete</a></td>
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