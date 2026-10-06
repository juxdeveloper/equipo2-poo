package mx.unam.fi.die.poo.g7.proyecto;

import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;

public class Sistema {
	// -- ATRIBUTOS --
	String nombreHospital;
	int noMedicos = 0;
	int noEnfermeros = 0;
	int noPacientes = 0;

	// Extras
	// Guardar el personal medico y pacientes que forman parte de dicho hospital
	ArrayList<Medico> m = new ArrayList<>();
	ArrayList<Enfermero> e = new ArrayList<>();
	ArrayList<Paciente> p = new ArrayList<>();

	// Guardar el diccionario de especialidades
	TreeMap<Integer,String> spec = new TreeMap<>();

	// CONSTRUCTOR
	Sistema(String nombreHospital){
		this.nombreHospital = nombreHospital;
	}

	// METODOS
	//
	// Dar de alta al medico en este hospital
	public void registroMedico(Medico med){
		m.add(med);
		noMedicos++;
	}

	// Dar de alta al enfermero en este hospital
	public void registroEnfermero(Enfermero enf){
		e.add(enf);
		noEnfermeros++;
	}

	// Dar de alta al paciente en este hospital
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
	public Paciente asignarPaciente(Medico medico, int especialidad){
		for(Paciente paciente : p){
			if(paciente.getEspecialidad() == especialidad && (paciente.getEstado() == Paciente.Estado.EN_ESPERA) && paciente.getMedico() == null){
				return paciente;
			}
		}
		return null;
	}

	// Para el enfermero que se le está asignando un paciente por orden del medico
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
	public void setEspecialidad(String nombre){
		spec.put(spec.size()+1, nombre);
	}

	public void getEspecialidad() {
 		for (Integer numero : spec.keySet()) {
			System.out.println(numero + " : " + spec.get(numero));
    		}
	}

	public int getNumeroEspecialidades() {
		return spec.size();
	}

	public String getNombreHospital() {
		return nombreHospital;
	}

	public ArrayList<Medico> getMedicos() {
		return m;
	}

	public ArrayList<Enfermero> getEnfermeros() {
		return e;
	}

	public ArrayList<Paciente> getPacientes() {
		return p;
	}
}
