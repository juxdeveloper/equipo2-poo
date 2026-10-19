package hospital.pacientes;

import hospital.personal.Medico;

public class PacienteEspecialidad extends Paciente {
	// Atributos
	private int especialidad = 0;


	// Constructor
	public PacienteEspecialidad(String nombre, int especialidad){
		super(nombre);
		this.especialidad = especialidad;
	}

	// Metodos
	public void setEspecialidad(int especialidad){
		this.especialidad = especialidad;
	}

	public int getEspecialidad(){
		return especialidad;
	}
	
	@Override
	protected Medico buscarMedico() {
		if(this.getEspecialidad() == 0){
			System.out.println("ERROR : No hay una especialidad especificada");
			return null;
		}
		return getSistema().asignarPaciente(this);
	}
}
