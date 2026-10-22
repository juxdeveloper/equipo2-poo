package personal;

import sistema.Sistema;
import pacientes.Paciente;

public class MedicoEspecialista extends Medico {
	// Como unico nuevo tiene obviamente la especialidad
	private int especialidad = -1;

	// Constructor
	public MedicoEspecialista(String nombre, String apellidoPaterno, String apellidoMaterno, int cedula, int especialidad){
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
			System.out.println("medico " + this.getNombre() + " correctamente");
			this.especialidad = especialidad;
		}
	}

	// Implementacion de solicitarPaciente, la cual es unica por el this
	@Override
	protected Paciente buscarPaciente(){
		Sistema s = this.getSistema();

		if(s == null){
			System.out.println("ERROR : El medico no está registrado en sistema");
			return null;
		}

		// El medico le pide al sistema que le asigne un paciente
		return s.asignarPaciente(this);
	}

	@Override
	public boolean darConsulta(Paciente paciente){
		if(!super.darConsulta(paciente))
			return false;
		System.out.println("INFO : Consulta de " + getSistema().getEspecialidad(especialidad));
		return true;
	}
}
