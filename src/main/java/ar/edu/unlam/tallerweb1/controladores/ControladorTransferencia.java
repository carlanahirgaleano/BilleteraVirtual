package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Transaccion;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;

@Controller
public class ControladorTransferencia {


	private ServicioCuenta servicioCuenta;
	private long id_cuenta;

	@Autowired
	public ControladorTransferencia(ServicioCuenta servicioCuenta){
		this.servicioCuenta = servicioCuenta;
	}

	
	@RequestMapping(path= "/transferir", method= RequestMethod.GET)
	public ModelAndView irATransferir(Long cid) {
		
		//Inicializo
		id_cuenta = cid;
		ModelMap modelo = new ModelMap();
		Transaccion transaccion = new Transaccion();
		transaccion.setNum_cuenta_origen(id_cuenta);
		
		modelo.put("transaccion", transaccion);
		modelo.put("listaCuentas", traerListaNumCuentas());
		
		return new ModelAndView("transferir",modelo);
	}
	
	@RequestMapping(path="/transferencia", method= RequestMethod.POST)
	public ModelAndView transferencia (@ModelAttribute("transaccion") Transaccion transaccion, HttpServletRequest request){
		
		ModelMap modelo = new ModelMap();
		
		//Asigno el id de cuenta
		transaccion.setNum_cuenta_origen(id_cuenta);
		
		//Reviso que el monto de la transacción no sea negativo
		if(transaccion.getMonto() >= 0) {
			modelo.put("error", false);
			modelo.put("mensaje", "Se realizó correctamente la transferencia.");
			servicioCuenta.transferir(transaccion);
		}else {
			modelo.put("error", true);
			modelo.put("mensaje", "Ocurrió un error al realizar la transferencia. Por favor revise.");
		}
		
		modelo.put("listaCuentas", traerListaNumCuentas());
		
		return new ModelAndView("transferir",modelo);
		
	}
	
	public List<Long> traerListaNumCuentas(){
		
		List<Long> lista_num_cuentas = new ArrayList();
		List<Cuenta> listaCuentas = servicioCuenta.traerTodos();
		
		listaCuentas.forEach(cuenta ->{
			lista_num_cuentas.add(cuenta.getNum());
		});
		
		return lista_num_cuentas;
		
	}
	
}
