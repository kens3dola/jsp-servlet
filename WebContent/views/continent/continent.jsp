<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Continent</title>

</head>
<body>
<jsp:include page="../../common/header.jsp"></jsp:include>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Continents</h3>
			<hr>
			<div class="container text-left">
        <div class="container text-left">

				<a href="<%=request.getContextPath()%>/continent?action=new" class="btn btn-success">Add
					New Continent</a>
			</div>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
					
						<th>Name</th>
						<th>Confirmed</th>
						<th>Discovred</th>
						<th>Deaths</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="continent" items="${listContinent}">

						<tr>
							<td><c:out value="${continent.name}" /></td>
							<td><c:out value="${continent.confirmed}" /></td>
							<td><c:out value="${continent.recovered}" /></td>
							<td><c:out value="${continent.deaths}" /></td>
							
							<td><a href="continent?action=edit&id=<c:out value='${continent.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="continent?action=delete&id=<c:out value='${continent.id}' />">Delete</a></td>
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