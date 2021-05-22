package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;

import org.springframework.web.servlet.ModelAndView;


public class ControladorHomeTest {

	private ServicioCuenta servicioCuenta;
	private ControladorHome controladorHome = new ControladorHome();
	private Cuenta cuenta = new Cuenta();
	private ModelAndView mav = new ModelAndView();
	
	@Test
	public void queNoMePermitaDepositarSiEsNumeroNegativo() {
		
		whenHagamosDepositoNegativo();
		
		thenNosDevuelvaError();
		
	}
	
	@Test
	public void queSalgaUnMensajeSiSePudoRealizarElDeposito() {
		
		whenHacemosDepositoValido();
		
		thenNosDevueltaMensajeExitoso();
		
	}
	
	@Test
	public void queSalgaMensajeCorrectoSiNoSePudoRealizarElDeposito() {
		
		whenHagamosDepositoNegativo();
		
		thenNosDevuelvaMensajeDeError();
		
	}

	private void thenNosDevuelvaMensajeDeError() {
		
		assertThat(mav.getModel().get("message").toString()).contains("Ocurrió un error");
		
	}

	private void whenHacemosDepositoValido() {
		
		double saldo = 2000;
		
		cuenta.setSaldo(saldo);
		
		mav = controladorHome.depositar(cuenta, null);
		
	}

	private void thenNosDevueltaMensajeExitoso() {
		
		assertThat(mav.getModel().get("error")).isEqualTo(Boolean.FALSE);
		assertThat(mav.getModel().get("message").toString()).contains("realizó correctamente");
		
	}

	private void whenHagamosDepositoNegativo() {
		
		double saldo = -1;
		
		cuenta.setSaldo(saldo);
		
		mav = controladorHome.depositar(cuenta, null);
		
	}

	private void thenNosDevuelvaError() {
		 assertThat(mav.getModel().get("error")).isEqualTo(Boolean.TRUE);
	}
	
}
