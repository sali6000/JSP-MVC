<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location de voitures</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<style>
#background
{
    background-size: auto;
    background-image: url("https://www.10wallpaper.com/wallpaper/1366x768/1705/2017_Skoda_visio_e-Car_Poster_HD_Wallpaper_1366x768.jpg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: top;
    background-size: cover;
    height: 100%;
}

#presentation
{
background-color: rgba(255, 255, 255, 0.6);
border-radius: 10px 10px 10px 10px;
margin-top:10%;
margin-bottom:10%;
padding-top:1%;
padding-bottom:1%;
}
</style>

<body id="background">
<jsp:include page="Content/NavBar.jsp"></jsp:include>

<div id="presentation" align="center">
<h1>Location de voitures - Belgique</h1>
<br/>
<h3>Trouvons ensemble la voiture idéale pour vos déplacements...</h3>
<br/>
<h2><a href="<%=request.getContextPath() + "/ReservationCondition"%>">Effectuer une réservation</a></h2>
</div>

</body>
</html>