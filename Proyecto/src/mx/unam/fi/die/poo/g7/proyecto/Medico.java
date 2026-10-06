package mx.unam.fi.die.poo.g7.proyecto;

import java.util.ArrayList;

public class Medico {
	// --- ATRIBUTOS ---
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes = 0;
	
	// Extras
	// Está atendiendo a alguien el médico?
	Paciente atendiendo = null;
	
	// A qué hospital pertenece 
	Sistema sis = null;
	
	// Qué pacientes tiene asignados
	ArrayList<Paciente> p = new ArrayList<>();

	// CONSTRUCTOR
	Medico(String nombre, int cedula, int especialidad){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
	}
	
	// MÉTODOS
	//
	// Darlo de alta en x sistema
	public void registroEnSistema(Sistema s){
		sis = s;
		s.registroMedico(this);
	}
	
	// Si esta disponible intentar asignar paciente al medico
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
	public boolean darConsulta(Paciente paciente){
		if(!p.contains(paciente) || atendiendo != null) return false;
		atendiendo = paciente;
		paciente.setEstado(Paciente.Estado.EN_CONSULTA);
		return true;
	}
	
	// Si no estoy atendiendo al paciente, no le puedo dar tratamiento de lo contrario vamos a asignarlo a hospitalizacion
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
	public void verListaPacientes() {
		p.sort((a, b) -> a.getApellido().compareToIgnoreCase(b.getApellido()));
		for (Paciente paciente : p)
			System.out.println(paciente.getApellido() + " " + paciente.getNombre() + " - Especialidad: " + paciente.getEspecialidad());
	}

	// extra
	// GETTERS Y SETTERS
	public int getEspecialidad(){
		return especialidad;
	}

	public int getNoPacientes(){
		return noPacientes;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public Paciente getAtendiendo() {
		return atendiendo;
	}

	public ArrayList<Paciente> getPacientesAsignados() {
		return p;
	}
}
