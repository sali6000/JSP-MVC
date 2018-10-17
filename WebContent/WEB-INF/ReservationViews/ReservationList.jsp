<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.og.model.Reservation"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Liste de réservations</title>
</head>
<body>

	<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>

	<!-- Table contenant les données de réservations -->
	<table class="table">
		<tr>
			<td></td>
			<td>Date de réservation</td>
			<td>Date de retour</td>
			<td>Prix</td>
			<td>Prenom client</td>
			<td>Nom client</td>
			<td>Vehicule</td>
			<td>Lieu de réservation</td>
			<td>Lieu de retour</td>
			<td></td>
		</tr>
		<c:forEach var="reservation" items="${Reservations}">
			<tr>
				<td></td>
				<td><fmt:formatDate
						value="${reservation.getDate_de_reservation()}"
						pattern="dd-MM-yyyy" /></td>
				<td><fmt:formatDate value="${reservation.getDate_de_retour()}"
						pattern="dd-MM-yyyy" /></td>
				<td>${reservation.getPrix()}</td>
				<td>${reservation.getPersonne().getPrenom()}</td>
				<td>${reservation.getPersonne().getNom()}</td>
				<td>${reservation.getVoitures().get(0).getNom()}</td>
				<td>${reservation.getAgences().get(1).getAdresse().getVille()}</td>
				<td>${reservation.getAgences().get(0).getAdresse().getVille()}</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>