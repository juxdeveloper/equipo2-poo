package pacientes;

import personal.*;
import sistema.Sistema;

public abstract class Paciente {
	public enum Estado {
		EN_ESPERA, EN_CONSULTA, EN_TRATAMIENTO, ATENDIDO;
	}

	// Atributos
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tratamiento;
	private Estado e = Estado.EN_ESPERA;

	private Sistema sistema;
	private Medico medico;
	private Enfermero enfermero;

	// Saber si necesita medicamento o no
	private boolean necesitaMedicamento = false;

	// Constructor
	public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno){
		setNombre(nombre);
		setApellidoPaterno(apellidoPaterno);
		setApellidoMaterno(apellidoMaterno);
	}

	// Metodos
	
	// GETTERS Y SETTERS
	// Se valida dentro de cada setter
	public void setNombre(String nombre){
		if(nombre == null || nombre.isBlank())
			System.out.println("ERROR : Nombre vacio");
		else 
			this.nombre = nombre.trim();
	}

	public String getNombre(){
		return nombre;
	}

	public void setApellidoPaterno(String apellidoPaterno){
		if(apellidoPaterno == null || apellidoPaterno.isBlank())
			System.out.println("ERROR : Apellido paterno vacio");
		else
			this.apellidoPaterno = apellidoPaterno.trim();
	}

	public String getApellidoPaterno(){
		return apellidoPaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno){
		if(apellidoMaterno == null || apellidoMaterno.isBlank())
			System.out.println("ERROR : Apellido materno vacio");
		else
			this.apellidoMaterno = apellidoMaterno.trim();
	}

	public String getApellidoMaterno(){
		return apellidoMaterno;
	}

	public String getNombreCompleto(){
		return apellidoPaterno + " " + apellidoMaterno + " " + nombre;
	}

	public String getOrdenNombre(){
		return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
	}

	public void setNecesitaMedicamento(boolean necesitaMedicamento){
		if(e == Estado.EN_CONSULTA)
			this.necesitaMedicamento = necesitaMedicamento;
		else
			System.out.println("ERROR : El medicamento se indica durante la consulta");
	}

	public boolean getNecesitaMedicamento(){
		return necesitaMedicamento;
	}

	public void setTratamiento(String tratamiento){
		if(e != Estado.EN_CONSULTA || tratamiento == null || tratamiento.isBlank())
			System.out.println("ERROR : Tratamiento vacio");
		else 
			this.tratamiento = tratamiento.trim();
	}
		
	public String getTratamiento(){
		return tratamiento;
	}

	public void verTratamiento(){
		System.out.println("TRATAMIENTO");
		System.out.println(tratamiento == null ? "Sin tratamiento indicado" : tratamiento);
	}

	public void setEstado(Estado e){
		if(e == Estado.EN_ESPERA && (this.e == Estado.EN_ESPERA || this.e == Estado.ATENDIDO)
				&& medico != null && medico.getEnConsulta() != this)
			this.e = e;
		else if(e == Estado.EN_CONSULTA && this.e == Estado.EN_ESPERA
				&& medico != null && medico.getEnConsulta() == this)
			this.e = e;
		else if(e == Estado.EN_TRATAMIENTO && this.e == Estado.EN_CONSULTA && medico != null
				&& medico.getEnConsulta() != this && tratamiento != null && necesitaMedicamento)
			this.e = e;
		else
			System.out.println("ERROR : Cambio de estado no valido");
	}

	public Estado getEstado(){
		return e;
	}

	public void setSistema(Sistema sistema){
		if(sistema == null || !sistema.tienePaciente(this)
				|| (this.sistema != null && this.sistema != sistema))
			System.out.println("ERROR : No se puede cambiar el sistema del paciente");
		else
			this.sistema = sistema;
	}

	public Sistema getSistema(){
		return sistema;
	}

	public void setMedico(Medico medico){
		if(medico == null){
			if(this.medico == null || !this.medico.tienePaciente(this))
				this.medico = null;
		} else if(this.medico == null && medico.getSistema() == sistema && medico.tienePaciente(this)){
			this.medico = medico;
			tratamiento = null;
			necesitaMedicamento = false;
		} else {
			System.out.println("ERROR : Asignacion de medico no valida");
		}
	}

	public Medico getMedico(){
		return medico;
	}

	public void setEnfermero(Enfermero enfermero){
		if(enfermero == null){
			if(this.enfermero == null || !this.enfermero.tienePaciente(this))
				this.enfermero = null;
		} else if(this.enfermero == null && enfermero.getSistema() == sistema && enfermero.tienePaciente(this)){
			this.enfermero = enfermero;
		} else {
			System.out.println("ERROR : Asignacion de enfermero no valida");
		}
	}

	public Enfermero getEnfermero(){
		return enfermero;
	}

	// Esto es igual en ambos tipos de pacientes
	public boolean registroEnSistema(Sistema sistema){
		if(sistema != null && sistema.registroPaciente(this)){
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
	
	
	public abstract int getEspecialidad();

	protected abstract Medico buscarMedico();
	// Depués me di cuenta de que solicitarconsulta es igual en ambos, solo cambia qué es el this (la autorreferencia) en cada uno
	// y el string de que ya se asignó médico general / especialista. Por eso como es lo único que cambia, el método
	//  abstracto solo será para buscar como tal y dicho string
	public boolean solicitarConsulta(){
		if(getSistema() == null || medico != null || enfermero != null) {
			System.out.println("ERROR : Paciente no registrado o con una atencion ya asignada");
			return false;
		}

		// Buscar un medico para el paciente y si lo encuentra asignarlo a, cambiando su estado a consulta
		Medico medico_tmp = buscarMedico();
		if(medico_tmp != null){
			System.out.println("INFO : Paciente asignado y en espera del medico " + medico_tmp.getNombreCompleto());
                        return true;
                } else {
                        System.out.println("ERROR : No se pudo asignar paciente a medico");
                        return false;
                }
	}

	public boolean finalizarAtencion(){
		if((e != Estado.EN_CONSULTA && e != Estado.EN_TRATAMIENTO) || tratamiento == null || medico == null || !medico.tienePaciente(this)
				|| (necesitaMedicamento && (e != Estado.EN_TRATAMIENTO || enfermero == null))){
			System.out.println("ERROR : No hay una atencion con tratamiento para finalizar");
			return false;
		}
		e = Estado.ATENDIDO;
		if(enfermero != null)
			enfermero.removePaciente(this);
		if(medico != null)
			medico.removePaciente(this);
		System.out.println("INFO : Atencion finalizada y cupos liberados");
		return true;
	}
}
