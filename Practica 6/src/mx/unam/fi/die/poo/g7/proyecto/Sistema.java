package mx.unam.fi.die.poo.g7.proyecto;

import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Contenedor del hospital que registra especialidades, medicos, enfermeros y pacientes.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Sistema {
	// -- ATRIBUTOS --
	/**
	 * Atributo {@code nombreHospital} de la clase {@code Sistema}.
	 */
	String nombreHospital;
	/**
	 * Atributo {@code noMedicos} de la clase {@code Sistema}.
	 */
	int noMedicos = 0;
	/**
	 * Atributo {@code noEnfermeros} de la clase {@code Sistema}.
	 */
	int noEnfermeros = 0;
	/**
	 * Atributo {@code noPacientes} de la clase {@code Sistema}.
	 */
	int noPacientes = 0;

	// Extras
	// Guardar el personal medico y pacientes que forman parte de dicho hospital
	/**
	 * Atributo {@code m} de la clase {@code Sistema}.
	 */
	ArrayList<Medico> m = new ArrayList<>();
	/**
	 * Atributo {@code e} de la clase {@code Sistema}.
	 */
	ArrayList<Enfermero> e = new ArrayList<>();
	/**
	 * Atributo {@code p} de la clase {@code Sistema}.
	 */
	ArrayList<Paciente> p = new ArrayList<>();

	// Guardar el diccionario de especialidades
	/**
	 * Atributo {@code spec} de la clase {@code Sistema}.
	 */
	TreeMap<Integer,String> spec = new TreeMap<>();

	// CONSTRUCTOR
	/**
	 * Constructor de la clase {@code Sistema}.
	 * @param nombreHospital valor inicial del parametro.
	 */
	Sistema(String nombreHospital){
		this.nombreHospital = nombreHospital;
	}

	// METODOS
	//
	// Dar de alta al medico en este hospital
	/**
	 * Metodo {@code registroMedico} de la clase {@code Sistema}.
	 * @param med argumento del metodo.
	 */
	public void registroMedico(Medico med){
		m.add(med);
		noMedicos++;
	}

	// Dar de alta al enfermero en este hospital
	/**
	 * Metodo {@code registroEnfermero} de la clase {@code Sistema}.
	 * @param enf argumento del metodo.
	 */
	public void registroEnfermero(Enfermero enf){
		e.add(enf);
		noEnfermeros++;
	}

	// Dar de alta al paciente en este hospital
	/**
	 * Metodo {@code registroPaciente} de la clase {@code Sistema}.
	 * @param pac argumento del metodo.
	 * @param especialidad argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean registroPaciente(Paciente pac, int especialidad){
		for(Medico medico : m) {
			if(medico.getEspecialidad() == especialidad && (medico.getNoPacientes() < 10)){
				p.add(pac);
				noPacientes++;
				return true;
			}
		}

		return false;
	}

	// METODO SOBRECARGADO
	// Para asignar paciente al medico que pide más pacientes
	/**
	 * Metodo {@code asignarPaciente} de la clase {@code Sistema}.
	 * @param medico argumento del metodo.
	 * @param especialidad argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public Paciente asignarPaciente(Medico medico, int especialidad){
		for(Paciente paciente : p){
			if(paciente.getEspecialidad() == especialidad && (paciente.getEstado() == Paciente.Estado.EN_ESPERA) && paciente.getMedico() == null){
				return paciente;
			}
		}
		return null;
	}

	// Para el enfermero que se le está asignando un paciente por orden del medico
	/**
	 * Metodo {@code asignarPaciente} de la clase {@code Sistema}.
	 * @param paciente argumento del metodo.
	 * @param medico argumento del metodo.
	 * @param especialidad argumento del metodo.
	 * @param prescripcion argumento del metodo.
	 * @return valor producido por el metodo.
	 */
	public boolean asignarPaciente(Paciente paciente, Medico medico, int especialidad, String prescripcion){
		for(Enfermero enfermero : e){
			if(paciente.getEstado() == Paciente.Estado.EN_CONSULTA && enfermero.puedeAtender(paciente.getEspecialidad())){
				enfermero.darTratamiento(paciente, prescripcion);
				return true;
			}
		}
		return false;
	}

	// GETTERS Y SETTERS
	/**
	 * Metodo {@code setEspecialidad} de la clase {@code Sistema}.
	 * @param nombre argumento del metodo.
	 */
	public void setEspecialidad(String nombre){
		spec.put(spec.size()+1, nombre);
	}

	/**
	 * Metodo {@code getEspecialidad} de la clase {@code Sistema}.
	 */
	public void getEspecialidad() {
 		for (Integer numero : spec.keySet()) {
			System.out.println(numero + " : " + spec.get(numero));
    		}
	}

	/**
	 * Metodo {@code getNumeroEspecialidades} de la clase {@code Sistema}.
	 * @return valor producido por el metodo.
	 */
	public int getNumeroEspecialidades() {
		return spec.size();
	}

	/**
	 * Metodo {@code getNombreHospital} de la clase {@code Sistema}.
	 * @return valor producido por el metodo.
	 */
	public String getNombreHospital() {
		return nombreHospital;
	}

	/**
	 * Metodo {@code getMedicos} de la clase {@code Sistema}.
	 * @return valor producido por el metodo.
	 */
	public ArrayList<Medico> getMedicos() {
		return m;
	}

	/**
	 * Metodo {@code getEnfermeros} de la clase {@code Sistema}.
	 * @return valor producido por el metodo.
	 */
	public ArrayList<Enfermero> getEnfermeros() {
		return e;
	}

	/**
	 * Metodo {@code getPacientes} de la clase {@code Sistema}.
	 * @return valor producido por el metodo.
	 */
	public ArrayList<Paciente> getPacientes() {
		return p;
	}
}
