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
			</div>
			<div class="row">
				<form:form action="depositar" method="POST" modalAttribute="cuenta">
					<div class="form-group row">
						<div class="col-md-6">
							<label for="saldo">Número Cuenta</label>
							<form:input path="num" type="text" id="num" class="form-control" />
						</div>
						<div class="col-md-6">
							<label for="monto">Monto</label>
							<form:input path="monto" type="text" id="monto" class="form-control" />
						</div>
					</div>
					<button type="Submit" class="btn btn-secondary">Depositar</button>
				</form:form>
			</div>
			
			<c:if test="${not empty error}">
		        <h4><span>${message}</span></h4>
		        <br>
	        </c:if>	
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>