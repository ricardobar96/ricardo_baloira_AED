<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="author" content="ricardo baloira"/>
<title>Insert title here</title>
</head>
<body>
	<h1 class="cabecera">JUGAR</h1>
	<form action="Principal" method="GET"> 
		<label for="apuesta">Apuesta: </label>
	   	<input type="text" name="apuesta" id="apuesta" required>
	   	<br>
	   	<br>
	   	<input type="submit" value="Apostar">
    </form>	
    <br>
    <p>Usuario: ${jugador}</p>
    <p>Hora del actual secreto: ${horaSecreto}</p>
    <p>Secreto actual: ${secreto}</p>
    <br>
    <c:forEach items="${apuestas}" var="intento">
	   <p>Secreto ${intento.comparacion} ${intento.numero}</p>   
	</c:forEach> 
</body>
</html>