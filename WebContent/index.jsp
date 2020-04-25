<%@page
	import="com.se2.model.FeedMessage,com.se2.model.Feed,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>

</head>
<body>
	<jsp:include page="./common/header.jsp"></jsp:include>
	<div class="row" style="height:100%;overflow:auto">
		<div class="col-md-2">
			<div class="card">
				<div class="card-header">World</div>
				<div class="card-body">
					<span class="badge badge-primary">Confirmed:
						${world.confirmed }</span> <br> <span class="badge badge-success">Recovered:
						${world.recovered }</span> <br> <span class="badge badge-danger">Deaths:
						${world.deaths }</span>
				</div>
			</div>
		</div>

		<div class="col-md-3">
			<div class="card">
				<div class="card-header">Continents</div>
				<div class="card-body">
					<span class="badge badge-primary">Confirmed:
						${world.confirmed }</span> <br> <span class="badge badge-success">Recovered:
						${world.recovered }</span> <br> <span class="badge badge-danger">Deaths:
						${world.deaths }</span>
				</div>
				
			</div>
		</div>

		<div class="col-md-3">
			<div class="card">
				<div class="card-header">Cities</div>
				<div class="card-body">
					<span class="badge badge-primary">Confirmed:
						${world.confirmed }</span> <br> <span class="badge badge-success">Recovered:
						${world.recovered }</span> <br> <span class="badge badge-danger">Deaths:
						${world.deaths }</span>
				</div>
			</div>
		</div>

		<div class="col-md-4" style="height:90vh;overflow:auto">
			<c:if test="${feed==null }">
				<div class="card-body">Not found</div>
			</c:if>
			<c:if test="${feed!=null }">
					<c:forEach var="m" items="${feed }">
						<div class="card">
							<div class="card-header">${m.title }</div>
							<div class="card-body">${m.description }</div>
						</div>
					</c:forEach>
			</c:if>
		</div>
	</div>
	<jsp:include page="./common/footer.jsp"></jsp:include>
</body>
</html>