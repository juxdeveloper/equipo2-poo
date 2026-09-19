class Medico {
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes;


	Medico(String nombre, int cedula, int especialidad, int noPacientes){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
		this.noPacientes = noPacientes;

		System.out.println("[add] Médico \"" + nombre + "\"");
	}

//	registroEnSistema, solicitarPaciente, darConsulta, darTratamiento, verListaPacientes.
}
