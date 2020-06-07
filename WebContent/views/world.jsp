<%@page
	import="com.se2.model.FeedMessage,com.se2.model.Feed,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<c:set var="current" value="${param.ddLanguage}" scope="session"/>
<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="com/se2/resources/message" scope="session"/>

<html>
<head>
<meta charset="ISO-8859-1">
<title><%= request.getParameter("name").toUpperCase()%> <fmt:message>statistic</fmt:message></title>
<%
	boolean islogin = (session.getAttribute("islogin") == null) ? false : (boolean) session.getAttribute("islogin");
%>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="row">
		<canvas id="myChart" style="height:80vh; width:60vw; margin:auto" aria-label="Hello ARIA World"></canvas>
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
		<script>
		var href = window.location.href.split("?")[1];
			var xhttp = new XMLHttpRequest();
			var data = null;
			xhttp.open("GET",
					"http://localhost:8080/jsp-servlet/statistics?"+href,
					"json", true)
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					data = JSON.parse(this.response).statistics;
					var name = data[0].name;
					var labels = [];
					var confirmed = [];
					var recovered = [];
					var deaths = [];
					function parseData(value) {
						console.log(value);
						labels.push(value.date);
						confirmed.push(value.confirmed);
						recovered.push(value.recovered);
						deaths.push(value.deaths);
					}
					data.forEach(parseData);
					var ctx = document.getElementById('myChart').getContext(
							'2d');
					var myChart = new Chart(ctx, {
						type : 'line',
						data : {
							labels : labels,
							datasets : [ {
								label : "Confirmed",
								data : confirmed,
								borderColor : "#044BFC",
								fill : false
							}, {
								label : "Recovered",
								data : recovered,
								borderColor : "#1DDF02",
								fill : false
							}, {
								label : "Deaths",
								data : deaths,
								borderColor : "#FC3004",
								fill : false
							} ]
						},
						options : {
							title : {
								display : true,
								text : '<%= request.getParameter("name").toUpperCase()%> statistics'
							},
							responsive: false
						}
					});
				}
			}
			xhttp.send();
		</script>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>