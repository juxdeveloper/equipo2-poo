package pacientes;

import personal.Medico;

public class PacienteEspecialidad extends Paciente {
	// Atributos
	private int especialidad = -1;


	// Constructor
	public PacienteEspecialidad(String nombre, String apellidoPaterno, String apellidoMaterno, int especialidad){
		super(nombre, apellidoPaterno, apellidoMaterno);
		setEspecialidad(especialidad);
	}

	// Metodos
	public void setEspecialidad(int especialidad){
		if(getSistema() != null || especialidad < 1 || especialidad > 99)
			System.out.println("ERROR : Especialidad no valida o paciente ya registrado");
		else
			this.especialidad = especialidad;
	}

	public int getEspecialidad(){
		return especialidad;
	}
	
	@Override
	protected Medico buscarMedico() {
		if(this.getEspecialidad() < 1){
			System.out.println("ERROR : No hay una especialidad especificada");
			return null;
		}
		return getSistema().asignarPaciente(this);
	}
}
