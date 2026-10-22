package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import pacientes.*;
import personal.*;

public class Sistema {

	private static int siguienteId = 1;
	private int id;
	private String nombreHospital;
	private int noMedicos = 0;
	private int noEnfermeros = 0;
	private int noPacientes = 0;
	private List<Medico> medicos = new ArrayList<>();
	private List<Enfermero> enfermeros = new ArrayList<>();
	private List<Paciente> pacientes = new ArrayList<>();
	private Map<Integer, String> especialidades = new TreeMap<>();

	public Sistema(String nombreHospital){
		id = siguienteId++;
		this.nombreHospital = "Hospital " + id;
		setNombreHospital(nombreHospital);
		especialidades.put(0, "Medicina general");
	}

	public int getId(){
		return id;
	}

	public String getNombreHospital(){
		return nombreHospital;
	}

	public void setNombreHospital(String nombreHospital){
		if(nombreHospital == null || nombreHospital.isBlank())
			System.out.println("ERROR : Nombre de hospital vacio");
		else
			this.nombreHospital = nombreHospital.trim();
	}

	public int getNoMedicos(){
		return noMedicos;
	}

	public int getNoEnfermeros(){
		return noEnfermeros;
	}

	public int getNoPacientes(){
		return noPacientes;
	}

	public boolean registroEspecialidad(int numero, String nombre){
		if(numero < 1 || numero > 99 || nombre == null || nombre.isBlank()
				|| especialidades.containsKey(numero)){
			System.out.println("ERROR : Especialidad invalida o numero ya registrado");
			return false;
		}
		especialidades.put(numero, nombre.trim());
		return true;
	}

	public String getEspecialidad(int numero){
		return especialidades.get(numero);
	}

	public void verEspecialidades(){
		for(int numero : especialidades.keySet())
			System.out.println(numero + ". " + especialidades.get(numero));
	}

	private boolean nombreValido(String nombre, String paterno, String materno){
		return nombre != null && !nombre.isBlank() && paterno != null && !paterno.isBlank()
				&& materno != null && !materno.isBlank();
	}

	private boolean cedulaDisponible(int cedula){
		return cedula >= 100000 && getMedicoPorCedula(cedula) == null
				&& getEnfermeroPorCedula(cedula) == null;
	}

	public boolean registroMedico(Medico medico){
		if(medico == null || medico.getSistema() != null || !cedulaDisponible(medico.getCedula())
				|| !nombreValido(medico.getNombre(), medico.getApellidoPaterno(), medico.getApellidoMaterno())
				|| !especialidades.containsKey(medico.getEspecialidad()))
			return false;
		medicos.add(medico);
		noMedicos++;
		medico.setSistema(this);
		return true;
	}

	public boolean registroEnfermero(Enfermero enfermero){
		if(enfermero == null || enfermero.getSistema() != null || !cedulaDisponible(enfermero.getCedula())
				|| !nombreValido(enfermero.getNombre(), enfermero.getApellidoPaterno(), enfermero.getApellidoMaterno())
				|| !especialidades.containsKey(enfermero.getEspecialidad()))
			return false;
		enfermeros.add(enfermero);
		noEnfermeros++;
		enfermero.setSistema(this);
		return true;
	}

	public boolean registroPaciente(Paciente paciente){
		if(paciente == null || paciente.getSistema() != null
				|| !nombreValido(paciente.getNombre(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno())
				|| !especialidades.containsKey(paciente.getEspecialidad()))
			return false;
		pacientes.add(paciente);
		noPacientes++;
		paciente.setSistema(this);
		return true;
	}

	public boolean tieneMedico(Medico medico){
		return medicos.contains(medico);
	}

	public boolean tieneEnfermero(Enfermero enfermero){
		return enfermeros.contains(enfermero);
	}

	public boolean tienePaciente(Paciente paciente){
		return pacientes.contains(paciente);
	}

	public Medico getMedico(int i){
		if(i < 0 || i >= noMedicos)
			return null;
		return medicos.get(i);
	}

	public Enfermero getEnfermero(int i){
		if(i < 0 || i >= noEnfermeros)
			return null;
		return enfermeros.get(i);
	}

	public Medico getMedicoPorCedula(int cedula){
		for(Medico medico : medicos)
			if(medico.getCedula() == cedula)
				return medico;
		return null;
	}

	public Enfermero getEnfermeroPorCedula(int cedula){
		for(Enfermero enfermero : enfermeros)
			if(enfermero.getCedula() == cedula)
				return enfermero;
		return null;
	}

	public Paciente getPaciente(int idPaciente){
		if(idPaciente < 1 || idPaciente > noPacientes)
			return null;
		return pacientes.get(idPaciente - 1);
	}

	public int getIdPaciente(Paciente paciente){
		return pacientes.indexOf(paciente) + 1;
	}

	private Medico buscarMedico(Paciente paciente){
		if(!tienePaciente(paciente) || paciente.getMedico() != null)
			return null;
		for(Medico medico : medicos){
			if(medico.getEspecialidad() == paciente.getEspecialidad() && medico.getNoPacientes() < 10
					&& medico.addPaciente(paciente))
				return medico;
		}
		return null;
	}

	public Medico asignarPaciente(PacienteGeneral paciente){
		return buscarMedico(paciente);
	}

	public Medico asignarPaciente(PacienteEspecialidad paciente){
		return buscarMedico(paciente);
	}

	private Paciente buscarPaciente(Medico medico){
		if(!tieneMedico(medico) || medico.getNoPacientes() >= 10)
			return null;
		for(Paciente paciente : pacientes){
			if(paciente.getMedico() == null && paciente.getEspecialidad() == medico.getEspecialidad()
					&& paciente.getEstado() == Paciente.Estado.EN_ESPERA && medico.addPaciente(paciente))
				return paciente;
		}
		return null;
	}

	public Paciente asignarPaciente(MedicoGeneral medico){
		return buscarPaciente(medico);
	}

	public Paciente asignarPaciente(MedicoEspecialista medico){
		return buscarPaciente(medico);
	}

	public Paciente asignarPaciente(Enfermero enfermero){
		if(!tieneEnfermero(enfermero) || enfermero.getNoPacientes() >= 3)
			return null;
		for(Paciente paciente : pacientes){
			if(paciente.getEstado() == Paciente.Estado.EN_TRATAMIENTO && paciente.getEnfermero() == null
					&& paciente.getEspecialidad() == enfermero.getEspecialidad() && enfermero.addPaciente(paciente))
				return paciente;
		}
		return null;
	}

	public Paciente asignarPaciente(EnfermeroEspecialista enfermero){
		return asignarPaciente((Enfermero) enfermero);
	}

	public Enfermero asignarEnfermero(Paciente paciente){
		if(!tienePaciente(paciente) || paciente.getEnfermero() != null
				|| paciente.getEstado() != Paciente.Estado.EN_TRATAMIENTO)
			return null;
		for(Enfermero enfermero : enfermeros){
			if(enfermero.getEspecialidad() == paciente.getEspecialidad() && enfermero.getNoPacientes() < 3
					&& enfermero.addPaciente(paciente))
				return enfermero;
		}
		return null;
	}
}
