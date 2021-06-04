package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Transaccion;

public interface ServicioCuenta {

	List<Cuenta> traerTodos();
	Cuenta consultarCuentaPorUsuarioId(Long uid);
	boolean depositar(Cuenta cuenta);
	void transferir(Transaccion transaccion);
	
}
