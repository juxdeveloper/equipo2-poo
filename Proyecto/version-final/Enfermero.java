import java.util.ArrayList;

public class Enfermero {
	// --- ATRIBUTOS ---
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes = 0;

	// Extras
	//
	// Qué pacientes tiene a cargo
	ArrayList<Paciente> p = new ArrayList<>();
	// A qué sistema pertenece
	Sistema sis = null;

	// -- CONSTRUCTOR
	Enfermero(String nombre, int cedula, int especialidad){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
	}

	// -- METODOS --
	//
	// Darlo de alta en x sistema
	public void registroEnSistema(Sistema s){
		sis = s;
		s.registroEnfermero(this);
	}

	// Imprimir sus pacientes ordenados
	public void verListaPacientes() {
		p.sort((a, b) -> a.getApellido().compareToIgnoreCase(b.getApellido()));
		for (Paciente paciente : p)
			System.out.println(paciente.getApellido() + " " + paciente.getNombre() + " - Especialidad: " + paciente.getEspecialidad());
	}

	// Si estoy disponible, tratar al paciente
	public boolean darTratamiento(Paciente paciente, String tratamiento){
		if(p.contains(paciente) || noPacientes >= 3) return false;
		p.add(paciente);
		paciente.setEnfermero(this);
		paciente.setTratamiento(tratamiento);
		paciente.setEstado(Paciente.Estado.EN_HOSPITAL);
		noPacientes++;
		return true;
	}

	// Dependiendo su puedo atender decir si sí o no
	public boolean puedeAtender(int especialidad){
		if(noPacientes < 3 && this.especialidad == especialidad) return true;

		return false;
	}


	// SETTERS Y GETTERS
	public String getNombre() {
		return nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public int getEspecialidad() {
		return especialidad;
	}

	public int getNoPacientes() {
		return noPacientes;
	}
}
