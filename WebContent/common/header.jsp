<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<c:set var="current" value="${param.ddLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="com/se2/resources/message" scope="session"/>

<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false : (boolean) session.getAttribute("islogin");
%>

<!-- Custom styles for this
 template -->
<link href="<c:url value = "/style/css/simple-sidebar.css"/>"
	rel="stylesheet">
<div class="d-flex toggled" id="wrapper">
	<c:if test="${islogin}">
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading"><fmt:message>statistic managing</fmt:message></div>
			<div class="list-group list-group-flush">
				<a href="" class="list-group-item list-group-item-action bg-light"><fmt:message>world</fmt:message></a>
				<a href="" class="list-group-item list-group-item-action bg-light"><fmt:message>continent</fmt:message></a>
				<a href="" class="list-group-item list-group-item-action bg-light"><fmt:message>country</fmt:message></a>
				<a href="" class="list-group-item list-group-item-action bg-light"><fmt:message>city</fmt:message></a>
				<a href="statistic?action=new"
					class="list-group-item list-group-item-action bg-light"><button
						type="sucess" class="btn btn-success"><fmt:message>add statistics</fmt:message></button></a>

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
                                    <form action="#" style="display:flex; align-item:center'" method="POST">
                                        <select name="ddLanguage">
                                            <option value="vi_VN">Tiếng Việt</option>
                                            <option value="en_US">English</option>
                                        </select>
                                        <input type="submit" class="" value="<fmt:message>submit</fmt:message>"/>
                                    </form>					
                                    <li class="nav-item active"><a class="nav-link"
						href="<%=request.getContextPath()%>/home"><fmt:message>home</fmt:message> <span
							class="sr-only">(current)</span></a></li>
					<c:if test="${islogin}">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/import"><fmt:message>import</fmt:message></a></li>
					</c:if>
					<li class="nav-item active"><c:if test="${!islogin}">
							<a href="<%=request.getContextPath()%>/login" class="nav-link"><fmt:message>login</fmt:message></a>
						</c:if> <c:if test="${islogin}">
							<a href="<%=request.getContextPath()%>/logout" class="nav-link"><fmt:message>logout</fmt:message></a>
						</c:if></li>
				</ul>
			</div>
		</nav>

		<div class="container-fluid">