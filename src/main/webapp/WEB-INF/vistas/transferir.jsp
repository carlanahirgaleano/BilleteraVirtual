<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Se lo tuve que agregar para que funcione, sino me tiraba un error de command -->
<jsp:useBean id="command" class="ar.edu.unlam.tallerweb1.modelo.Transaccion" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Módulo Transferencias</title>
<!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet" >
<!-- Bootstrap theme -->
  <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div>
			<h1>Módulo de Transferencias</h1>
		</div>
		<div>
			<form:form action="transferencia" method="POST" modalAttribute="transaccion"> 
				<div class="form-group">
					<label>Cuenta Origen</label>
					<form:input path="num_cuenta_origen" value="${transaccion.num_cuenta_origen}"  class="form-control" disabled="true" />
				</div>
				<div class="form-group">
					<label for="destinatario">Cuenta Destino:</label>
					<form:select path="num_cuenta_destino" id="destinatario" class="form-control">
						<form:options items="${listaCuentas}" />
					</form:select>
				</div>
				<div class="form-group">
					<label for="monto">Monto:</label>
					<form:input path="monto" type="number" id="monto" step="0.01" class="form-control" />
				</div>
				<div class="form-group d-flex">
					<button Type="Submit" class="btn btn-primary">Realizar Transferencia</button>
				</div>
			</form:form>
		</div>
		<div>
			<a href="home?uid=${transaccion.num_cuenta_origen}">Volver al home</a>
		</div>
	</div>
	
	<c:if test="${not empty error}">
		<c:if test="${error == true}">
	     	 <div class="alert alert-danger" role="alert">
			  	${mensaje}
			 </div>
		</c:if>
		<c:if test="${error == false}">
			 <div class="alert alert-success" role="alert">
			  	${mensaje}
			 </div>
		</c:if>
    </c:if>	

	
	<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js?v=1" type="text/javascript"></script>
</body>
</html>