<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>City List</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of City</h3>
			<hr>
			<div class="container text-left">
        <div class="container text-left">

				<a href="<%=request.getContextPath()%>/newCity" class="btn btn-success">Add
					New City</a>
			</div>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
					
						<th>Name</th>
						<th>Confirmed</th>
						<th>Recovered</th>
						<th>Deaths</th>
						<th>Action</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="city" items="${listCity}">

						<tr>
							<td><c:out value="${city.name}" /></td>
							<td><c:out value="${city.confirmed}" /></td>
							<td><c:out value="${city.recovered}" /></td>
							<td><c:out value="${city.deaths}" /></td>
							
							<td><a href="editContinent?id=<c:out value='${city.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteContinent?id=<c:out value='${city.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>