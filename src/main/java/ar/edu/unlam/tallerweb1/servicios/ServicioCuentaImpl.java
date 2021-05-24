package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
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
				cuentaExistente.setSaldo(cuentaExistente.getSaldo() + cuenta.getMonto());
				cuentaExistente.setMonto(cuenta.getMonto());
				servicioCuentaDao.guardar(cuentaExistente);
			}else {
				if(cuenta.getNum() != 0) {
					return false;
				}
				servicioCuentaDao.guardar(cuenta);
			}
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}


}
