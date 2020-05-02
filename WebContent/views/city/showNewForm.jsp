<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>city Management Application</title>
</head>
<body>
	<jsp:include page="../../common/header.jsp"></jsp:include>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${city != null}">
					<form action="city?action=update&id=${city.id}" method="post">
				</c:if>
				<c:if test="${city == null}">
					<form action="city?action=new" method="post">
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
					<label>confirmed</label> <input type="number"
						value="<c:out value='${city.confirmed}' />" class="form-control"
						name="confirmed">
				</fieldset>

				<fieldset class="form-group">
					<label>recovered</label> <input type="number"
						value="<c:out value='${city.recovered}' />" class="form-control"
						name="recovered">
				</fieldset>
				<fieldset class="form-group">
					<label>deaths</label> <input type="number"
						value="<c:out value='${city.deaths}' />" class="form-control"
						name="deaths">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>