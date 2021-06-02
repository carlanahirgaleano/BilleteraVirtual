package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Transaccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;

public class ControladorTransferenciaTest {

	private ControladorTransferencia controladorTransferencia;
	private Transaccion transaccion = new Transaccion();
	private ModelAndView mav;
	private ServicioCuenta servicioCuenta;
	
	//Creo un mock (fake object) para que se comporte como yo le indique
	@Before
	public void init() {
		
		servicioCuenta = mock(ServicioCuenta.class);
		controladorTransferencia = new ControladorTransferencia(servicioCuenta);
	}
	
	@Test
	public void alHacerUnaTransferenciaLoDevuelvaALaPaginaTransferir() {
		
		whenRealizamosUnaTransferencia();
		
		thenNosLleveALaPaginaTransferir();
		
	}
	
	@Test
	public void alHacerUnaTransferenciaConMontoNegativoLanzeUnError() {
		
		whenRealizamosTransferenciaNegativa();
		
		thenNosLanzaUnError();
	}
	
	@Test
	public void alHacerUnaTransferenciaConMontoPositivoNoMeLazeError() {
		
		whenRealizamosUnaTransferenciaConMontoPositivo();
		
		thenNosNosLanzaUnError();
		
	}

	private void whenRealizamosUnaTransferenciaConMontoPositivo() {
		
		transaccion.setMonto(15);
		
		mav = controladorTransferencia.transferencia(transaccion, null);
		
	}

	private void thenNosNosLanzaUnError() {
		
		assertThat(mav.getModel().get("error")).isEqualTo(Boolean.FALSE);
	}

	private void whenRealizamosTransferenciaNegativa() {
		
		transaccion.setMonto(-15);
		
		mav = controladorTransferencia.transferencia(transaccion, null);
		
	}

	private void thenNosLanzaUnError() {
		
		assertThat(mav.getModel().get("error")).isEqualTo(Boolean.TRUE);
	}

	private void whenRealizamosUnaTransferencia() {
		
		mav = controladorTransferencia.transferencia(transaccion, null);

	}

	private void thenNosLleveALaPaginaTransferir() {
		
		assertThat(mav.getViewName())
			.isEqualTo("transferir");
	}
	
}
