<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>continent Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> continent Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listContinent"
					class="nav-link">continents</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${continent != null}">
					<form action="updateContinent" method="post">
				</c:if>
				<c:if test="${continent == null}">
					<form action="insertContinent" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${continent != null}">
            			Edit continent
            		</c:if>
						<c:if test="${continent == null}">
            			Add New continent
            		</c:if>
					</h2>
				</caption>
				<c:if test="${continent != null}">
             <input type="hidden" name="id" value="<c:out value='${continent.id}' />" />
                </c:if>
				

				<fieldset class="form-group">
					<label>name</label> <input type="text"
						value="<c:out value='${continent.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>confirmed</label> <input type="text"
						value="<c:out value='${continent.confirmed}' />" class="form-control"
						name="confirmed">
				</fieldset>

				<fieldset class="form-group">
					<label>recovered</label> <input type="text"
						value="<c:out value='${continent.recovered}' />" class="form-control"
						name="recovered">
				</fieldset>
				<fieldset class="form-group">
					<label>deaths</label> <input type="text"
						value="<c:out value='${continent.deaths}' />" class="form-control"
						name="deaths">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>