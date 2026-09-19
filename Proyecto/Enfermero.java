public class Enfermero {
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes;

	Enfermero(String nombre, int cedula, int especialidad, int noPacientes){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
		this.noPacientes = noPacientes;

		System.out.println("[add] Enfermero \"" + nombre + "\"");
	}
	// registroEnSistema, verListaPacientes, darTratamiento.
	//
}
