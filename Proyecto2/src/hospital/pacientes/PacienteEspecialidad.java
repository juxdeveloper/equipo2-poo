package hospital.pacientes;
import hospital.personal.Medico;
/** Paciente que requiere una especialidad del catálogo. */
public class PacienteEspecialidad extends Paciente {
    private final int especialidad;
    /** Crea un paciente de especialidad con sus datos iniciales. */
    public PacienteEspecialidad(String nombre, int especialidad) {
        super(nombre);
        if (especialidad < 2 || especialidad > 99) throw new IllegalArgumentException("Especialidad inválida.");
        this.especialidad = especialidad;
    }
    @Override public int getEspecialidad() { return especialidad; }
    /** Consulta el nombre de la especialidad en el hospital. */
    public String consultarEspecialidad() { return getSistema().nombreEspecialidad(especialidad); }
    @Override protected Medico buscarMedico() { return getSistema().asignarPaciente(this); }
}
