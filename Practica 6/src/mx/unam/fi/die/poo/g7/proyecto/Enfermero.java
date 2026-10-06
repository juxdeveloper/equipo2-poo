package mx.unam.fi.die.poo.g7.proyecto;

import java.util.ArrayList;

/**
 * Entidad enfermero del sistema hospitalario con identificadores y registro.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Enfermero {
	// --- ATRIBUTOS ---
	/**
	 * Atributo {@code nombre} de la clase {@code Enfermero}.
	 */
	String nombre;
	/**
	 * Atributo {@code cedula} de la clase {@code Enfermero}.
	 */
	int cedula;
	/**
	 * Atributo {@code especialidad} de la clase {@code Enfermero}.
	 */
	int especialidad;
	/**
	 * Atributo {@code noPacientes} de la clase {@code Enfermero}.
	 */
	int noPacientes = 0;

	// Extras
	//
	// Qué pacientes tiene a cargo
	/**
	 * Atributo {@code p} de la clase {@code Enfermero}.
	 */
	ArrayList<Paciente> p = new ArrayList<>();
	// A qué sistema pertenece
	/**
	 * Atributo {@code sis} de la clase {@code Enfermero}.
	 */
	Sistema sis = null;

	// -- CONSTRUCTOR
	/**
	 * Constructor de la clase {@code Enfermero}.
	 * @param nombre valor inicial del parametro.
	 * @param cedula valor inicial del parametro.
	 * @param especialidad valor inicial del parametro.
	 */
	Enfermero(String nombre, int cedula, int especialidad){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
	}

	// -- METODOS --
	//
	// Darlo de alta en x sistema
	/**
	 * Metodo {@code registroEnSistema} de la clase {@code Enfermero}.
	 * @param s argumento del metodo.
	 */
	public void registroEnSistema(Sistema s){
		sis = s;
		s.registroEnfermero(this);
	}

	// Imprimir sus pacientes ordenados
	/**
	 * Metodo {@code verListaPacientes} de la clase {@code Enfermero}.
	 */
	public void verListaPacientes() {
		p.sort((a, b) -> a.getApellido().compareToIgnoreCase(b.getApellido()));
		for (Paciente paciente : p)
			System.out.println(paciente.getApellido() + " " + paciente.getNombre() + " - Especialidad: " + paciente.getEspecialidad());
	}

	// Si estoy disponible, tratar al paciente
	/**
	 * Metodo {@code darTratamiento} de la clase {@code Enfermero}.
	 * @param paciente argumento del metodo.
	 * @param tratamiento argumento del metodo.
	 * @return valor producido por el metodo.
	 */
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
	/**
	 * Metodo {@code puedeAtender} de la clase {@code Enfermero}.
	 * @param especialidad argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean puedeAtender(int especialidad){
		if(noPacientes < 3 && this.especialidad == especialidad) return true;

		return false;
	}


	// SETTERS Y GETTERS
	/**
	 * Metodo {@code getNombre} de la clase {@code Enfermero}.
	 * @return valor producido por el metodo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo {@code getCedula} de la clase {@code Enfermero}.
	 * @return valor producido por el metodo.
	 */
	public int getCedula() {
		return cedula;
	}

	/**
	 * Metodo {@code getEspecialidad} de la clase {@code Enfermero}.
	 * @return valor producido por el metodo.
	 */
	public int getEspecialidad() {
		return especialidad;
	}

	/**
	 * Metodo {@code getNoPacientes} de la clase {@code Enfermero}.
	 * @return valor producido por el metodo.
	 */
	public int getNoPacientes() {
		return noPacientes;
	}
}
