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

		<h3>Avec les options suivantes :</h3>

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
							<td>${option.getPrix()} euro</td>
							<!-- L'erreur située sur la ligne en dessous provient d'éclipse mais ne cause aucun problèmes -->
							<td>${optionQuantite}</td>
							<td></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>
		<br />

		<!-- ASSURANCES RESERVATION -->

		<h4>Sélectionnez parmis les choix disponibles une ou des
			assurance(s) pour votre réservation</h4>

		<form
			action="<%=request.getContextPath()%>/ReservationVerification"
			method="get">
			<c:choose>
				<c:when test="${optionsReservation.size() > 0 }">
					<br />
					<table class="table">
						<tr>
							<td></td>
							<td>Nom de l'assurance</td>
							<td>Prix de l'assurance</td>
							<td>Cochez les options souhaitées</td>
							<td></td>
						</tr>


						<c:forEach var="option"
							items="<%=session.getAttribute(\"optionsReservation\")%>">
							<tr>
								<td></td>
								<td>${option.getNom()}</td>
								<td>${option.getPrix()}</td>
								<td><input type="checkbox"
									name="get${option.getIdentifiant()}"></td>
								<td></td>
							</tr>
						</c:forEach>
					</table>
					<br />
				</c:when>
				<c:otherwise>
					<br />
					<br />
					<br />
					<h4>Aucunes option n'est disponible actuellement pour cette
						réservation</h4>
					<br />
				</c:otherwise>
			</c:choose>
			<button type="submit" class="btn btn-info">Passé à
				l'enregistrement</button>
		</form>
		<br />
		<br />

	</div>
</body>
</html>