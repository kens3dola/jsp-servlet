<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- Bootstrap core CSS -->
<link
	href="<c:url value = "/style/vendor/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false
			: (boolean) session.getAttribute("islogin");
%>

<!-- Custom styles for this template -->
<link href="<c:url value = "/style/css/simple-sidebar.css"/>"
	rel="stylesheet">
<div class="d-flex" id="wrapper">
	<c:if test="${islogin}">
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">Statistics managing</div>
			<div class="list-group list-group-flush">
				<a href="<%=request.getContextPath()%>/world"
					class="list-group-item list-group-item-action bg-light">World</a> <a
					href="<%=request.getContextPath()%>/continent"
					class="list-group-item list-group-item-action bg-light">Continent</a>
				<a href="<%=request.getContextPath()%>/country"
					class="list-group-item list-group-item-action bg-light">Country</a>
				<a href="<%=request.getContextPath()%>/city"
					class="list-group-item list-group-item-action bg-light">City</a>
			</div>
		</div>
	</c:if>

	<div id="page-content-wrapper">

		<nav
			class="navbar navbar-expand-lg navbar-light bg-light border-bottom">

			<c:if test="${islogin}">
				<button class="btn btn-primary" id="menu-toggle">
					<i class="fa fas fa-bars"></i>
				</button>
			</c:if>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
					<li class="nav-item active"><a class="nav-link"
						href="<%=request.getContextPath()%>/home">Home <span
							class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#">Report</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Account </a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdown">
							<c:if test="${!islogin}">
								<a href="<%=request.getContextPath()%>/login" class="nav-link">Login</a>
							</c:if>
							<c:if test="${islogin}">
								<a href="<%=request.getContextPath()%>/changepassword"
									class="nav-link">Change password</a>
								<a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a>
							</c:if>
						</div></li>
				</ul>
			</div>
		</nav>

		<div class="container-fluid">