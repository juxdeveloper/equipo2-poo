package hospital.personal;
import hospital.pacientes.Paciente;
/** Enfermero que atiende pacientes de su especialidad, incluida medicina general. */
public class EnfermeroEspecialista extends Enfermero {
    private final int especialidad;
    /** Crea un enfermero especialista con sus datos iniciales. */
    public EnfermeroEspecialista(String nombre, int cedula, int especialidad) {
        super(nombre, cedula);
        if (especialidad < 1 || especialidad > 99) throw new IllegalArgumentException("Especialidad inválida.");
        this.especialidad = especialidad;
    }
    @Override public int getEspecialidad() { return especialidad; }
    /** Consulta el nombre de la especialidad en el hospital. */
    public String consultarEspecialidad() { return getSistema().nombreEspecialidad(especialidad); }
    @Override protected boolean puedeAtender(Paciente paciente) {
        return paciente != null && getSistema() != null && paciente.getSistema() == getSistema()
                && paciente.getEspecialidad() == especialidad;
    }
}
