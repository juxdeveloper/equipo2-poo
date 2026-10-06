package mx.unam.fi.die.poo.g7.proyecto;

/**
 * Entidad paciente del sistema hospitalario con nombre, estado y medico asignado.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Paciente {
	
	// --- ATRIBUTOS ---
	/**
	 * Atributo {@code nombre} de la clase {@code Paciente}.
	 */
	String nombre;
	/**
	 * Atributo {@code apellido} de la clase {@code Paciente}.
	 */
	String apellido;
	/**
	 * Atributo {@code especialidadAtencion} de la clase {@code Paciente}.
	 */
	int especialidadAtencion;
	
	// --- Atributos Extras ---
	// Guardar el tratamiento dado al paciente 
	/**
	 * Atributo {@code tratamiento} de la clase {@code Paciente}.
	 */
	String tratamiento = "Sin tratamiento";
	// Guardar el médico que atiende al paciente 
	/**
	 * Atributo {@code medico} de la clase {@code Paciente}.
	 */
	Medico medico = null;
	// Guardar el enfermero que atiende
	/**
	 * Atributo {@code enfermero} de la clase {@code Paciente}.
	 */
	Enfermero enfermero = null;
	/**
	 * Enumeracion de estados del paciente.
	 *
	 * @author Equipo 2
	 */
	enum Estado {
		/** Estado inicial en espera. */
		EN_ESPERA,
		/** Estado en consulta medica. */
		EN_CONSULTA,
		/** Estado hospitalizado. */
		EN_HOSPITAL
	}
	/**
	 * Atributo {@code e} de la clase {@code Paciente}.
	 */
	Estado e = Estado.EN_ESPERA;

	
	// --- CONSTRUCTOR ---
	/**
	 * Constructor de la clase {@code Paciente}.
	 * @param nombre valor inicial del parametro.
	 * @param apellido valor inicial del parametro.
	 * @param especialidadAtencion valor inicial del parametro.
	 */
	Paciente(String nombre, String apellido, int especialidadAtencion){
		this.nombre = nombre;
		this.apellido = apellido;
		this.especialidadAtencion = especialidadAtencion;
	}
	
	// --- METODOS ---
	//
	//
	// Se agrega en el hospital con su especialidad
	/**
	 * Metodo {@code registroEnSistema} de la clase {@code Paciente}.
	 * @param s argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean registroEnSistema(Sistema s){
		return s.registroPaciente(this, especialidadAtencion);
	}
	
	// Pude consulta a un medico
	/**
	 * Metodo {@code solicitarConsulta} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public boolean solicitarConsulta(){
		if(medico == null) return false;

		if(medico.darConsulta(this)){
			e = Estado.EN_CONSULTA;
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo {@code verTratamiento} de la clase {@code Paciente}.
	 */
	public void verTratamiento(){
		System.out.println("El tratamiento del paciente es:\n" + tratamiento);
	}

	// GETTERS Y SETTERS

	/**
	 * Metodo {@code getEspecialidad} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public int getEspecialidad(){
		return especialidadAtencion;
	}

	/**
	 * Metodo {@code getEstado} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public Estado getEstado(){
		return e;
	}

	/**
	 * Metodo {@code setEstado} de la clase {@code Paciente}.
	 * @param e argumento del metodo.
	 */
	public void setEstado(Estado e){
		this.e = e;
	}

	/**
	 * Metodo {@code setMedico} de la clase {@code Paciente}.
	 * @param medico argumento del metodo.
	 */
	public void setMedico(Medico medico){
		this.medico = medico;
	}

	/**
	 * Metodo {@code getMedico} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public Medico getMedico(){
		return medico;
	}

	/**
	 * Metodo {@code setEnfermero} de la clase {@code Paciente}.
	 * @param enfermero argumento del metodo.
	 */
	public void setEnfermero(Enfermero enfermero){
		this.enfermero = enfermero;
	}

	/**
	 * Metodo {@code getEnfermero} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public Enfermero getEnfermero(){
		return enfermero;
	}

	/**
	 * Metodo {@code setTratamiento} de la clase {@code Paciente}.
	 * @param tratamiento argumento del metodo.
	 */
	public void setTratamiento(String tratamiento){
		this.tratamiento = tratamiento;
	}

	/**
	 * Metodo {@code getTratamiento} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public String getTratamiento(){
		return tratamiento;
	}

	/**
	 * Metodo {@code getNombre} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public String getNombre() {
 		return nombre;
	}

	/**
	 * Metodo {@code getApellido} de la clase {@code Paciente}.
	 * @return valor producido por el metodo.
	 */
	public String getApellido() {
		return apellido;
	}
}
