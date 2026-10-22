package hospital.personal;
import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteGeneral;
/** Médico responsable de la consulta general. */
public class MedicoGeneral extends Medico {
    private final String servicio = "Consulta general";
    /** Crea un médico general con sus datos iniciales. */
    public MedicoGeneral(String nombre, int cedula) { super(nombre, cedula); }
    /** Obtiene servicio. */
    public String getServicio() { return servicio; }
    @Override public int getEspecialidad() { return 1; }
    @Override protected Paciente solicitarPaciente() { return getSistema() == null ? null : getSistema().asignarPaciente(this); }
    @Override public boolean darConsulta(Paciente paciente) { return paciente instanceof PacienteGeneral && super.darConsulta(paciente); }
}
