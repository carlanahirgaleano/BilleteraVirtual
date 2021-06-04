package ar.edu.unlam.tallerweb1.servicios;



import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParser;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Transaccion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCuenta;



@Service("servicioCuenta")
@Transactional
public class ServicioCuentaImpl implements ServicioCuenta{

	private RepositorioCuenta servicioCuentaDao;
	
	@Autowired
	public ServicioCuentaImpl(RepositorioCuenta servicioCuentaDao){
		this.servicioCuentaDao = servicioCuentaDao;
	}
	
	@Override
	public boolean depositar(Cuenta cuenta) {
		
		try {
			Cuenta cuentaExistente = servicioCuentaDao.consultarCuenta(cuenta);
			if(cuentaExistente != null) {
				cuentaExistente.setMoneda(cuenta.getMoneda());
//				cuentaExistente.setSaldo(cuentaExistente.getSaldo() + cuenta.getSaldo());
				//cuentaExistente.setMonto(cuenta.getMonto());
				cuentaExistente = regularSaldo(cuentaExistente);
				servicioCuentaDao.guardar(cuentaExistente);
			}else {
				if(cuenta.getNum() != 0) {
					return false;
				}
				cuenta = regularSaldo(cuenta);
				servicioCuentaDao.guardar(cuenta);
			}
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}
	
	public Cuenta regularSaldo(Cuenta cuenta) {
		
		if(cuenta.getMoneda().equals("Peso ARG")) {
			//cuenta.setSaldo(cuenta.getSaldo() + cuenta.getMonto());
		}
		
		if(cuenta.getMoneda().equals("Dólar")) {
			//cuenta.setSaldo(cuenta.getSaldo() + precioDolarHoy(cuenta.getMonto()));
		}
		
		return cuenta;
		
	}
	
	public double precioDolarHoy(double monto) {
		
//		Se agrego la dependencia json de maven en el pom.xml
//		se utilizo la api dolarsi para saber el valor del dólar actualizado
		
		double precio = 0.0;
		
		HttpClient httpClient = HttpClient.newBuilder()		
				.build();

		String uri_api = "https://www.dolarsi.com/api/api.php?type=valoresprincipales";
		
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(uri_api))
				.build();
		
		try {
			
			HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
			String rta = response.body().toString();
									
			JSONArray arr = new JSONArray(rta);
			
//			for(int i = 0; i < arr.length(); i++) {}
				
				JSONObject json = arr.getJSONObject(0);
				
				Object objJson = json.get("casa");
				
				JSONObject valoresJson = (JSONObject)objJson;
				
				precio = Double.parseDouble(valoresJson.get("venta").toString().replace(",", "."));
						
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		
		precio = monto * precio;
		
		
		DecimalFormat formato = new DecimalFormat("#.000");
		precio = Double.parseDouble(formato.format(precio).replace(",", "."));
		
		return precio;
	}


	@Override
	public List<Cuenta> traerTodos() {

		return servicioCuentaDao.traerCuentas();
	}

	@Override
	public Cuenta consultarCuentaPorUsuarioId(Long uid) {
		
		return servicioCuentaDao.consultarCuentaPorUsuarioId(uid);
	}

	@Override
	public void transferir(Transaccion transaccion) {
		
		try {
		
			//Resto el saldo de la cuenta origen
			Cuenta cuentaOrigen = servicioCuentaDao.consultarCuentaPorNum(transaccion.getNum_cuenta_origen());
			cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - transaccion.getMonto());
			
			//Sumo el saldo a la cuenta destino
			Cuenta cuentaDestino = servicioCuentaDao.consultarCuentaPorNum(transaccion.getNum_cuenta_destino());
			cuentaDestino.setSaldo(cuentaDestino.getSaldo() + transaccion.getMonto());
			
			//Guardo la transaccion
			servicioCuentaDao.guardarTransaccion(transaccion);
			
			//Guardo las cuentas
			servicioCuentaDao.guardar(cuentaOrigen);
			servicioCuentaDao.guardar(cuentaDestino);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}


}
