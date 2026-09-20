import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class Enfermero {
	// ATRIBUTOS
	String nombre;
	int cedula;
	int especialidad;
	int noPacientes = 0;


	// Atributo para almacenar la lista de pacientes asignados al enfermero
	ArrayList<Paciente> p = new ArrayList<>();

	// CONSTRUCTOR
	Enfermero(String nombre, int cedula, int especialidad){
		this.nombre = nombre;
		this.cedula = cedula;
		this.especialidad = especialidad;
		System.out.println("[add] Enfermero \"" + nombre + "\"");
	}


	// registroEnSistema, verListaPacientes, darTratamiento.
	// METODOS
	public void registroEnSistema(Sistema sistema){
		sistema.registroEnfermero(this);
	}

	public void verListaPacientes(){
		ArrayList<Paciente> Copia_Ordenada = new ArrayList<>(p);

		Collator comparadorEspañol = Collator.getInstance(Locale.of("es"));
    	comparadorEspañol.setStrength(Collator.PRIMARY);

		Copia_Ordenada.sort((a, b) -> comparadorEspañol.compare(b.nombre, a.nombre));

		System.out.println("El enfermero " + nombre + " tiene los siguientes pacientes asignados:");
		for(Paciente p : Copia_Ordenada){
			System.out.println("- " + p.nombre + " | Especialidad: " + p.especialidadAtencion);
		}
	}

	public boolean darTratamiento(Paciente paciente, String tratamiento){
		if((noPacientes < 3 && especialidad == paciente.especialidadAtencion) || p.contains(paciente)){
			paciente.tratamiento = tratamiento;
			if(!p.contains(paciente)){
				p.add(paciente);
				noPacientes++;
			}
			return true;
		}
		return false;
	}
}
