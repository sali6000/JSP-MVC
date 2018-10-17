<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.og.model.Voiture"%>
<%@ page import="com.og.model.Agence"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Détail de réservation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>
	<div align="center">
		<h3>L'agence située à <%= ((Agence)session.getAttribute("agenceDeDepart")).getAdresse().getVille() %> -
			<%= ((Agence)session.getAttribute("agenceDeDepart")).getRue() %> possède actuellement les voitures suivantes</h3>
	</div>
	<br />
	<c:choose>
	<c:when test="${voitures.size() > 0 }">
	<table class="table">
		<tr>
			<td>Nom</td>
			<td>Année</td>
			<td>Boite de vitesse</td>
			<td>Carburant</td>
			<td>Categorie</td>
			<td>Prix</td>
			<td>Action</td>
			<td></td>
		</tr>
		<c:forEach var="voiture" items="${voitures}">
			<tr>
				<td>${voiture.getNom()}</td>
				<td>${voiture.getAnnee()}</td>
				<td>${voiture.getBoite_vitesse() == 1? 'Automatic':'Manuel'}</td>
				<td>${voiture.getCarburant().getNom()}</td>
				<td>${voiture.getCategorie().getNom()}</td>
				<td>${Double.parseDouble(voiture.getPrix().toString())} euros/Jour</td>
				<td>
					<form action="<%= request.getContextPath() %>/ReservationOptionVoiture" method="get">
						<button type="submit" class="btn btn-info" name="idVehicule" value="${voiture.getIdentifiant() }">Louer ce véhicule</button>
					</form>
				</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
		<br /><br /><br /><div align="center"><h4> Aucune voitures n'est disponibles pour cette agence</h4></div>
	</c:otherwise>
	</c:choose>
</body>
</html>