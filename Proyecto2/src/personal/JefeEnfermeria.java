package personal;

import pacientes.Paciente;

public class JefeEnfermeria extends Enfermero {

	// Atributos
	//
	// La especialidad a la que está a cargo el enfermero
	private int AreaEspecialidad = -1;


	// Constructor
	public JefeEnfermeria(String nombre, String apellidoPaterno, String apellidoMaterno, int cedula, int AreaEspecialidad){
		super(nombre, apellidoPaterno, apellidoMaterno, cedula);
		setAreaEspecialidad(AreaEspecialidad);
	}

	// Metodos
	//
	// Para el area a la que está a cargo el enfermero jefe
	public void setAreaEspecialidad(int AreaEspecialidad){
		if(getSistema() != null || AreaEspecialidad < 0 || AreaEspecialidad > 99)
			System.out.println("ERROR : Ingrese una especialidad valida");
		else {
			System.out.println("INFO : Asignada correctamente la especialidad/area del enfermero");
			this.AreaEspecialidad = AreaEspecialidad;
		}
	}

	public int getAreaEspecialidad(){
		return AreaEspecialidad;
	}	

	// asigna pacientes
	public boolean cambiarEnfermero(Enfermero actual, Enfermero nuevo, Paciente paciente){
		if(!puedeCoordinar(actual) || !puedeCoordinar(nuevo) || actual == nuevo
				|| paciente == null || paciente.getEnfermero() != actual
				|| !actual.tienePaciente(paciente) || nuevo.getNoPacientes() >= 3
				|| paciente.getEstado() != Paciente.Estado.EN_TRATAMIENTO){
			System.out.println("ERROR : Transferencia incompatible o enfermero sin cupo");
			return false;
		}
		actual.removePaciente(paciente);
		if(!nuevo.addPaciente(paciente)){
			actual.addPaciente(paciente);
			return false;
		}
		System.out.println("INFO : Paciente transferido de " + actual.getNombreCompleto()
				+ " a " + nuevo.getNombreCompleto());
		return true;
	}

	// cambia el turno de los enfermeros
	public void cambiarTurno(Enfermero enfermero, boolean turno){
		if(puedeCoordinar(enfermero))
			enfermero.setTurno(turno);
		else
			System.out.println("ERROR : El enfermero no pertenece al hospital y area del jefe");
	}

	private boolean puedeCoordinar(Enfermero enfermero){
		return getSistema() != null && getSistema().tieneEnfermero(this)
				&& enfermero != null && getSistema().tieneEnfermero(enfermero)
				&& enfermero.getEspecialidad() == AreaEspecialidad;
	}

	@Override
	public int getEspecialidad(){
		return AreaEspecialidad;
	}
}
