package hospital.personal;
import java.util.ArrayList;
import java.util.List;
import hospital.interfaces.AtencionMedica;
import hospital.pacientes.Paciente;
import hospital.sistema.Sistema;
/** Personal de enfermería con turno y hasta tres pacientes. */
public abstract class Enfermero implements AtencionMedica {
    private String nombre;
    private final int cedula;
    private boolean turno;
    private Sistema sistema;
    private final List<Paciente> pacientes = new ArrayList<>();
    /** Crea un enfermero con sus datos iniciales. */
    public Enfermero(String nombre, int cedula) {
        this.nombre = (nombre != null && !nombre.isBlank()) ? nombre.trim() : "Enfermero";
        this.cedula = cedula;
    }
    /** Obtiene nombre. */
    public String getNombre() { return nombre; }
    /** Actualiza nombre. */
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }
    /** Obtiene cedula. */
    public int getCedula() { return cedula; }
    /** Obtiene turno. */
    public boolean getTurno() { return turno; }
    /** Actualiza turno. */
    public void setTurno(boolean turno) { this.turno = turno; }
    /** Obtiene el nombre del turno. */
    public String nombreTurno() { return turno ? "Vespertino" : "Matutino"; }
    /** Obtiene el número de pacientes. */
    public int getNoPacientes() { return pacientes.size(); }
    /** Obtiene una copia no modificable de los pacientes. */
    public List<Paciente> getPacientes() { return List.copyOf(pacientes); }
    /** Obtiene sistema. */
    public Sistema getSistema() { return sistema; }
    /** Actualiza sistema. */
    public void setSistema(Sistema sistema) {
        if (sistema != null && (this.sistema == null || this.sistema == sistema)) {
            this.sistema = sistema;
        }
    }
    /** Obtiene especialidad. */
    public abstract int getEspecialidad();
    /** Realiza la operación puedeAtender. */
    protected abstract boolean puedeAtender(Paciente paciente);
    /** Registra el objeto en el hospital. */
    public boolean registroEnSistema(Sistema sistema) { return sistema != null && sistema.registroEnfermero(this); }
    /** Pide autorización al supervisor cuando el paciente la necesita. */
    public boolean solicitarAutorizacion(Paciente paciente) {
        if (paciente == null || sistema == null || paciente.getSistema() != sistema
                || paciente.getEspecialidad() != getEspecialidad()) return false;
        if (!paciente.getNecesitaMedicamento() || paciente.getMedicamentoAutorizado()) return true;
        JefeEnfermeria jefe = sistema.buscarJefe(getEspecialidad());
        return jefe != null && jefe.autorizarMedicamentos(paciente);
    }
    @Override public boolean darTratamiento(Paciente paciente) {
        if (!puedeAtender(paciente) || pacientes.size() >= 3 || pacientes.contains(paciente)
                || paciente.getEnfermero() != null || paciente.getEstado() != Paciente.Estado.EN_CONSULTA
                || paciente.getMedico() == null || paciente.getMedico().getEnConsulta() != paciente
                || !solicitarAutorizacion(paciente)) return false;
        pacientes.add(paciente);
        paciente.setEnfermero(this);
        paciente.setEstado(Paciente.Estado.EN_TRATAMIENTO);
        paciente.setTratamiento("Tratamiento hospitalario");
        return true;
    }
    /** Traslada un paciente solamente con un supervisor de su área. */
    public boolean trasladarPaciente(Paciente paciente, Enfermero nuevo, JefeEnfermeria jefe) {
        if (jefe == null || !jefe.supervisa(this) || nuevo == this || !jefe.supervisa(nuevo)
                || !pacientes.contains(paciente) || paciente.getEnfermero() != this
                || paciente.getEstado() != Paciente.Estado.EN_TRATAMIENTO
                || !nuevo.puedeAtender(paciente) || nuevo.getNoPacientes() >= 3) return false;
        pacientes.remove(paciente);
        nuevo.pacientes.add(paciente);
        paciente.setEnfermero(nuevo);
        return true;
    }
    /** Muestra apellidos, nombres y especialidad de Z a A. */
    public void verListaPacientes() {
        List<Paciente> lista = new ArrayList<>(pacientes);
        lista.sort((a, b) -> b.getNombre().compareToIgnoreCase(a.getNombre()));
        for (Paciente paciente : lista) System.out.println(paciente);
    }
}
