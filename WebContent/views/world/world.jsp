<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>World</title>
</head>
<body>
	<jsp:include page="../../common/header.jsp"></jsp:include>
	<form class="form">
	<span class="badge badge-primary">Confirmed: ${world.confirmed }</span>
	<br>
	<span class="badge badge-success">Recovered: ${world.recovered }</span>
	<br>
	<span class="badge badge-danger">Deaths: ${world.deaths }</span>
	<br>
	<a class="btn btn-primary" href="<%= request.getContextPath()%>/world?action=update">Update</a>
	<a class="btn btn-primary" href="<%= request.getContextPath()%>/world?action=delete">Delete</a>
	</form>
	<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>