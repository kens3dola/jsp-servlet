<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>City Form</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> List City </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listCity"
					class="nav-link">City</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${city != null}">
					<form action="/city/update" method="post">
				</c:if>
				<c:if test="${city == null}">
					<form action="/city/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${city != null}">
            			Edit city
            		</c:if>
						<c:if test="${city == null}">
            			Add New city
            		</c:if>
					</h2>
				</caption>
				<c:if test="${city != null}">
             <input type="hidden" name="id" value="<c:out value='${city.id}' />" />
                </c:if>
				

				<fieldset class="form-group">
					<label>name</label> <input type="text"
						value="<c:out value='${city.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>confirmed</label> <input type="text"
						value="<c:out value='${city.confirmed}' />" class="form-control"
						name="confirmed">
				</fieldset>

				<fieldset class="form-group">
					<label>recovered</label> <input type="text"
						value="<c:out value='${city.recovered}' />" class="form-control"
						name="recovered">
				</fieldset>
				<fieldset class="form-group">
					<label>deaths</label> <input type="text"
						value="<c:out value='${city.deaths}' />" class="form-control"
						name="deaths">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>