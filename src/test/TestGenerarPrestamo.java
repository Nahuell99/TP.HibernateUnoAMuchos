package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestGenerarPrestamo {
	public static void main(String[] args) {
		PrestamoABM PrestamoABM = new PrestamoABM();
		ClienteABM ClienteABM = new ClienteABM();
		long idCliente = 1;
		Cliente cliente = null;
		try {
			cliente = ClienteABM.traer(idCliente);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(cliente);
		
		
		LocalDate fecha = LocalDate.now();
		double monto = 1000d;
		double interes = 30d;
		int cantCuotas = 24;
		
		try {
			PrestamoABM.agregar(fecha, monto, interes, cantCuotas, cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
