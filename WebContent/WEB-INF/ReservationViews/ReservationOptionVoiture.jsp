<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.og.model.OptionVoiture"%>
<%@ page import="com.og.model.Voiture"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choix des options</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>

	<div align="center">
		<h3>
			Choix des options pour la
			<%=((Voiture) session.getAttribute("voiture")).getNom()%></h3>
		<br />
		
		<form action="<%=request.getContextPath()%>/ReservationOptionReservation"
			method="get">
		<c:choose>
		<c:when test="${optionsVoiture.size() > 0 }">
		<h3>Les options disponibles:</h3>
		<br />
			<table class="table">
				<tr>
					<td></td>
					<td>Nom de l'option</td>
					<td>Prix de l'option</td>
					<td>Quantité disponible</td>
					<td>Indiquez le nombre d'options souhaitées</td>
					<td></td>
				</tr>
				
				<!-- Obligé d'utilisé des caractères d'échappement dans mon cas sinon confusions avec les "..." -->
				<c:forEach var="option" items="<%= session.getAttribute(\"optionsVoiture\") %>">
					<tr>
						<td></td>
						<td>${option.getNom()}</td>
						<td>${option.getPrix()} euro/jour</td>
						<!-- L'erreur située sur la ligne en dessous provient d'éclipse mais ne cause aucun problèmes -->
						<td>${quantiteOptionsVoiture[optionsVoiture.indexOf(option)] }</td>
						<td><input type="number" name="quantity${option.getIdentifiant()}" placeholder="0" min="0" max="${quantiteOptionsVoiture[optionsVoiture.indexOf(option)]}"></td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
			<br />
		</c:when>
		<c:otherwise>
		<br /><br /><br /><h4>Aucunes option n'est disponible actuellement pour cette voiture</h4><br />
		</c:otherwise>
		</c:choose>
			<button type="submit" class="btn btn-info">Passé à l'option de réservation</button>
		</form>
	</div>
</body>
</html>