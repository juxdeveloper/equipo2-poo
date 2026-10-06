package mx.unam.fi.die.poo.g7.proyecto;

import java.util.ArrayList;

/**
 * Entidad medico del sistema hospitalario con cedula, especialidad y pacientes asignados.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Medico {
	// --- ATRIBUTOS ---
	/**
	 * Atributo {@code nombre} de la clase {@code Medico}.
	 */
	String nombre;
	/**
	 * Atributo {@code cedula} de la clase {@code Medico}.
	 */
	int cedula;
	/**
	 * Atributo {@code especialidad} de la clase {@code Medico}.
	 */
	int especialidad;
	/**
	 * Atributo {@code noPacientes} de la clase {@code Medico}.
	 */
	int noPacientes = 0;
	
	// Extras
	// Está atendiendo a alguien el médico?
	/**
	 * Atributo {@code atendiendo} de la clase {@code Medico}.
	 */
	Paciente atendiendo = null;
	
	// A qué hospital pertenece 
	/**
	 * Atributo {@code sis} de la clase {@code Medico}.
	 */
	Sistema sis = null;
	
	// Qué pacientes tiene asignados
	/**
	 * Atributo {@code p} de la clase {@code Medico}.
	 */
	ArrayList<Paciente> p = new ArrayList<>();

	// CONSTRUCTOR
	/**
	 * Constructor de la clase {@code Medico}.
	 * @param nombre valor inicial del parametro.
	 * @param cedula valor inicial del parametro.
	 * @param especialidad valor inicial del parametro.
	 */
	Medico(String nombre, int cedula, int especialidad){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
	}
	
	// MÉTODOS
	//
	// Darlo de alta en x sistema
	/**
	 * Metodo {@code registroEnSistema} de la clase {@code Medico}.
	 * @param s argumento del metodo.
	 */
	public void registroEnSistema(Sistema s){
		sis = s;
		s.registroMedico(this);
	}
	
	// Si esta disponible intentar asignar paciente al medico
	/**
	 * Metodo {@code solicitarPaciente} de la clase {@code Medico}.
	 * @param s argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean solicitarPaciente(Sistema s){
		if(sis == null) sis = s;
		if(noPacientes < 10){
			Paciente pt = s.asignarPaciente(this, especialidad);
			if(pt != null){
				p.add(pt);
				pt.setMedico(this);
				noPacientes++;
				return true;
			}
		}
		return false;
	}
	
	// Si puede atender porque ya tiene al paciente asignado y no esta atendiendo a alguien entonces atiendelo
	/**
	 * Metodo {@code darConsulta} de la clase {@code Medico}.
	 * @param paciente argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean darConsulta(Paciente paciente){
		if(!p.contains(paciente) || atendiendo != null) return false;
		atendiendo = paciente;
		paciente.setEstado(Paciente.Estado.EN_CONSULTA);
		return true;
	}
	
	// Si no estoy atendiendo al paciente, no le puedo dar tratamiento de lo contrario vamos a asignarlo a hospitalizacion
	/**
	 * Metodo {@code darTratamiento} de la clase {@code Medico}.
	 * @param paciente argumento del metodo.
	 * @param tratamiento argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean darTratamiento(Paciente paciente, String tratamiento){
		if(paciente != atendiendo) return false;

		if(sis != null && sis.asignarPaciente(paciente, this, especialidad, tratamiento)){
			atendiendo = null;
			paciente.setTratamiento(tratamiento);
			paciente.setEstado(Paciente.Estado.EN_HOSPITAL);
			return true;
		}
		return false;
	}

	// Ordena e imprime cada elemento del arraylist p de pacientes
	/**
	 * Metodo {@code verListaPacientes} de la clase {@code Medico}.
	 */
	public void verListaPacientes() {
		p.sort((a, b) -> a.getApellido().compareToIgnoreCase(b.getApellido()));
		for (Paciente paciente : p)
			System.out.println(paciente.getApellido() + " " + paciente.getNombre() + " - Especialidad: " + paciente.getEspecialidad());
	}

	// extra
	// GETTERS Y SETTERS
	/**
	 * Metodo {@code getEspecialidad} de la clase {@code Medico}.
	 * @return valor producido por el metodo.
	 */
	public int getEspecialidad(){
		return especialidad;
	}

	/**
	 * Metodo {@code getNoPacientes} de la clase {@code Medico}.
	 * @return valor producido por el metodo.
	 */
	public int getNoPacientes(){
		return noPacientes;
	}

	/**
	 * Metodo {@code getNombre} de la clase {@code Medico}.
	 * @return valor producido por el metodo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo {@code getCedula} de la clase {@code Medico}.
	 * @return valor producido por el metodo.
	 */
	public int getCedula() {
		return cedula;
	}

	/**
	 * Metodo {@code getAtendiendo} de la clase {@code Medico}.
	 * @return valor producido por el metodo.
	 */
	public Paciente getAtendiendo() {
		return atendiendo;
	}

	/**
	 * Metodo {@code getPacientesAsignados} de la clase {@code Medico}.
	 * @return valor producido por el metodo.
	 */
	public ArrayList<Paciente> getPacientesAsignados() {
		return p;
	}
}
