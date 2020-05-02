<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>country Management Application</title>
</head>
<body>
	<jsp:include page="../../common/header.jsp"></jsp:include>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${country != null}">
					<form action="country?action=update&id=${country.id}" method="post">
				</c:if>
				<c:if test="${country == null}">
					<form action="country?action=new" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${country != null}">
            			Edit country
            		</c:if>
						<c:if test="${country == null}">
            			Add New country
            		</c:if>
					</h2>
				</caption>
				<c:if test="${country != null}">
             <input type="hidden" name="id" value="<c:out value='${country.id}' />" />
                </c:if>
				

				<fieldset class="form-group">
					<label>name</label> <input type="text"
						value="<c:out value='${country.name}' />" class="form-control"
						name="name" required="required" >
				</fieldset>

				<fieldset class="form-group">
					<label>confirmed</label> <input type="number"
						value="<c:out value='${country.confirmed}' />" class="form-control"
						name="confirmed">
				</fieldset>

				<fieldset class="form-group">
					<label>recovered</label> <input type="number"
						value="<c:out value='${country.recovered}' />" class="form-control"
						name="recovered">
				</fieldset>
				<fieldset class="form-group">
					<label>deaths</label> <input type="number"
						value="<c:out value='${country.deaths}' />" class="form-control"
						name="deaths">
				</fieldset>
                                                
                                <fieldset class="form-group">
					<label>continent_id</label> <input type="number"
						value="<c:out value='${country.continent_id}' />" class="form-control"
						name="continent_id">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>
</html>