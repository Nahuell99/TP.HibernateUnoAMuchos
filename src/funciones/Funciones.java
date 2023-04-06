package funciones;

import java.time.LocalDate;

public class Funciones {

	public static String traerFechaCorta(LocalDate fecha) {
		return fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
	}

}
