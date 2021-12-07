<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align:center;">Datos de la matricula</h1>
	<p>Anio: ${year}</p>

	<p>Alumno:  ${alumnoMat}</p>

	<p>Asignaturas:</p>
	<ul>
		<c:forEach items="${asignaturas}" var="asignatura">
			<li>
				<a href="./gestionAsignatura?idasignatura=${asignatura.getIdasignatura()}"> ${asignatura.getNombre()} ${asignatura.getCurso()}</a>
			</li>
		</c:forEach>   
	</ul>
	<br>
	<p><a href="users/editarMatricula.jsp">Editar matricula</a></p>
	<br>
	<form name="formulario_borrar" action="./gestionMatricula" method="post">
		<input id="boton_borrar" name="botonMatricula" type="submit" value="Borrar matricula" style="width:20%"/>
	</form>
</body>
</html>