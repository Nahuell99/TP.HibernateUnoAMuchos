package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestAgregarPrestamo {
	public static void main(String[] args) {
		PrestamoABM PrestamoABM = new PrestamoABM();
		ClienteABM ClienteABM = new ClienteABM();
		
		long idCliente = 2; // Elijo el id del cliente
		Cliente cliente = null;
		try {
			cliente = ClienteABM.traer(idCliente); //Traigo el cliente con el ID
			System.out.println(cliente);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		LocalDate fecha = LocalDate.now();
		double monto = 100000;
		double interes = 78;
		int cantCuotas = 6;
		
		try {
			PrestamoABM.agregar(fecha, monto, interes, cantCuotas, cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
