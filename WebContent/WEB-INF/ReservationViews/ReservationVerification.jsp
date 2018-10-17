<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.og.model.OptionReservation"%>
<%@ page import="com.og.model.Voiture"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Option de réservation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>

	<div align="center">
		<h3>
			Vous avez donc choisit une
			<%=((Voiture) session.getAttribute("voiture")).getNom()%></h3>
		<br />

		<h3>Avec les options de voiture suivantes :</h3>

		<table class="table">
			<tr>
				<td></td>
				<td>Nom de l'option</td>
				<td>Prix de l'option</td>
				<td>Quantitée</td>
				<td></td>
			</tr>

			<!-- Je parcours la liste des options disponibles et la quantité d'option choisies par le client
			     Et je n'affiche que les options choisies par le client -->
			<c:forEach var="option"
				items="<%=session.getAttribute(\"optionsVoiture\")%>"
				varStatus="iteratorOption">
				<c:forEach var="optionQuantite"
					items="<%=session.getAttribute(\"quantiteOptionsVoiture\")%>"
					varStatus="iteratorQuantity">
					<c:if
						test="${(iteratorOption.index == iteratorQuantity.index) && (optionQuantite > 0) }">
						<tr>
							<td></td>
							<td>${option.getNom()}</td>
							<td>${option.getPrix()}</td>
							<!-- L'erreur située sur la ligne en dessous provient d'éclipse mais ne cause aucun problèmes -->
							<td>${optionQuantite}</td>
							<td></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>
		<br />
		<h3>Avec les options de réservations suivants :</h3>

		<!-- Option de réservation -->

		<table class="table">
			<tr>
				<td></td>
				<td>Nom de l'option</td>
				<td>Prix de l'option</td>
				<td></td>
			</tr>

			<!-- Je parcours la liste des options disponibles et la quantité d'option choisies par le client
			     Et je n'affiche que les options choisies par le client -->
			<c:forEach var="option"
				items="<%=session.getAttribute(\"optionsReservation\")%>"
				varStatus="iteratorOption">
				<c:forEach var="optionQuantite"
					items="<%=session.getAttribute(\"quantiteOptionsReservation\")%>"
					varStatus="iteratorQuantity">
					<c:if
						test="${(iteratorOption.index == iteratorQuantity.index) && (optionQuantite.equals(\"Oui\"))}">
						<tr>
							<td></td>
							<td>${option.getNom()}</td>
							<td>${option.getPrix()}</td>
							<td></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>

		<form action="<%=request.getContextPath()%>/ReservationValidation"
			method="get">
			<input class="btn btn-info" type="submit"
				value="Valider la sélection et passé à l'authentification" />
		</form>
	</div>
</body>
</html>