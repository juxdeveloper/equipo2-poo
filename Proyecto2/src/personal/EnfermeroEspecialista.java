package personal;

import sistema.Sistema;
import pacientes.Paciente;

public class EnfermeroEspecialista extends Enfermero {
	// Como unico nuevo tiene obviamente la especialidad
	private int especialidad = -1;

	// Constructor
	public EnfermeroEspecialista(String nombre, String apellidoPaterno, String apellidoMaterno, int cedula, int especialidad){
		super(nombre, apellidoPaterno, apellidoMaterno, cedula);
		setEspecialidad(especialidad);
	}

	// Metodos
	public int getEspecialidad(){
		return especialidad;
	}

	public void setEspecialidad(int especialidad){
		if(getSistema() != null || especialidad < 1 || especialidad > 99)
			System.out.println("ERROR : No. de especialidad invalida");
		else {
			System.out.println("INFO : No. de especialidad " + especialidad + " asignado a");
			System.out.println("enfermero " + this.getNombre() + " correctamente");
			this.especialidad = especialidad;
		}
	}

	// Implementacion de solicitarPaciente, la cual es unica por el this
	@Override
	protected Paciente buscarPaciente(){
		Sistema sistema = this.getSistema();

		if(sistema == null){
			System.out.println("ERROR : El enfermero no esta registrado en el sistema");
			return null;
		}
		return sistema.asignarPaciente(this);
	}


}
