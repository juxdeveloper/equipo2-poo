package hospital.pacientes;
import hospital.personal.Medico;
/** Paciente de medicina general. */
public class PacienteGeneral extends Paciente {
    private String motivoConsulta;
    /** Crea un paciente general con sus datos iniciales. */
    public PacienteGeneral(String nombre) { this(nombre, "Revisión general"); }
    /** Crea un paciente general con sus datos iniciales. */
    public PacienteGeneral(String nombre, String motivo) {
        super(nombre);
        this.motivoConsulta = (motivo != null && !motivo.isBlank()) ? motivo.trim() : "Revisión general";
    }
    /** Obtiene el motivo de consulta. */
    public String getMotivoConsulta() { return motivoConsulta; }
    /** Actualiza el motivo de consulta. */
    public void setMotivoConsulta(String motivo) {
        if (motivo != null && !motivo.isBlank()) {
            motivoConsulta = motivo.trim();
        }
    }
    @Override public int getEspecialidad() { return 1; }
    @Override protected Medico buscarMedico() { return getSistema().asignarPaciente(this); }
}
