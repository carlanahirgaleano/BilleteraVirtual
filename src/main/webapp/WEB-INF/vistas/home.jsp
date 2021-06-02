<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Se lo tuve que agregar para que funcione, sino me tiraba un error de command -->
<jsp:useBean id="command" class="ar.edu.unlam.tallerweb1.modelo.Cuenta" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
	<head>
		<title>Inicio Cuenta de Banco</title>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<div class="row">
				<h1>Cuenta de banco</h1>
			</div>
			<div class="row">
				<div class="col-md-2">
					<a href="transferir?cid=${cuenta.num}">Transferir Dinero</a>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="row">
						<h3>Número de cuenta: ${cuenta.num}</h3>
					</div>
					<div class="row">
						<h4>Alias: ${cuenta.alias}</h4>
					</div>
					<div class="row">
						<h4>Moneda: ${cuenta.moneda}</h4>
					</div>
				</div>
				<div class="col-md-3 card bg-info text-center" style="height:150px;">
					<div class="card-title ">
						<h3>Saldo</h3>
					</div>
					<div class="card-body">
						<h4>${cuenta.saldo}</h4> 
					</div>
				</div>
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