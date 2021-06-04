package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long num_cuenta_destino;
	private long num_cuenta_origen;
	private double monto;
	private boolean anulado;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNum_cuenta_destino() {
		return num_cuenta_destino;
	}
	public void setNum_cuenta_destino(long num_cuenta_destino) {
		this.num_cuenta_destino = num_cuenta_destino;
	}
	public long getNum_cuenta_origen() {
		return num_cuenta_origen;
	}
	public void setNum_cuenta_origen(long num_cuenta_origen) {
		this.num_cuenta_origen = num_cuenta_origen;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	
}
