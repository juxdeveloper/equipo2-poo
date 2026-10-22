package personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import pacientes.Paciente;
import sistema.Sistema;
import interfaces.AtencionMedica;

public class Enfermero implements AtencionMedica {

	// Atributos de todos los enfermeros sin importar si es general o especialista
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int cedula;
	private int noPacientes = 0;
	private Sistema sistema;
	private boolean turno = false;

	// Guardar la lista de pacientes
	private List <Paciente> pacientes = new ArrayList<>();

	// Constructor
	public Enfermero(String nombre, String apellidoPaterno, String apellidoMaterno, int cedula){
		setNombre(nombre);
		setApellidoPaterno(apellidoPaterno);
		setApellidoMaterno(apellidoMaterno);
		setCedula(cedula);
	}

	// Metodos
	public void setNombre(String nombre){
		if(nombre == null || nombre.isBlank())
			System.out.println("ERROR : El nombre está vacío");
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

	public void setTurno(boolean turno){
		if(turno == this.turno)
			System.out.println("ERROR : El nuevo turno es igual al anterior");
		else {
			this.turno = turno;
			System.out.print("INFO : Asignado el turno ");
			if(turno)
				System.out.println("vespertino");
			else
				System.out.println("matutino");
			System.out.println("\nAl enfermero " + this.getNombre());
		}
	}

	public int getEspecialidad(){
		return 0;
	}

	public boolean getTurno(){
		return turno;
	}

	public void setCedula(int cedula){
		if(sistema != null || cedula < 100000)
			System.out.println("ERROR : Cedula invalida o personal ya registrado");
		else
			this.cedula = cedula;
	}

	public int getCedula(){
		return cedula;
	}

	public void setNoPacientes(int noPacientes){
		if(noPacientes != pacientes.size())
			System.out.println("ERROR : El numero debe coincidir con los pacientes asignados");
		else
			this.noPacientes = noPacientes;
	}

	public int getNoPacientes(){
		return noPacientes;
	}

	public void setSistema(Sistema sistema){
		if(sistema == null || !sistema.tieneEnfermero(this)
				|| (this.sistema != null && this.sistema != sistema))
			System.out.println("ERROR : No se puede cambiar el sistema del personal");
		else
			this.sistema = sistema;
	}

	public Sistema getSistema(){
		return sistema;
	}

	public boolean addPaciente(Paciente paciente){
		if(paciente == null || sistema == null || !sistema.tieneEnfermero(this)
				|| !sistema.tienePaciente(paciente) || paciente.getEnfermero() != null
				|| pacientes.contains(paciente) || noPacientes >= 3
				|| paciente.getEspecialidad() != getEspecialidad() || paciente.getEstado() != Paciente.Estado.EN_TRATAMIENTO
				|| paciente.getMedico() == null || !paciente.getMedico().tienePaciente(paciente)){
			System.out.println("ERROR : Paciente incompatible, ya asignado o sin cupo");
			return false;
		}
		pacientes.add(paciente);
		noPacientes++;
		paciente.setEnfermero(this);
		return true;
	}

	public boolean tienePaciente(Paciente paciente){
		return pacientes.contains(paciente);
	}

	public boolean removePaciente(Paciente paciente){
		if(!pacientes.remove(paciente))
			return false;
		noPacientes--;
		paciente.setEnfermero(null);
		return true;
	}

	public Paciente getPaciente(int i){
		if(i < 0 || i >= pacientes.size()){
			System.out.println("ERROR : El indice se sale de la longitud de la lista de pacientes");
			return null;
		} else {
			return pacientes.get(i);
		}
	}

	public boolean registroEnSistema(Sistema sistema){
		if(sistema == null) {
			System.out.println("ERROR : El sistema no puede ser nulo");
			return false;
		}
		
		
		if(sistema.registroEnfermero(this)){
			System.out.println("INFO : Enfermero registrado correctamente");
			this.setSistema(sistema);
			return true;
		} else {
			System.out.println("ERROR : No se pudo registrar al Enfermero");
			return false;
		}
	}

	// Dependiendo si es un enfermero general o especialista, su autorreferencia y su validacion
	// para encontrar pacientes cambia, sin embargo es lo único que cambia en solicitar paciente,
	// por lo que agrego una nueva función como en paciente donde busca y otra solo son los string y asignación
	//
	// En este caso como no hay enfermeros generales pues si quiero una instancia de aqui, por eso la clase 
	// ya no es abstracta y puedo crear enfermeros normales
	//
	// Este metodo es para lo enfermeros normales y ya cada quien que extienda y quiera algo diferente, solo
	// le pone @Override
	protected Paciente buscarPaciente(){
		if(sistema == null){
			System.out.println("ERROR : El enfermero no esta registrado en el sistema");
			return null;
		}
		return sistema.asignarPaciente(this);
	}

	@Override
	public boolean darTratamiento(Paciente paciente) {
		if (paciente == null || !pacientes.contains(paciente)
				|| paciente.getEstado() != Paciente.Estado.EN_TRATAMIENTO) {
			System.out.println("ERROR : No se puede tratar a un paciente que no está asignado a este enfermero");
			return false;
		} else {
			System.out.println("INFO : Tratamiento proporcionado al paciente " + paciente.getNombre() + " por");
			System.out.println(this.getNombre());
			paciente.verTratamiento();
			return paciente.finalizarAtencion();
		}
	}

	public Paciente solicitarPaciente() {
		if (noPacientes >= 3) {
			System.out.println("ERROR : El enfermero ya alcanzó su cupo máximo de 3 pacientes");
			return null;
	        }
		
		Paciente p = buscarPaciente();
		if (p != null) {
			System.out.println("INFO : Paciente " + p.getNombre() + " asignado al enfermero " + this.getNombre());
			return p;
		} else {
			System.out.println("ERROR : No se pudo asignar un paciente al enfermero");
			return null;
		}
	}
	public void verListaPacientes(boolean porApellido){
		System.out.println("El enfermero " + this.getNombre() + " tiene los pacientes:");

		// Usado en la industria para ordenar toda la Lista con O(n logn)
		List<Paciente> lista = new ArrayList<>(pacientes);
		if(porApellido)
			lista.sort(Comparator.comparing(Paciente::getNombreCompleto, String.CASE_INSENSITIVE_ORDER));
		else
			lista.sort(Comparator.comparing(Paciente::getOrdenNombre, String.CASE_INSENSITIVE_ORDER));

		for(Paciente p : lista){
			System.out.println("---------------------------------------");
			System.out.println("ID " + sistema.getIdPaciente(p) + " | " + p.getNombreCompleto()
					+ " | " + sistema.getEspecialidad(p.getEspecialidad()) + " | " + p.getEstado());
		}
		System.out.println("---------------------------------------");
	}
}
