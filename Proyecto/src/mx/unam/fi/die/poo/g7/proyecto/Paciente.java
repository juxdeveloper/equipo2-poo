package mx.unam.fi.die.poo.g7.proyecto;

public class Paciente {
	
	// --- ATRIBUTOS ---
	String nombre;
	String apellido;
	int especialidadAtencion;
	
	// --- Atributos Extras ---
	// Guardar el tratamiento dado al paciente 
	String tratamiento = "Sin tratamiento";
	// Guardar el médico que atiende al paciente 
	Medico medico = null;
	// Guardar el enfermero que atiende
	Enfermero enfermero = null;
	// Guardar el estado del paciente 
	enum Estado {
		EN_ESPERA,
		EN_CONSULTA,
		EN_HOSPITAL
	}
	Estado e = Estado.EN_ESPERA;

	
	// --- CONSTRUCTOR ---
	Paciente(String nombre, String apellido, int especialidadAtencion){
		this.nombre = nombre;
		this.apellido = apellido;
		this.especialidadAtencion = especialidadAtencion;
	}
	
	// --- METODOS ---
	//
	//
	// Se agrega en el hospital con su especialidad
	public boolean registroEnSistema(Sistema s){
		return s.registroPaciente(this, especialidadAtencion);
	}
	
	// Pude consulta a un medico
	public boolean solicitarConsulta(){
		if(medico == null) return false;

		if(medico.darConsulta(this)){
			e = Estado.EN_CONSULTA;
			return true;
		}
		return false;
	}
	
	public void verTratamiento(){
		System.out.println("El tratamiento del paciente es:\n" + tratamiento);
	}

	// GETTERS Y SETTERS

	public int getEspecialidad(){
		return especialidadAtencion;
	}

	public Estado getEstado(){
		return e;
	}

	public void setEstado(Estado e){
		this.e = e;
	}

	public void setMedico(Medico medico){
		this.medico = medico;
	}

	public Medico getMedico(){
		return medico;
	}

	public void setEnfermero(Enfermero enfermero){
		this.enfermero = enfermero;
	}

	public Enfermero getEnfermero(){
		return enfermero;
	}

	public void setTratamiento(String tratamiento){
		this.tratamiento = tratamiento;
	}

	public String getTratamiento(){
		return tratamiento;
	}

	public String getNombre() {
 		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
}
