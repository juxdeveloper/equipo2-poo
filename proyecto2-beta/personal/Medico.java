package hospital.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import hospital.pacientes.Paciente;
import hospital.sistema.Sistema;
import hospital.interfaces.AtencionMedica;

public abstract class Medico implements AtencionMedica {

	// Atributos de todos los medicos sin importar si es general o especialista
	private String nombre;
	private int cedula;
	private int noPacientes = 0;
	private Sistema sistema;
	private Paciente enConsulta = null;

	// Guardar la lista de pacientes
	private List <Paciente> pacientes = new ArrayList<>();

	// Constructor
	public Medico(String nombre, int cedula){
		this.nombre = nombre;
		this.cedula = cedula;
	}

	// Metodos
	public void setNombre(String nombre){
		if(nombre == null || nombre.isBlank())
			System.out.println("ERROR : El nombre está vacío"a);
		else
			this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}

	public void setCedula(int cedula){
		if(cedula < 100000)
			System.out.println("ERROR : La cedula debe tener al menos 6 digitos");
		else
			this.cedula = cedula;
	}

	public int getCedula(){
		return cedula;
	}

	public void setNoPacientes(int noPacientes){
		if(noPacientes < 1 || noPacientes > 10)
			System.out.println("ERROR : Deben ser entre 1 y 10 pacientes");
		else
			this.noPacientes = noPacientes;
	}

	public int getNoPacientes(){
		return noPacientes;
	}

	public void setSistema(Sistema sistema){
		if(sistema == null)
			System.out.println("ERROR : El sistema no debe ser nulo")
		else
			this.sistema = sistema;
	}

	public Sistema getSistema(){
		return sistema;
	}

	public void addPaciente(Paciente paciente){
		if(sistema == null)
			System.out.println("ERROR : El paciente no debe ser nulo");
		else if (noPacientes == 10)
			System.out.println("ERROR : El medico ya tiene 10 pacientes, el maximo");
		else
			pacientes.add(paciente);
	}

	public Paciente getPaciente(int i){
		if(i < 0 || i > 9)
			System.out.println("ERROR : El indice se sale de la longitud de la lista de pacientes");
		else
			pacientes.get(i);
	}

	public boolean registroEnSistema(Sistema sistema){
		if(sistema.registroMedico(this)){
			System.out.println("INFO : Medico registrado correctamente");
			return true;
		} else {
			System.out.println("ERROR : No se pudo registrar al Medico");
			return false;
		}
	}

	public void setEnConsulta(Paciente enConsulta){
		this.enConsulta = enConsulta;
	}

	public Paciente getEnConsulta(){
		return EnConsulta;
	}


	// Dependiendo si es un medico general o especialista, su autorreferencia y su validacion para encontrar pacientes cambia
	protected abstract Paciente solicitarPaciente();

	public boolean darConsulta(Paciente paciente){
		if(!pacientes.contains(paciente)){
			System.out.println("ERROR : El medico no tiene asignado a dicho paciente");
			return false;
		} else if(getEnConsulta() != null){
			System.out.println("ERROR : El medico ya esta atendiendo a alguien en este momento");
			return false;
		} else if(getEnConsulta() == paciente){
			System.out.println("ERROR : El medico ya esta atendiendo a este paciente en este momento");
			return false;
		}
		// Cada clase debe tener su responsabilidad de cambiar sus datos
		// paciente.setEstado(Estado.EN_CONSULTA);
		setEnConsulta = paciente;
		return true;
	}

	public boolean darTratamiento(Paciente paciente){
		if(getEnConsulta() != paciente){
			System.out.println("ERROR : No se puede tratar a un paciente que no está en consulta en este momento");
			return false;
		} else {
			System.out.println("INFO : Tratamiento proporcionado al paciente " + paciente.getNombre());
			return true
		}
	}

	public verListaPacientes(){
		System.out.println("El medico " + this.getNombre() + " tiene los pacientes:");

		// Usado en la industria para ordenar toda la Lista con O(n logn)
		pacientes.sort(Comparator.comparing(Paciente::getNombre));
		for(Paciente p : pacientes){
			System.out.println("---------------------------------------");
			System.out.println(p.key+1 + ". " + p.value);
		}
	}
