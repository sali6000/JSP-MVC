<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.og.model.Agence"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>Reservation</title>
</head>
<body>
	<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>

	<div align="center">
		<br />
		<h2>Formulaire de réservation</h2>
		<br /> <br />
		<c:if test="${errorDate == true}">
			<div class="alert alert-danger">
				<strong>Attention !</strong> La date de début de réservation doit être inférieur à la date de retour
			</div>
		</c:if>
		<form action="<%=request.getContextPath()%>/ReservationDisponibilite"
			method="get">
			<h4>Ville de départ:</h4>
			<select name="Depart" required>
				<option value="">-- Sélectionnez une ville pour le départ --</option>
				<c:forEach items="${agences}" var="agence">
					<option value="${agence.getIdentifiant()}">${agence.getAdresse().getVille()} - ${agence.getRue()}</option>
				</c:forEach>
			</select> <br /> <br />
			<h4>Lieu de retour:</h4>
			<select name="Retour" required>
				<option value="">-- Sélectionnez une ville pour le retour --</option>
				<c:forEach items="${agences}" var="agence">
					<option value="${agence.getIdentifiant()}">${agence.getAdresse().getVille()} - ${agence.getRue()}</option>
				</c:forEach>
			</select><br /> <br />
			<h4>Date de début de la réservation:</h4>
			<input type="text" name="Debut"
				placeholder="Selectionnez une date..." id="dateDeb" required readonly>
			<br /> <br />
			<h4>Date de fin de la réservation:</h4>
			<input type="text" name="Fin" placeholder="Selectionnez une date..."
				id="dateFin" required readonly> <br /> <br /> <br /> 
				<input class="btn btn-info" type="submit" value="Valider et voir les véhicules disponibles" />
		</form>
	</div>
</body>

<!-- Chargement des scripts pour le calendrier Jquery -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#dateDeb").datepicker({
			minDate : +0,
			maxDate : "+6M"
		});
		$("#dateFin").datepicker({
			minDate : +0,
			maxDate : "+6M"
		});
	});
</script>
</head>
</html>