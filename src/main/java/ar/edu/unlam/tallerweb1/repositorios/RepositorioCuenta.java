package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Transaccion;

public interface RepositorioCuenta {

	Cuenta consultarCuenta(Cuenta cuenta);
	void guardar(Cuenta cuenta);
	List<Cuenta> traerCuentas();
	Cuenta consultarCuentaPorUsuarioId(Long uid);
	Cuenta consultarCuentaPorNum(Long num);
	void guardarTransaccion(Transaccion transaccion);
}
