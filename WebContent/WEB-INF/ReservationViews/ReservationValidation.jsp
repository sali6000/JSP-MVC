<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.og.model.Personne"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Validation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/Content/NavBar.jsp"></jsp:include>
<div align="center">
<br/>
<br/>
<h2>Votre réservation a bien été validée au prix de ${prix} euros Monsieur/Madame <%=((Personne) session.getAttribute("personne")).getNom()%> !</h2>
</div>
</body>
</html>