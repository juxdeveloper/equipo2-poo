import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

class Medico {
	// ATRIBUTOS
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes = 0;

	// Atributo para almacenar la lista de pacientes asignados al médico
	ArrayList<Paciente> p = new ArrayList<>();

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
		ArrayList<Paciente> Copia_Ordenada = new ArrayList<>(p);
		
		Collator comparadorEspañol = Collator.getInstance(Locale.of("es"));
    	comparadorEspañol.setStrength(Collator.PRIMARY);

		Copia_Ordenada.sort((a, b) -> comparadorEspañol.compare(b.nombre, a.nombre));

		System.out.println("El médico " + nombre + " tiene los siguientes pacientes asignados:");
		for(Paciente p : Copia_Ordenada){
			System.out.println("- " + p.nombre + " | Especialidad: " + p.especialidadAtencion);
		}
	}
}