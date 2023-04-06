package datos;

import java.time.LocalDate;
import funciones.Funciones;

public class Prestamo {
	private long idPrestamo;
	private LocalDate fecha;
	private double monto;
	private double interes;
	private int cantCuotas;
	private Cliente cliente;
	private boolean cancelado;

	public Prestamo() {
	}

	public Prestamo(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.monto = monto;
		this.interes = interes;
		this.cantCuotas = cantCuotas;
		this.cancelado = false;
		this.cliente = cliente;
	}

	public long getIdPrestamo() {
		return idPrestamo;
	}

	protected void setIdPrestamo(long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public int getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public boolean getCancelado() {
		return this.cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String toString() {
		String prestamo = "id: " + idPrestamo + "\nPrestamo: $ " + monto + "\nFecha: "
				+ Funciones.traerFechaCorta(fecha) + "\nInteres: " + interes + "\nCant.de Cuotas: " + cantCuotas
				+ "\nCancelado: " + cancelado + "\nidCliente: " + getCliente().getIdCliente();
		return prestamo;
	}
	
	public void prestamoPago() {
		//Si todas las 'Cuota' dentro del 'Prestamo' tienen el atributo 'Cancelada" en True, cambiar 'Cancelado' del 'Prestamo' a True.
	}
}
