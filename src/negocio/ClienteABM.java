package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.ClienteDao;
import datos.Cliente;

public class ClienteABM {
	ClienteDao dao = new ClienteDao();

	public Cliente traer(long idCliente) throws Exception {
		if (dao.traer(idCliente) == null) {
			throw new Exception("Cliente no existe.");
		}
		return dao.traer(idCliente);
	}

	public Cliente traer(int dni) throws Exception {
		return dao.traer(dni);
	}

	public int agregar(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento) throws Exception {
		if (traer(dni) != null) {
			throw new Exception(
					"Se esta queriendo insertar un 'Cliente' en la DB con el DNI '" + dni + "' ya existente.");
		}
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		return dao.agregar(c);
	}

	public void modificar(Cliente c) throws Exception {
		if (traer(c.getDni()) != null) {
			throw new Exception(
					"Se esta queriendo actualizar un 'Cliente' en la DB con el DNI '" + c.getDni() + "' ya existente.");
		}
		dao.actualizar(c);
	}

	public void eliminar(long idCliente) throws Exception {
		if (traer(idCliente) == null) {
			throw new Exception(
					"Se esta queriendo eliminar un 'Cliente' en la DB con el ID '" + idCliente + "' que no existente.");
		}
		Cliente c = dao.traer(idCliente);
		dao.eliminar(c);
	}

	public List<Cliente> traer() {
		return dao.traer();
	}

	public Cliente traerClienteYPrestamos(long idCliente) {
		return dao.traerClienteYPrestamos(idCliente);
	}

}
