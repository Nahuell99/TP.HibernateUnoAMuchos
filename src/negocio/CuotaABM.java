package negocio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.CuotaDao;
import datos.Cuota;
import datos.Prestamo;
import funciones.Funciones;

public class CuotaABM {
	private CuotaDao dao = new CuotaDao();

	public Cuota traerCuota(long idCuota) throws Exception {
		if (dao.traer(idCuota) == null) {
			throw new Exception("Cuota no existe");
		}
		return dao.traer(idCuota);
	}

	public List<Cuota> traerCuotas(Prestamo p) {
		// Implementar: si el no existe el Cliente lanzar la excepción
		// Implementar: si el no existen prestamos asociados lanzar la excepción
		return dao.traer(p);
	}

	public int agregar(int nroCuota, LocalDate fechaVencimiento, double saldoPendiente, double amortizacion,
			double interesCuota, double cuota, double deuda, LocalDate fechaDePago, double punitorios,
			Prestamo prestamo) throws Exception {

		PrestamoABM prestamoABM = new PrestamoABM();
		if (prestamoABM.traerPrestamo(prestamo.getIdPrestamo()) == null) {
			throw new Exception("Se esta queriendo insertar un 'Cuota' en la DB sobre un 'Prestamo' que no existe.");
		}
		Cuota c = new Cuota(nroCuota, fechaVencimiento, saldoPendiente, amortizacion, interesCuota, cuota, deuda,
				fechaDePago, punitorios, prestamo);
		return dao.agregar(c);
	}

	public void modificar(Cuota cuota) throws Exception {
		dao.actualizar(cuota);
	}

	public void eliminar(long idCuota) throws Exception {
		if (this.traerCuota(idCuota) == null) {
			throw new Exception(
					"Se esta queriendo eliminar un 'Cuota' en la DB con el ID '" + idCuota + "' que no existente.");
		}
		Cuota c = this.traerCuota(idCuota);
		dao.eliminar(c);
	}

	@SuppressWarnings("null")
	public Set<Cuota> generarCuotas(LocalDate fecha, double monto, double interes, int cantCuotas, Prestamo prestamo) {
		Set<Cuota> cuotas = new HashSet<Cuota>();

		int nroCuota = 0;
		LocalDate fechaVencimiento = fecha;
		double saldoPendiente;
		double amortizacion = 0;
		double interesCuota;
		double cuota;
		double deuda;
		LocalDate fechaDePago = null;
		double punitorios = 0;

		for (int i = 0; i < cantCuotas; i++) {
			// Numero de cuota es igual a la iteracion del FOR mas 1
			nroCuota = i + 1;

			// Entonces el primer 'saldoPendiente' sera el monto solicitado del crédito
			saldoPendiente = monto - amortizacion;

			// Calculo del interés 𝑖𝑛𝑡𝑒𝑟𝑒𝑠𝐶𝑢𝑜𝑡𝑎 =
			// 𝑠𝑎𝑙𝑑𝑜𝑃𝑒𝑛𝑑𝑖𝑒𝑛𝑡𝑒∗𝑖𝑛𝑡𝑒𝑟𝑒𝑠 //(Porcentaje x Total) / 100
			interesCuota = saldoPendiente * interes;

			// Calculo de la amortizaron 𝑎𝑚𝑜𝑟𝑡𝑖𝑧𝑎𝑐𝑖𝑜𝑛 =
			// 𝑠𝑎𝑙𝑑𝑜𝑃𝑒𝑛𝑑𝑖𝑒𝑛𝑡𝑒∗𝑖𝑛𝑡𝑒𝑟𝑒𝑠(1+𝑖𝑛𝑡𝑒𝑟𝑒𝑠)𝑛−1
			if (i == 0) {
				amortizacion = (interesCuota) / (Math.pow(1 + interes, cantCuotas) - 1);
			} else {
				amortizacion = (interesCuota) / (Math.pow(1 + interes, cantCuotas - 1) - 1);
			}

			// Entonces el valor de la cuota será: 𝑐𝑢𝑜𝑡𝑎 = 𝑎𝑚𝑜𝑟𝑖𝑡𝑖𝑧𝑎𝑐𝑖𝑜𝑛 +
			// 𝑖𝑛𝑡𝑒𝑟𝑒𝑠𝐶𝑢𝑜𝑡
			cuota = amortizacion + interesCuota;

			// Entonces la deuda pendiente será: 𝑑𝑒𝑢𝑑𝑎 = 𝑠𝑎𝑙𝑑𝑜𝑃𝑒𝑛𝑑𝑖𝑒𝑛𝑡𝑒 −
			// 𝑎𝑚𝑜𝑟𝑡𝑖𝑧𝑎𝑐𝑖𝑜
			deuda = saldoPendiente - amortizacion;

			// Entonces el saldo pendiente será:𝑆𝑎𝑙𝑑𝑜𝑃𝑒𝑛𝑑𝑖𝑒𝑛𝑡𝑒 =
			// 𝑆𝑎𝑙𝑑𝑜𝑃𝑒𝑛𝑑𝑖𝑒𝑛𝑡𝑒 − 𝑎𝑚𝑜𝑟𝑡𝑖𝑧𝑎𝑐𝑖𝑜
			saldoPendiente = saldoPendiente - amortizacion;

			if (fechaVencimiento.plusMonths(1).getDayOfWeek().getValue() == 6) {
				fechaVencimiento = fechaVencimiento.plusMonths(1).plusDays(2);
			} else if (fechaVencimiento.plusMonths(1).getDayOfWeek().getValue() == 7) {
				fechaVencimiento = fechaVencimiento.plusMonths(1).plusDays(1);
			} else {
				fechaVencimiento = fechaVencimiento.plusMonths(1);
			}
			Cuota ObjetoCuota = new Cuota(nroCuota, fechaVencimiento, saldoPendiente, amortizacion, interesCuota, cuota, deuda,
					fechaDePago, punitorios, prestamo);
			
			System.out.println(ObjetoCuota + "\n\n\n");
			
			cuotas.add(ObjetoCuota);
		}

		return cuotas;
	}

}