package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.servicios.ServicioCuenta;

@Controller
public class ControladorHome {

	private ServicioCuenta servicioCuenta;

	public ControladorHome() {}
	
	@Autowired
	public ControladorHome(ServicioCuenta servicioCuenta){
		this.servicioCuenta = servicioCuenta;
	}
	
	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		
		ModelMap modelo = new ModelMap();
		
		Cuenta cuenta = new Cuenta();
		modelo.put("cuenta", cuenta);
		
		ArrayList<String> listaMonedas = new ArrayList();
		listaMonedas.add("Peso ARG");
		listaMonedas.add("Dólar");
		modelo.put("listaMoneda",listaMonedas);
		
		return new ModelAndView("home", modelo);
	}
	
	@RequestMapping(path="/depositar", method= RequestMethod.POST)
	public ModelAndView depositar(@ModelAttribute("cuenta") Cuenta cuenta, HttpServletRequest request){
		
		ModelMap modelo = new ModelMap();
		
		if(cuenta.getMonto() < 0) {
			modelo.put("error", true);
			modelo.put("message", "Ocurrió un error al realizar el deposito. Verifique.");
			
		}else {
//			cuenta.setSaldo(cuenta.getMonto());
			if(servicioCuenta.depositar(cuenta)) {
				modelo.put("error", false);
				modelo.put("message", "El deposito se realizó correctamente.");
			}else {
				modelo.put("error", true);
				modelo.put("message", "Ocurrió un error al realizar el deposito. Verifique.");
			}
		}
		
		ArrayList<String> listaMonedas = new ArrayList();
		listaMonedas.add("Peso ARG");
		listaMonedas.add("Dólar");
		modelo.put("listaMoneda",listaMonedas);
		
		
		
		return new ModelAndView("home",modelo);
	}
	
}
