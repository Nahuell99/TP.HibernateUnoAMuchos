package test;

import java.time.LocalDate;
import negocio.ClienteABM;

public class TestAgregarCliente {
	public static void main(String[] args) {
		ClienteABM clienteABM = new ClienteABM();

		String apellido = "Ponce";
		String nombre = "Nahuel";
		int dni = 42231767;
		LocalDate fechaDeNacimiento = LocalDate.now();

		try {
			clienteABM.agregar(apellido, nombre, dni, fechaDeNacimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
