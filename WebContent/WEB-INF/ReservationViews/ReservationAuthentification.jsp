<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.og.model.Personne"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentification</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>
	<div align="center">
		<c:forEach var="personne" items="${personnes}">

			<form
				action="<%=request.getContextPath()%>/ReservationValidation"
				method="get">
				<button type="submit" class="btn btn-info" name="idPersonne"
					value="${personne.getIdentifiant() }">${personne.getNom()}</button>
			</form>
		</c:forEach>
	</div>
</body>
</html>