package hospital.personal;
import java.util.ArrayList;
import java.util.List;
import hospital.interfaces.AtencionMedica;
import hospital.pacientes.Paciente;
import hospital.sistema.Sistema;
/** Médico con hasta diez pacientes y una sola consulta activa. */
public abstract class Medico implements AtencionMedica {
    private String nombre;
    private final int cedula;
    private Sistema sistema;
    private Paciente enConsulta;
    private final List<Paciente> pacientes = new ArrayList<>();
    /** Crea un médico con sus datos iniciales. */
    public Medico(String nombre, int cedula) {
        setNombre(nombre);
        if (cedula < 100000) throw new IllegalArgumentException("La cédula debe tener al menos seis dígitos.");
        this.cedula = cedula;
    }
    /** Obtiene nombre. */
    public String getNombre() { return nombre; }
    /** Actualiza nombre. */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre está vacío.");
        this.nombre = nombre.trim();
    }
    /** Obtiene cedula. */
    public int getCedula() { return cedula; }
    /** Obtiene el número de pacientes. */
    public int getNoPacientes() { return pacientes.size(); }
    /** Obtiene una copia no modificable de los pacientes. */
    public List<Paciente> getPacientes() { return List.copyOf(pacientes); }
    /** Obtiene paciente. */
    public Paciente getPaciente(int i) { return pacientes.get(i); }
    /** Obtiene sistema. */
    public Sistema getSistema() { return sistema; }
    /** Actualiza sistema. */
    public void setSistema(Sistema sistema) {
        if (sistema == null || this.sistema != null && this.sistema != sistema) throw new IllegalArgumentException("Hospital incompatible.");
        this.sistema = sistema;
    }
    /** Obtiene el paciente que está en consulta. */
    public Paciente getEnConsulta() { return enConsulta; }
    /** Obtiene especialidad. */
    public abstract int getEspecialidad();
    /** Registra el objeto en el hospital. */
    public boolean registroEnSistema(Sistema sistema) { return sistema != null && sistema.registroMedico(this); }
    /** Agrega un paciente compatible sin superar el cupo. */
    public boolean addPaciente(Paciente paciente) {
        if (paciente == null || sistema == null || paciente.getSistema() != sistema
                || paciente.getEspecialidad() != getEspecialidad() || pacientes.size() >= 10
                || paciente.getMedico() != null || pacientes.contains(paciente)) return false;
        pacientes.add(paciente);
        paciente.setMedico(this);
        return true;
    }
    /** Comprueba si el paciente está en la lista del médico. */
    public boolean tienePaciente(Paciente paciente) { return pacientes.contains(paciente); }
    /** Busca un paciente compatible en el hospital. */
    protected abstract Paciente solicitarPaciente();
    /** Solicita un paciente en espera de la misma especialidad. */
    public boolean solicitarPacienteDisponible() { return solicitarPaciente() != null; }
    /** Inicia una consulta cuando el médico y el paciente están disponibles. */
    public boolean darConsulta(Paciente paciente) {
        if (!pacientes.contains(paciente) || enConsulta != null || paciente.getEstado() != Paciente.Estado.EN_ESPERA) return false;
        enConsulta = paciente;
        paciente.setEstado(Paciente.Estado.EN_CONSULTA);
        return true;
    }
    @Override public boolean darTratamiento(Paciente paciente) {
        if (paciente == null || enConsulta != paciente || !sistema.asignarPaciente(paciente)) return false;
        enConsulta = null;
        return true;
    }
    /** Muestra apellidos, nombres y especialidad de Z a A. */
    public void verListaPacientes() {
        List<Paciente> lista = new ArrayList<>(pacientes);
        lista.sort((a, b) -> b.getNombre().compareToIgnoreCase(a.getNombre()));
        for (Paciente paciente : lista) System.out.println(paciente);
    }
}
