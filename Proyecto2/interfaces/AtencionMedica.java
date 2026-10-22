package hospital.interfaces;
import hospital.pacientes.Paciente;
/** Atención que pueden proporcionar médicos y enfermeros. */
public interface AtencionMedica {
    /** Intenta proporcionar tratamiento al paciente. */
    boolean darTratamiento(Paciente paciente);
}
