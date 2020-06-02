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
<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false
			: (boolean) session.getAttribute("islogin");
%>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="row" style="height: 100%; overflow: auto">
	<div class="col-md-2 p-1">
			<div class="card">
				<div class="card-header">world</div>
				<div class="card-body">
					<c:forEach var="world" items="${world}">
						<span class="badge badge-warning"> Name:${world.name }</span>
						<br>
						<span class="badge badge-primary">
							Confirmed:${world.confirmed }</span>
						<br>
						<span class="badge badge-success">Recovered:
							${world.recovered }</span>
						<br>
						<span class="badge badge-danger">Deaths: ${world.deaths }</span>
						<br>	
						<hr>
							<c:if test="${islogin}">
								<a href="statistic?action=update&id=<c:out value='${world.id}' />"><button type="button" class="btn btn-primary btn-sm">Update</button></a>
			               </c:if>
			              <a href=""><button type="button" class="btn btn-primary btn-sm">Chart</button></a>
					</c:forEach>
				
				</div>

			</div>
			
		</div>
		
	<div class="col-md-2 p-1">
			<div class="card">
				<div class="card-header">continent</div>
				<div class="card-body">
					<c:forEach var="continent" items="${continent}">
						<span class="badge badge-warning"> Name:${continent.name }</span>
						<br>
						<span class="badge badge-primary">
							Confirmed:${continent.confirmed }</span>
						<br>
						<span class="badge badge-success">Recovered:
							${continent.recovered }</span>
						<br>
						<span class="badge badge-danger">Deaths: ${continent.deaths }</span>
						<br>
						<c:if test="${islogin}">
								<a href="statistic?action=update&id=<c:out value='${continent.id}' />"><button type="button" class="btn btn-primary btn-sm">Update</button></a>
			               </c:if>	
			                <a href=""><button type="button" class="btn btn-primary btn-sm">Chart</button></a>
						<hr>
					 
					</c:forEach>
					
				</div>

			</div>
			
		</div>
	<div class="col-md-2 p-1">
			<div class="card">
				<div class="card-header">country</div>
				<div class="card-body">
					<c:forEach var="country" items="${country}">
						<span class="badge badge-warning"> Name:${country.name }</span>
						<br>
						<span class="badge badge-primary">
							Confirmed:${country.confirmed }</span>
						<br>
						<span class="badge badge-success">Recovered:
							${country.recovered }</span>
						<br>
						<span class="badge badge-danger">Deaths: ${country.deaths }</span>
						<br>
						<c:if test="${islogin}">
								<a href="statistic?action=update&id=<c:out value='${country.id}' />"><button type="button" class="btn btn-primary btn-sm">Update</button></a>
			               </c:if>	
			                <a href=""><button type="button" class="btn btn-primary btn-sm">Chart</button></a>
						<hr>
							
					</c:forEach>
				
				</div>

			</div>
			
		</div>
			<div class="col-md-2 p-1">
			<div class="card">
				<div class="card-header">city</div>
				<div class="card-body">
					<c:forEach var="city" items="${city}">
						<span class="badge badge-warning"> Name:${city.name }</span>
						<br>
						<span class="badge badge-primary">
							Confirmed:${city.confirmed }</span>
						<br>
						<span class="badge badge-success">Recovered:
							${city.recovered }</span>
						<br>
						<span class="badge badge-danger">Deaths: ${city.deaths }</span>
						<br>
						<c:if test="${islogin}">
								<a href="statistic?action=update&id=<c:out value='${city.id}' />"><button type="button" class="btn btn-primary btn-sm">Update</button></a>
			               </c:if>	
			                <a href=""><button type="button" class="btn btn-primary btn-sm">Chart</button></a>
						<hr>
							
					</c:forEach>
				
				</div>

			</div>
			
		</div>
		

		<div class="col-md-4 p-1" style="height: 90vh; overflow: auto">
			<c:if test="${feed==null }">
				<div class="card-body">Not found</div>
			</c:if>
			<c:if test="${feed!=null }">
				<c:forEach var="m" items="${feed }">
					<div class="card mb-2">
						<div class="card-header">
							<a href="${m.link }">${m.title } | <span class="small">${m.pubDate }</span></a>
						</div>
						<div class="card-body">${m.description }</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>