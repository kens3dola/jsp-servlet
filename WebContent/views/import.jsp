<%@page
	import="com.se2.model.FeedMessage,com.se2.model.Feed,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="current" value="${param.ddLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="com/se2/resources/message" scope="session"/>

<html>
<head>
<meta charset="ISO-8859-1">
<title><fmt:message>continent statistic</fmt:message></title>
<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false : (boolean) session.getAttribute("islogin");
%>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container col-md-8 col-md-offset-3">
		<h3>Import statistic from data API</h3>
		<form class="form p-5" action="<%=request.getContextPath()%>/import?code=world"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>World:</label><input
					class="form-control" type="text" name="api" value="https://disease.sh/v2/historical/all?lastdays=30">
			</div>
			<button type="submit" class="btn btn-primary"><fmt:message>submit</fmt:message></button>
		</form>
		
		<form class="form p-5" action="<%=request.getContextPath()%>/import?code=continent"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Continents: </label><input
					class="form-control" type="text" name="api" value="https://disease.sh/v2/continents">
			</div>
			<button type="submit" class="btn btn-primary"><fmt:message>submit</fmt:message></button>
		</form>
		<form class="form p-5" action="<%=request.getContextPath()%>/import?code=country"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Countries:</label><input
					class="form-control" type="text" name="api" value="https://disease.sh/v2/jhucsse">
			</div>
			<button type="submit" class="btn btn-primary"><fmt:message>submit</fmt:message></button>
		</form>
		<form class="form p-5" action="<%=request.getContextPath()%>/import?code=vietnam"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>VietNam:</label><input
					class="form-control" type="text" name="api" value="https://disease.sh/v2/historical/Vietnam?lastdays=30">
			</div>
			<button type="submit" class="btn btn-primary"><fmt:message>submit</fmt:message></button>
		</form>
		
	</div>
	<br>
	<hr>
	<br>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>