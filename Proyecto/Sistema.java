import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class Sistema {
	// ATRIBUTOS
	String nombreHospital;
	int noMedicos;
	int noEnfermeros;
	int noPacientes;

	// Atributos extras para almacenar la lista de médicos, enfermeros y pacientes
	ArrayList<Medico> m = new ArrayList<>();
	ArrayList<Enfermero> e = new ArrayList<>();
	ArrayList<Paciente> p = new ArrayList<>();

	// Atributo extra para almacenar las especialidades
    public Map<Integer, String> ESPECIALIDADES = new LinkedHashMap<>();

	// Atributo extra para llevar un contador de especialidades
	public int contadorEspecialidades = 0;

	// CONSTRUCTOR
	Sistema(String nombreHospital){
		this.nombreHospital = nombreHospital;
		System.out.println("[add] Sistema / Hospital \"" + nombreHospital + "\"");
	}

	// METODOS
	// metodo extra para ingresar las especialidades
	public int agregarEspecialidad(String nombre){
		ESPECIALIDADES.put(++contadorEspecialidades, nombre);
		return contadorEspecialidades;
	}

	// metodo extra para mostrar las especialidades
	public void mostrarEspecialidades(){
		System.out.println("Especialidades disponibles:");
		for (Map.Entry<Integer, String> especialidad : ESPECIALIDADES.entrySet()) {
			System.out.println(especialidad.getKey() + " - " + especialidad.getValue());
		}
	}
	
	// metodo extra para eliminar las especialidades
	public void eliminarEspecialidad(int id){
		if(ESPECIALIDADES.containsKey(id)){
			ESPECIALIDADES.remove(id);
			System.out.println("[remove] Especialidad con ID " + id + " eliminada.");
			return;
		}
		System.out.println("[error] No existe una especialidad con ID " + id);
	}

	// metodo extra para obtener el nombre de la especialidad a partir de su ID
	public String obtenerNombreEspecialidad(int id){
		return ESPECIALIDADES.getOrDefault(id, "No existe especialidad con ID " + id);
	}

	// registroMedico, registroEnfermero, registroPaciente, asignarPaciente
	public void registroMedico(Medico medico){
		m.add(medico);
		noMedicos++;
	}	
	
	public void registroEnfermero(Enfermero enfermero){
		e.add(enfermero);
		noEnfermeros++;
	}

	public void registroPaciente(Paciente paciente){
		p.add(paciente);
		noPacientes++;
	}

	boolean asignarPaciente(Paciente paciente, Medico medico){
		return medico.solicitarPaciente(paciente	);
	}

	boolean asignarPaciente(Paciente paciente, Enfermero enfermero){
		return enfermero.darTratamiento(paciente, paciente.tratamiento);
	}
	
}
