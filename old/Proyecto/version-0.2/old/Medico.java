import java.util.TreeSet;

class Medico {
	// ATRIBUTOS
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes = 0;

	// Atributo para almacenar la lista de pacientes asignados al médico
	TreeSet<String> p = new TreeSet<>();

	// Atributo para saber si está p_atendido a un paciente o no
	Paciente p_atendido = null;

	// CONSTRUCTOR
	Medico(String nombre, int cedula, int especialidad){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;

		System.out.println("[add] Médico \"" + nombre + "\"");
	}

	// METODOS
	//	registroEnSistema, solicitarPaciente, darConsulta, darTratamiento, verListaPacientes.
	public void registroEnSistema(Sistema sistema){
		sistema.registroMedico(this);
	}

	public boolean solicitarPaciente(Paciente paciente){
		if(noPacientes >= 10 || especialidad != paciente.especialidadAtencion)
			return false;
		p.add(paciente);
		noPacientes++;
		return true;
	}

	public boolean darConsulta(Paciente paciente){
		if(p_atendido == null && p.contains(paciente)){
			p_atendido = paciente;
			paciente.estado = Paciente.statuses.EN_CONSULTA;
			return true;
		}
		return false;
	}

	public boolean darTratamiento(Paciente paciente, Enfermero enfermero, String tratamiento){
		if(p_atendido == paciente){
			paciente.estado = Paciente.statuses.EN_TRATAMIENTO;
			paciente.enfermeroAsignado = enfermero;
			p_atendido = null;
			p.remove(paciente);
			noPacientes--;
			return enfermero.darTratamiento(paciente, tratamiento);
		}
		
		return false;
	}

	public void verListaPacientes(){
		System.out.println("El médico " + nombre + " tiene los siguientes pacientes asignados:");
		System.out.println(p);
	}
}
