package hospital.pacientes;
import hospital.personal.*;
import hospital.sistema.Sistema;
/** Datos y estado de un paciente del hospital. */
public abstract class Paciente {
    /** Etapas de atención del paciente. */
    public enum Estado { EN_ESPERA, EN_CONSULTA, EN_TRATAMIENTO }
    private String nombre;
    private String tratamiento;
    private Estado estado = Estado.EN_ESPERA;
    private Sistema sistema;
    private Medico medico;
    private Enfermero enfermero;
    private boolean necesitaMedicamento = Math.random() < 0.5;
    private boolean medicamentoAutorizado;
    /** Crea un paciente en espera, con necesidad de medicamento aleatoria. */
    public Paciente(String nombre) { setNombre(nombre); }
    /** Obtiene nombre. */
    public String getNombre() { return nombre; }
    /** Actualiza nombre. */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre está vacío.");
        this.nombre = nombre.trim();
    }
    /** Obtiene tratamiento. */
    public String getTratamiento() { return tratamiento; }
    /** Actualiza tratamiento. */
    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.isBlank()) throw new IllegalArgumentException("El tratamiento está vacío.");
        this.tratamiento = tratamiento.trim();
    }
    /** Muestra el tratamiento indicado. */
    public void verTratamiento() { System.out.println(tratamiento == null ? "Sin tratamiento indicado." : tratamiento); }
    /** Obtiene estado. */
    public Estado getEstado() { return estado; }
    /** Actualiza estado. */
    public void setEstado(Estado estado) {
        if (estado == null) throw new IllegalArgumentException("El estado no puede ser nulo.");
        this.estado = estado;
    }
    /** Obtiene sistema. */
    public Sistema getSistema() { return sistema; }
    /** Actualiza sistema. */
    public void setSistema(Sistema sistema) {
        if (sistema == null || this.sistema != null && this.sistema != sistema)
            throw new IllegalArgumentException("El paciente ya pertenece a otro hospital o el hospital es nulo.");
        this.sistema = sistema;
    }
    /** Obtiene medico. */
    public Medico getMedico() { return medico; }
    /** Actualiza medico. */
    public void setMedico(Medico medico) {
        if (medico == null || medico.getSistema() != sistema || medico.getEspecialidad() != getEspecialidad())
            throw new IllegalArgumentException("Médico incompatible.");
        this.medico = medico;
    }
    /** Obtiene enfermero. */
    public Enfermero getEnfermero() { return enfermero; }
    /** Actualiza enfermero. */
    public void setEnfermero(Enfermero enfermero) {
        if (enfermero == null || enfermero instanceof JefeEnfermeria || enfermero.getSistema() != sistema
                || enfermero.getEspecialidad() != getEspecialidad()) throw new IllegalArgumentException("Enfermero incompatible.");
        this.enfermero = enfermero;
    }
    /** Indica si el paciente necesita medicamento controlado. */
    public boolean getNecesitaMedicamento() { return necesitaMedicamento; }
    /** Permite corregir el requerimiento antes de iniciar la atención. */
    public void setNecesitaMedicamento(boolean necesita) {
        if (estado != Estado.EN_ESPERA) throw new IllegalArgumentException("El paciente ya inició su atención.");
        necesitaMedicamento = necesita;
        medicamentoAutorizado = false;
    }
    /** Indica si el supervisor autorizó el medicamento. */
    public boolean getMedicamentoAutorizado() { return medicamentoAutorizado; }
    /** Guarda la autorización de un supervisor de la misma área. */
    public boolean autorizarMedicamento(JefeEnfermeria jefe) {
        if (sistema == null || !necesitaMedicamento || jefe == null || jefe.getSistema() != sistema
                || jefe.getEspecialidad() != getEspecialidad() || !sistema.getEnfermeros().contains(jefe)) return false;
        medicamentoAutorizado = true;
        return true;
    }
    /** Obtiene especialidad. */
    public abstract int getEspecialidad();
    /** Realiza la operación buscarMedico. */
    protected abstract Medico buscarMedico();
    /** Registra el objeto en el hospital. */
    public boolean registroEnSistema(Sistema sistema) { return sistema != null && sistema.registroPaciente(this); }
    /** Busca un médico y solicita consulta sin perder una asignación previa. */
    public boolean solicitarConsulta() {
        if (sistema == null || estado != Estado.EN_ESPERA) return false;
        Medico disponible = medico == null ? buscarMedico() : medico;
        return disponible != null && disponible.darConsulta(this);
    }
    @Override
    public String toString() {
        return nombre + " | " + (sistema == null ? "Especialidad " + getEspecialidad() : sistema.nombreEspecialidad(getEspecialidad()))
                + " | " + estado;
    }
}
