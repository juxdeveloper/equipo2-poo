package personal;

import sistema.Sistema;
import pacientes.Paciente;

public class MedicoGeneral extends Medico {
	// Algun comportamiento unico para seguir la rubrica xd
	private int noConsultorio = 0;
	// por default 0, no importa mucho si no se asigna

	// Constructor
	public MedicoGeneral(String nombre, String apellidoPaterno, String apellidoMaterno, int cedula){
		super(nombre, apellidoPaterno, apellidoMaterno, cedula);
	}

	// Metodos
	public int getNoConsultorio(){
		return noConsultorio;
	}

	public void setNoConsultorio(int noConsultorio){
		if(noConsultorio < 1 || noConsultorio > 99)
			System.out.println("ERROR : No. de Consultorio invalido");
		else {
			System.out.println("INFO : No. de Consultorio " + noConsultorio + " asignado a");
			System.out.println("medico " + this.getNombre() + " correctamente");
			this.noConsultorio = noConsultorio;
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
	public int getEspecialidad(){
		return 0;
	}

	@Override
	public boolean darConsulta(Paciente paciente){
		if(!super.darConsulta(paciente))
			return false;
		System.out.println("INFO : Consulta general en consultorio " + noConsultorio);
		return true;
	}
}
