package personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import pacientes.Paciente;
import sistema.Sistema;
import interfaces.AtencionMedica;

public abstract class Medico implements AtencionMedica {

	// Atributos de todos los medicos sin importar si es general o especialista
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int cedula;
	private int noPacientes = 0;
	private Sistema sistema;
	private Paciente enConsulta = null;

	// Guardar la lista de pacientes
	private List <Paciente> pacientes = new ArrayList<>();

	// Constructor
	public Medico(String nombre, String apellidoPaterno, String apellidoMaterno, int cedula){
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
		if(sistema == null || !sistema.tieneMedico(this)
				|| (this.sistema != null && this.sistema != sistema))
			System.out.println("ERROR : No se puede cambiar el sistema del personal");
		else
			this.sistema = sistema;
	}

	public Sistema getSistema(){
		return sistema;
	}

	public boolean addPaciente(Paciente paciente){
		if(paciente == null || sistema == null || !sistema.tieneMedico(this)
				|| !sistema.tienePaciente(paciente) || paciente.getMedico() != null
				|| pacientes.contains(paciente) || noPacientes >= 10
				|| paciente.getEspecialidad() != getEspecialidad() || (paciente.getEstado() != Paciente.Estado.EN_ESPERA && paciente.getEstado() != Paciente.Estado.ATENDIDO)){
			System.out.println("ERROR : Paciente incompatible, ya asignado o sin cupo");
			return false;
		}
		pacientes.add(paciente);
		noPacientes++;
		paciente.setMedico(this);
		paciente.setEstado(Paciente.Estado.EN_ESPERA);
		return true;
	}

	public boolean tienePaciente(Paciente paciente){
		return pacientes.contains(paciente);
	}

	public boolean removePaciente(Paciente paciente){
		if(paciente == null || paciente.getEstado() != Paciente.Estado.ATENDIDO || !pacientes.remove(paciente))
			return false;
		noPacientes--;
		paciente.setMedico(null);
		if(enConsulta == paciente)
			enConsulta = null;
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
		if(sistema != null && sistema.registroMedico(this)){
			System.out.println("INFO : Medico registrado correctamente");
			this.setSistema(sistema);
			return true;
		} else {
			System.out.println("ERROR : No se pudo registrar al Medico");
			return false;
		}
	}

	public void setEnConsulta(Paciente enConsulta){
		if(enConsulta != null){
			darConsulta(enConsulta);
		} else if(this.enConsulta != null){
			System.out.println("ERROR : Finaliza la consulta indicando el tratamiento");
		}
	}

	public Paciente getEnConsulta(){
		return enConsulta;
	}


	// Dependiendo si es un medico general o especialista, su autorreferencia y su validacion
	// para encontrar pacientes cambia, sin embargo es lo único que cambia en solicitar paciente,
	// por lo que agrego una nueva función como en paciente donde busca y otra solo son los string y asignación
	public abstract int getEspecialidad();

	protected abstract Paciente buscarPaciente();
	
	public Paciente solicitarPaciente(){
		Paciente p = buscarPaciente();

		if(p != null){
			System.out.println("INFO : Paciente " + p.getNombre() + " asignado al medico");
			System.out.println(this.getNombre() + " correctamente");
			return p;
		} else {
			System.out.println("ERROR : No se pudo asignar un paciente al medico");
			return null;
		}
	}



	public boolean darConsulta(Paciente paciente){
		if(paciente == null || !pacientes.contains(paciente)
				|| paciente.getEstado() != Paciente.Estado.EN_ESPERA){
			System.out.println("ERROR : El medico no tiene asignado a dicho paciente");
			return false;
		} else if(getEnConsulta() == paciente){
			System.out.println("ERROR : El medico ya esta atendiendo a este paciente en este momento");
			return false;
		} else if(getEnConsulta() != null){
			System.out.println("ERROR : El medico ya esta atendiendo a alguien en este momento");
			return false;
		}
		// Cada clase debe tener su responsabilidad de cambiar sus datos
		// paciente.setEstado(Estado.EN_CONSULTA);
		enConsulta = paciente;
		paciente.setEstado(Paciente.Estado.EN_CONSULTA);
		System.out.println("INFO : Consulta iniciada con " + paciente.getNombreCompleto());
		return true;
	}

	public boolean darTratamiento(Paciente paciente){
		if(paciente == null || getEnConsulta() != paciente
				|| paciente.getEstado() != Paciente.Estado.EN_CONSULTA
				|| paciente.getTratamiento() == null){
			System.out.println("ERROR : Se requiere una consulta activa y un tratamiento indicado");
			return false;
		}
		System.out.println("INFO : Consulta finalizada para " + paciente.getNombreCompleto());
		if(paciente.getNecesitaMedicamento()){
			enConsulta = null;
			paciente.setEstado(Paciente.Estado.EN_TRATAMIENTO);
			System.out.println("INFO : Paciente pendiente de asignacion y tratamiento de enfermeria");
		} else {
			return paciente.finalizarAtencion();
		}
		return true;
	}

	public void verListaPacientes(boolean porApellido){
		System.out.println("El medico " + this.getNombre() + " tiene los pacientes:");

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
