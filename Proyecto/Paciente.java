public class Paciente {
	// ATRIBUTOS
	String nombre;
	int especialidadAtencion;

	// Atributo extra necesario para guardar el tratamiento que se le asigna al Paciente
	String tratamiento = "No tiene tratamiento asignado";
	// Atributo extra necesario para saber qué Medico y qué enfermero está atendiendo al Paciente
	Medico medicoAsignado = null;
	Enfermero enfermeroAsignado = null;

	// Atributo extra necesario para saber el estado del Paciente
	public enum statuses {
    EN_ESPERA,      
    EN_CONSULTA,    
    EN_TRATAMIENTO  
	}

	public statuses estado = statuses.EN_ESPERA;

	// CONSTRUCTOR
	Paciente(String nombre, int especialidadAtencion){
		this.nombre = nombre;
		this.especialidadAtencion = especialidadAtencion;
	}

	// METODOS
	//registroEnSistema, solicitarConsulta, verTratamiento
	public void registroEnSistema(Sistema sistema){
		sistema.registroPaciente(this);
	}

	public boolean solicitarConsulta(Medico medico){
		boolean resultado = medico.solicitarPaciente(this);
		if(!resultado) return false;
		medicoAsignado = medico;
		return medico.darConsulta(this);
	}

	public void verTratamiento(){
		System.out.println("EL PACIENTE: " + nombre);
		System.out.println("Tiene el tratamiento:\n" + tratamiento);
	}
}