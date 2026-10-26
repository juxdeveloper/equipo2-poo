package hospital.personal;
import hospital.pacientes.Paciente;
import hospital.pacientes.PacienteEspecialidad;
/** Médico que atiende una sola especialidad. */
public class MedicoEspecialista extends Medico {
    private final int especialidad;
    /** Crea un médico especialista con sus datos iniciales. */
    public MedicoEspecialista(String nombre, int cedula, int especialidad) {
        super(nombre, cedula);
        this.especialidad = especialidad;
    }
    @Override public int getEspecialidad() { return especialidad; }
    /** Consulta el nombre de la especialidad en el hospital. */
    public String consultarEspecialidad() { return getSistema().nombreEspecialidad(especialidad); }
    @Override protected Paciente solicitarPaciente() { return getSistema() == null ? null : getSistema().asignarPaciente(this); }
    @Override public boolean darConsulta(Paciente paciente) {
        return paciente instanceof PacienteEspecialidad && paciente.getEspecialidad() == especialidad && super.darConsulta(paciente);
    }
}
