public class Paciente {
	String nombre;
	int especialidadAtencion;

	Paciente(String nombre, int especialidadAtencion){
		this.nombre = nombre;
		this.especialidadAtencion = especialidadAtencion;
	}
	//registroEnSistema, solicitarConsulta, verTratamiento.
	public void registroEnSistema(Sistema sistema){
		sistema.registroPaciente(this);
	}

	bool solicitarConsulta(){

}
