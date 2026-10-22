package pacientes;

import sistema.Sistema;
import personal.Medico;

public class PacienteGeneral extends Paciente {
	// Atributo exclusivo
	private boolean esUrgencia;

	// Constructor
	public PacienteGeneral(String nombre, String apellidoPaterno, String apellidoMaterno, boolean esUrgencia){
		// Paciente.nombre = nombre; no es posible, pues nombre es privado, pero como es
		// algo de la superclase (Paciente), entonces se usa
		super(nombre, apellidoPaterno, apellidoMaterno);
		this.esUrgencia = esUrgencia;
	}

	// Metodos
	public void setEsUrgencia(boolean esUrgencia){
		this.esUrgencia = esUrgencia;
	}

	public boolean getEsUrgencia(){
		return esUrgencia;
	}

	// Es el metodo que debo de implementar de la clase abstracta aqui
	// No es necesario override, pues es implicito que se debe implementar pero se coloca
	// para mejorar lo enetendible del codigo
	@Override
	protected Medico buscarMedico() {
		if(esUrgencia)
			System.out.println("INFO : El paciente requiere atencion general de urgencia");
		return getSistema().asignarPaciente(this);
	}

	@Override
	public int getEspecialidad(){
		return 0;
	}
}
