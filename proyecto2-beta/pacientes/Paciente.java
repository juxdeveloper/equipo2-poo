package hospital.pacientes;

import hospital.personal.*;
import hospital.sistema.Sistema;

public abstract class Paciente {
	public enum Estado {
		EN_ESPERA, EN_CONSULTA, EN_TRATAMIENTO;
	}

	// Atributos
	private String nombre;
	private String tratamiento;
	private Estado e = Estado.EN_ESPERA;

	private Sistema sistema;
	private Medico medico;
	private Enfermero enfermero;

	// Constructor
	public Paciente(String nombre){
		this.nombre = nombre;
	}

	// Metodos
	
	// GETTERS Y SETTERS
	// Se valida dentro de cada setter
	public void setNombre(String nombre){
		if(nombre == null || nombre.isBlank())
			System.out.println("ERROR : Nombre vacio");
		else 
			this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}

	public void setTratamiento(String tratamiento){
		if(tratamiento == null || tratamiento.isBlank())
			System.out.println("ERROR : Tratamiento vacio");
		else 
			this.tratamiento = tratamiento;
	}
		
	public void verTratamiento(){
		System.out.println("TRATAMIENTO");
		System.out.println(tratamiento);
	}

	public void setEstado(Estado e){
		this.e = e;
	}

	public Estado getEstado(){
		return e;
	}

	public void setSistema(Sistema sistema){
		if(sistema == null)
			System.out.println("ERROR : Sistema nulo no es valido");
		else
			this.sistema = sistema;
	}

	public Sistema getSistema(){
		return sistema;
	}

	public void setMedico(Medico medico){
		if(medico == null)
			System.out.println("ERROR : Medico nulo no es valido");
		else
			this.medico = medico;
	}

	public Medico getMedico(){
		return medico;
	}

	public void setEnfermero(Enfermero enfermero){
		if(enfermero == null)
			System.out.println("ERROR : Enfermero nulo no es valido");
		else
			this.enfermero = enfermero;
	}

	public Enfermero getEnfermero(){
		return enfermero;
	}

	// Esto es igual en ambos tipos de pacientes
	public boolean registroEnSistema(Sistema sistema){
		if(sistema.registroPaciente(this)){
			this.setSistema(sistema);
			System.out.println("INFO : Paciente registrado");
			return true;
		} else {
			System.out.println("ERROR : No se pudo asignar paciente");
			return false;
		}
	}

	// Pendiente de implementar segun la clase que extienda esta clase abstracta
	//public abstract boolean solicitarConsulta();
	
	
	protected abstract Medico buscarMedico();
	// Depués me di cuenta de que solicitarconsulta es igual en ambos, solo cambia qué es el this (la autorreferencia) en cada uno
	// y el string de que ya se asignó médico general / especialista. Por eso como es lo único que cambia, el método
	//  abstracto solo será para buscar como tal y dicho string
	public boolean solicitarConsulta(){
		if(getSistema() == null) {
			System.out.println("ERROR: Paciente no registrado en el sistema");
			return false;
		}

		// Buscar un medico para el paciente y si lo encuentra asignarlo a, cambiando su estado a consulta
		Medico medico_tmp = buscarMedico();
		if(medico_tmp != null){
			System.out.println("INFO : Paciente en consulta con el medico " + medico_tmp.getNombre());
			setMedico(medico_tmp);
			setEstado(Estado.EN_CONSULTA);
                        return true;
                } else {
                        System.out.println("ERROR : No se pudo asignar paciente a medico");
                        return false;
                }
	}
}
