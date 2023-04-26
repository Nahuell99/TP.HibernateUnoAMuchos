package datos;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import funciones.Funciones;

public class Prestamo {
	private long idPrestamo;
	private LocalDate fecha;
	private double monto;
	private double interes;
	private int cantCuotas;
	private boolean cancelado;
	private Cliente cliente;
	private Set<Cuota> cuota; // ¿Porque utilizamos Set y no List?
	

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
	
	public Set<Cuota> getCuota() {
		return cuota;
	}

	public void setCuota(Set<Cuota> cuota) {
		this.cuota = cuota;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(idPrestamo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		return idPrestamo == other.idPrestamo;
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
