package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.CuotaDao;
import datos.Cuota;
import datos.Prestamo;

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
			double interesCuota, double cuota, double deuda, boolean cancelada, LocalDate fechaDePago,
			double punitorios, Prestamo prestamo) throws Exception {

		PrestamoABM prestamoABM = new PrestamoABM();
		if (prestamoABM.traerPrestamo(prestamo.getIdPrestamo()) == null) {
			throw new Exception("Se esta queriendo insertar un 'Cuota' en la DB sobre un 'Prestamo' que no existe.");
		}
		Cuota c = new Cuota(nroCuota, fechaVencimiento, saldoPendiente, amortizacion, interesCuota, cuota, deuda,
				cancelada, fechaDePago, punitorios, prestamo);
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

}