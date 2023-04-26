package test;

import java.time.LocalDate;
import java.util.List;

import dao.ClienteDao;
import dao.PrestamoDao;
import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestHQLs {
	public static void main(String[] args) {
		ClienteABM ClienteABM = new ClienteABM();
		PrestamoABM PrestamoABM = new PrestamoABM();
		ClienteDao ClienteDao = new ClienteDao();
		PrestamoDao PrestamoDao = new PrestamoDao();
		
		long idCliente = 2; // Elijo el id del cliente
		Cliente cliente = ClienteDao.traer(idCliente);
		List<Prestamo> prestamos = null;
		LocalDate fecha1 = LocalDate.of(2023, 10, 18);
		LocalDate fecha2 = LocalDate.of(2023, 04, 15);
		
		try {
			prestamos = ClienteDao.traerPrestamosCuotaAVencer(fecha1); //Traigo el cliente con el ID
			System.out.println(prestamos);
			
			//System.out.println(PrestamoABM.traerPrestamo(cliente));
			
			
			//System.out.println(Dao.traerPrestamosPorEstado(cliente, false));
			
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		

	}
}
