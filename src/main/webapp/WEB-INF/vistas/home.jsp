<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Se lo tuve que agregar para que funcione, sino me tiraba un error de command -->
<jsp:useBean id="command" class="ar.edu.unlam.tallerweb1.modelo.Cuenta" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<div class="row">
				<h1>Cuenta de banco</h1>
				<h6>--Déje el número de cuenta en 0 para crear una nueva--</h6>
			</div>
			<div class="row">
				<form:form action="depositar" method="POST" modalAttribute="cuenta">
					<div class="form-group row">
						<div class="col-md-4">
							<label for="saldo">Número Cuenta</label>
							<form:input path="num" type="number" id="num" class="form-control" />
						</div>
						<div class="col-md-4">
							<label for="moneda">Moneda</label>
							<form:select  path="moneda" class="form-control">
							    <form:options items="${listaMoneda}"></form:options>
						  	</form:select>
						</div>
						<div class="col-md-4">
							<label for="monto">Monto</label>
							<form:input path="monto" type="number" id="monto" class="form-control" step="0.01" />
						</div>
					</div>
					<button type="Submit" class="btn btn-secondary">Depositar</button>
				</form:form>
			</div>
			
			<c:if test="${not empty error}">
		        <h4>
		        	<c:if test="${error == true}">
		        		<span class="text-danger">${message}</span>
		        	</c:if>
		        	<c:if test="${error == false}">
		        		<span class="text-success">${message}</span>
		        	</c:if>
		        </h4>
		        <br>
	        </c:if>	
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>