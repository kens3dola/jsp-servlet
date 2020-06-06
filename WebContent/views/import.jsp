<%@page
	import="com.se2.model.FeedMessage,com.se2.model.Feed,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Continent statistic</title>
<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false : (boolean) session.getAttribute("islogin");
%>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container col-md-8 col-md-offset-3">
		<form class="form p-5" action="<%=request.getContextPath()%>/import"
			method="post" enctype="multipart/form-data">
			<div class="form-grpup">
				<label>Data API (https://disease.sh/v2/jhucsse): </label><input
					class="form-control" type="text" name="api">
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<br>
	<hr>
	<br>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>