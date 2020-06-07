<%@page
	import="com.se2.model.FeedMessage,com.se2.model.Feed,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<c:set var="current" value="${param.ddLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="com/se2/resources/message" scope="session"/>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>


<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false : (boolean) session.getAttribute("islogin");
%>

</head>
<body>
	<style>
::-webkit-scrollbar {
	width: 1px;
}
</style>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="row p-0" style="height: 100%; overflow: auto">
		<div class="col-md-10 p-0">
			<div class="row"></div>
			<div class="row m-0 p-10">

				<div class="col-md-3 p-0">
					<div class="card" style="height: 90vh; overflow: auto">
						<div class="card-header"><fmt:message>world</fmt:message></div>
						<div class="card-body">
							<c:forEach var="world" items="${world}">

								<span class="badge badge-primary">
									Confirmed:${world.confirmed }</span>
								<br>
								<span class="badge badge-success">Recovered:
									${world.recovered }</span>
								<br>
								<span class="badge badge-danger">Deaths: ${world.deaths }</span>
								<br>
								<c:if test="${islogin}">
									<a
										href="statistic?action=update&id=<c:out value='${world.id}' />"><button
											type="button" class="btn btn-primary btn-sm"><fmt:message>update</fmt:message></button></a>
								</c:if>
								<a href="<%=request.getContextPath()%>/charts?code=world&name=world"><button
										type="button" class="btn btn-primary btn-sm"><fmt:message>chart</fmt:message></button></a>
								<hr>
							</c:forEach>

						</div>
					</div>
				</div>
				<div class="col-md-3 p-0">
					<div class="card" style="height: 90vh; overflow: auto">
						<div class="card-header"><fmt:message>continent</fmt:message></div>
						<div class="card-body">

							<span class="badge badge-primary"><fmt:message>confirmed</fmt:message></span> <span
								class="badge badge-success"><fmt:message>recovered</fmt:message></span> <span
								class="badge badge-danger"><fmt:message>deaths</fmt:message></span>
							<hr>
							<c:forEach var="continent" items="${continent}">
								<span class="badge badge-warning">${continent.name }</span>
								<br>
								<span class="badge badge-primary"> ${continent.confirmed }</span>

								<span class="badge badge-success">${continent.recovered }</span>
								<span class="badge badge-danger">${continent.deaths }</span>
								<br>
								<c:if test="${islogin}">
									<a
										href="statistic?action=update&id=<c:out value='${continent.id}' />"><button
											type="button" class="btn btn-primary btn-sm"><fmt:message>update</fmt:message></button></a>
								</c:if>
								<a
									href="<%=request.getContextPath()%>/charts?code=continent&name=${continent.name}"><button
										type="button" class="btn btn-primary btn-sm"><fmt:message>chart</fmt:message></button></a>
								<hr>

							</c:forEach>

						</div>

					</div>

				</div>
				<div class="col-md-3 p-0">
					<div class="card" style="height: 90vh; overflow: auto">
						<div class="card-header"><fmt:message>country</fmt:message></div>
						<div class="card-body">
							<span class="badge badge-primary"><fmt:message>confirmed</fmt:message></span> <span
								class="badge badge-success"><fmt:message>recovered</fmt:message></span> <span
								class="badge badge-danger"><fmt:message>deaths</fmt:message></span>
							<hr>
							<c:forEach var="country" items="${country}">
								<span class="badge badge-warning">${country.name }:</span>
								<br>
								<span class="badge badge-primary"> ${country.confirmed }</span>

								<span class="badge badge-success"> ${country.recovered }</span>

								<span class="badge badge-danger">${country.deaths }</span>
								<br>
								<c:if test="${islogin}">
									<a
										href="statistic?action=update&id=<c:out value='${country.id}' />"><button
											type="button" class="btn btn-primary btn-sm"><fmt:message>update</fmt:message></button></a>
								</c:if>
								<a
									href="<%=request.getContextPath()%>/charts?code=country&name=${country.name}"><button
										type="button" class="btn btn-primary btn-sm"><fmt:message>chart</fmt:message></button></a>
								<hr>

							</c:forEach>

						</div>

					</div>

				</div>
				<div class="col-md-3 p-0">
					<div class="card"
						style="height: 90vh; overflow: auto; scrollbar-width: thin">
						<div class="card-header"><fmt:message>city</fmt:message></div>
						<div class="card-body">
							<span class="badge badge-primary"><fmt:message>confirmed</fmt:message></span> <span
								class="badge badge-success"><fmt:message>recovered</fmt:message></span> <span
								class="badge badge-danger"><fmt:message>deaths</fmt:message></span>
							<hr>
							<c:forEach var="city" items="${city}">
								<span class="badge badge-warning">${city.name }</span>
								<br>
								<span class="badge badge-primary">${city.confirmed }</span>

								<span class="badge badge-success">${city.recovered }</span>

								<span class="badge badge-danger">${city.deaths }</span>
								<br>
								<c:if test="${islogin}">
									<a
										href="statistic?action=update&id=<c:out value='${city.id}' />"><button
											type="button" class="btn btn-primary btn-sm"><fmt:message>update</fmt:message></button></a>
								</c:if>
								<a
									href="<%=request.getContextPath()%>/charts?code=city&name=${city.name}"><button
										type="button" class="btn btn-primary btn-sm"><fmt:message>chart</fmt:message></button></a>
								<hr>

							</c:forEach>

						</div>

					</div>

				</div>

			</div>
		</div>


		<div class="col-md-2 p-0" style="height: 90vh; overflow: auto">
			<c:if test="${feed==null }">
				<div class="card-body"><fmt:message>not found</fmt:message></div>
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