<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<header>
	<nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
		<ul class="navbar-nav navbar-collapse justify-content-start">
				<li><a href="<%=request.getContextPath()%>/index.jsp"
					class="nav-link">Home</a></li>
					</ul>
		<ul class="navbar-nav navbar-collapse justify-content-end">
			<%
				boolean islogin = (session.getAttribute("islogin") == null) ? false
						: (boolean) session.getAttribute("islogin");
			%>
			<c:if test="${!islogin}">
				<li><a href="<%=request.getContextPath()%>/login"
					class="nav-link">Login</a></li>
			</c:if>
			<c:if test="${islogin}">
				<li><a href="<%=request.getContextPath()%>/changepassword"
					class="nav-link">Change password</a></li>
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</c:if>
		</ul>
	</nav>
</header>