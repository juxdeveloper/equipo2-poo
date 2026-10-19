package hospital.pacientes;

import hospital.sistema.Sistema;
import hospital.personal.Medico;

public class PacienteGeneral extends Paciente {
	// Atributo exclusivo
	private boolean esUrgencia;

	// Constructor
	public PacienteGeneral(String nombre, boolean esUrgencia){
		// Paciente.nombre = nombre; no es posible, pues nombre es privado, pero como es
		// algo de la superclase (Paciente), entonces se usa
		super(nombre);
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
		return getSistema().asignarPaciente(this);
	}
}
