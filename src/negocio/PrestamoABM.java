package negocio;

import dao.PrestamoDao;
import java.time.LocalDate;
import java.util.List;
import datos.Cliente;
import datos.Prestamo;

public class PrestamoABM {
	private PrestamoDao dao = new PrestamoDao();

	public Prestamo traerPrestamo(long idPrestamo) throws Exception {
		if (dao.traer(idPrestamo) == null) {
			throw new Exception("Prestamo no existe");
		}
		return dao.traer(idPrestamo);
	}

	public List<Prestamo> traerPrestamo(Cliente c) {
		// Implementar: si el no existe el Cliente lanzar la excepción
		// Implementar: si el no existen prestamos asociados lanzar la excepción
		return dao.traer(c);
	}

	public int agregar(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente)
			throws Exception {
		ClienteABM clienteABM = new ClienteABM();
		if (clienteABM.traer(cliente.getIdCliente()) == null) {
			throw new Exception("Se esta queriendo insertar un 'Prestamo' en la DB sobre un Cliente que no existe.");
		}
		Prestamo c = new Prestamo(fecha, monto, interes, cantCuotas, cliente);
		return dao.agregar(c);
	}

	public void modificar(Prestamo prestamo) throws Exception {
		dao.actualizar(prestamo);
	}

	public void eliminar(long idPrestamo) throws Exception {
		if (this.traerPrestamo(idPrestamo) == null) {
			throw new Exception(
					"Se esta queriendo eliminar un 'Prestamo' en la DB con el ID '" + idPrestamo + "' que no existente.");
		}
		Prestamo c = this.traerPrestamo(idPrestamo);
		dao.eliminar(c);
	}

}
