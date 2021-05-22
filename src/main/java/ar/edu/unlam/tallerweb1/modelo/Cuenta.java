package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
@Entity
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long num;
	
	//private Usuario usuario;
	private double saldo;
	
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
