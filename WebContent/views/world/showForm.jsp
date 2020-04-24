<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:include page="../../common/header.jsp"></jsp:include>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<h1>Edit statistics</h1>
		<form action="<%=request.getContextPath()%>/world" method="post">

			<div class="form-group">
				<label for="uname">Confirmed:</label> <input type="number"
					class="form-control" id="conf" value="${world.confirmed }"
					name="confirmed" required>
			</div>

			<div class="form-group">
				<label for="uname">Recovered:</label> <input type="number"
					class="form-control" id="reco" value="${world.recovered }"
					name="recovered" required>
			</div>
			<div class="form-group">
				<label for="uname">Deaths:</label> <input type="number"
					class="form-control" id="deaths" value="${world.deaths }"
					name="deaths" required>
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>