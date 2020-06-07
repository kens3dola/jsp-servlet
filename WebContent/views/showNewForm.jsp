<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="current" value="${param.ddLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="com/se2/resources/message" scope="session"/>

<html>
<head>
<title><fmt:message>statistic Management Application</fmt:message></title>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			
				<c:if test="${statistic != null}">
					<form action="statistic?action=update" method="post">
				</c:if>
	
				<c:if test="${statistic == null}">
					<form action="statistic?action=new" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${statistic!= null}">
            			<fmt:message>edit statistics</fmt:message>
            		</c:if>
						<c:if test="${statistic == null}">
            			<fmt:message>add statistics</fmt:message>
            		</c:if>
					</h2>
				</caption>
				
				

				 <c:if test="${statistic != null}">
					<input value="<c:out value='${statistic.code}'/>"class="form-control"  name ="code"  type="hidden"/>

				</c:if>
				 <c:if test="${statistic == null}">
					<fieldset class="form-group">
					<label type="hidden">Code</label> 
					<select class="custom-select custom-select-sm" name="code">
  					<option selected>Select Code</option>
  					<option value="continent" name="code"><fmt:message>continent</fmt:message></option>
  					<option value="country" name="code"><fmt:message>country</fmt:message></option>
  					<option value="city" name="code"><fmt:message>city</fmt:message></option>
					</select>
				</fieldset>
			
				</c:if>
				
				
				<fieldset class="form-group">
					<label type="hidden"><fmt:message>name</fmt:message></label> <input type="text"
						value="<c:out value='${statistic.name}' />" class="form-control"
						name="name" required="required">
						</input>
				</fieldset>

				<fieldset class="form-group">
					<label><fmt:message>confirmed</fmt:message></label> <input type="number"
						value="<c:out value='${statistic.confirmed}' />" class="form-control"
						name="confirmed">
				</fieldset>

				<fieldset class="form-group">
					<label><fmt:message>recovered</fmt:message></label> <input type="number"
						value="<c:out value='${statistic.recovered}' />" class="form-control"
						name="recovered">
				</fieldset>
				<fieldset class="form-group">
					<label><fmt:message>deaths</fmt:message></label> <input type="number"
						value="<c:out value='${statistic.deaths}' />" class="form-control"
						name="deaths">
				</fieldset>
				
				<button type="submit" class="btn btn-success"><fmt:message>save</fmt:message></button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>