package hospital.sistema;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import hospital.pacientes.*;
import hospital.personal.*;
/** Registra personas y asigna atención dentro de un hospital. */
public class Sistema {
    private String nombreHospital;
    private int noMedicos;
    private int noEnfermeros;
    private int noPacientes;
    private final Map<Integer, String> especialidades = new LinkedHashMap<>();
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Enfermero> enfermeros = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();
    /** Crea un hospital con sus datos iniciales. */
    public Sistema(String nombre) {
        this.nombreHospital = (nombre != null && !nombre.isBlank()) ? nombre.trim() : "Hospital";
        especialidades.put(1, "Medicina general");
    }
    /** Obtiene el nombre del hospital. */
    public String getNombreHospital() { return nombreHospital; }
    /** Actualiza nombre hospital. */
    public void setNombreHospital(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            nombreHospital = nombre.trim();
        }
    }
    /** Obtiene el número de médicos. */
    public int getNoMedicos() { return noMedicos; }
    /** Obtiene el número de enfermeros. */
    public int getNoEnfermeros() { return noEnfermeros; }
    /** Obtiene el número de pacientes. */
    public int getNoPacientes() { return noPacientes; }
    /** Obtiene una copia no modificable de los médicos. */
    public List<Medico> getMedicos() { return List.copyOf(medicos); }
    /** Obtiene una copia no modificable de los enfermeros. */
    public List<Enfermero> getEnfermeros() { return List.copyOf(enfermeros); }
    /** Obtiene una copia no modificable de los pacientes. */
    public List<Paciente> getPacientes() { return List.copyOf(pacientes); }
    /** Obtiene el catálogo sin permitir cambios externos. */
    public Map<Integer, String> getEspecialidades() { return java.util.Collections.unmodifiableMap(especialidades); }
    /** Obtiene el nombre asociado al ID. */
    public String nombreEspecialidad(int id) { return especialidades.getOrDefault(id, "Sin especialidad"); }
    /** Agrega un ID y nombre al catálogo. */
    public boolean registroEspecialidad(int id, String nombre) {
        if (id < 2 || id > 99 || nombre == null || nombre.isBlank() || especialidades.containsKey(id)) return false;
        for (String existente : especialidades.values()) if (existente.equalsIgnoreCase(nombre.trim())) return false;
        especialidades.put(id, nombre.trim());
        return true;
    }
    /** Comprueba que la cédula sea válida y no esté registrada. */
    public boolean cedulaDisponible(int cedula) {
        if (cedula < 100000) return false;
        for (Medico medico : medicos) if (medico.getCedula() == cedula) return false;
        for (Enfermero enfermero : enfermeros) if (enfermero.getCedula() == cedula) return false;
        return true;
    }
    /** Registra un médico compatible con el catálogo. */
    public boolean registroMedico(Medico medico) {
        if (medico == null || !cedulaDisponible(medico.getCedula()) || !especialidades.containsKey(medico.getEspecialidad())
                || medico.getSistema() != null && medico.getSistema() != this) return false;
        medicos.add(medico); medico.setSistema(this); noMedicos++;
        return true;
    }
    /** Registra un enfermero y limita los jefes a dos por área. */
    public boolean registroEnfermero(Enfermero enfermero) {
        if (enfermero == null || !cedulaDisponible(enfermero.getCedula()) || !especialidades.containsKey(enfermero.getEspecialidad())
                || enfermero.getSistema() != null && enfermero.getSistema() != this) return false;
        if (enfermero instanceof JefeEnfermeria) {
            int jefes = 0;
            for (Enfermero actual : enfermeros) if (actual instanceof JefeEnfermeria
                    && actual.getEspecialidad() == enfermero.getEspecialidad()) jefes++;
            if (jefes >= 2) return false;
        }
        enfermeros.add(enfermero); enfermero.setSistema(this); noEnfermeros++;
        return true;
    }
    /** Registra un paciente sin duplicar su entrada. */
    public boolean registroPaciente(Paciente paciente) {
        if (paciente == null || pacientes.contains(paciente) || !especialidades.containsKey(paciente.getEspecialidad())
                || paciente.getSistema() != null && paciente.getSistema() != this) return false;
        pacientes.add(paciente); paciente.setSistema(this); noPacientes++;
        return true;
    }
    private Medico buscarMedico(Paciente paciente) {
        if (paciente == null || paciente.getSistema() != this || paciente.getEstado() != Paciente.Estado.EN_ESPERA) return null;
        if (paciente.getMedico() != null) return paciente.getMedico();
        for (Medico medico : medicos) if (medico.getEspecialidad() == paciente.getEspecialidad()
                && medico.getEnConsulta() == null && medico.addPaciente(paciente)) return medico;
        return null;
    }
    /** Asigna atención según el tipo de argumento. */
    public Medico asignarPaciente(PacienteGeneral paciente) { return buscarMedico(paciente); }
    /** Asigna atención según el tipo de argumento. */
    public Medico asignarPaciente(PacienteEspecialidad paciente) { return buscarMedico(paciente); }
    private Paciente buscarPaciente(Medico medico) {
        if (medico == null || !medicos.contains(medico)) return null;
        for (Paciente paciente : pacientes) if (paciente.getEstado() == Paciente.Estado.EN_ESPERA && medico.addPaciente(paciente)) return paciente;
        return null;
    }
    /** Asigna atención según el tipo de argumento. */
    public Paciente asignarPaciente(MedicoGeneral medico) { return buscarPaciente(medico); }
    /** Asigna atención según el tipo de argumento. */
    public Paciente asignarPaciente(MedicoEspecialista medico) { return buscarPaciente(medico); }
    /** La referencia base selecciona la sobrecarga de tratamiento. */
    public boolean asignarPaciente(Paciente paciente) {
        if (paciente == null || !pacientes.contains(paciente)) return false;
        for (Enfermero enfermero : enfermeros) if (enfermero.darTratamiento(paciente)) return true;
        return false;
    }
    /** Busca un supervisor registrado de la especialidad. */
    public JefeEnfermeria buscarJefe(int especialidad) {
        for (Enfermero enfermero : enfermeros) if (enfermero instanceof JefeEnfermeria
                && enfermero.getEspecialidad() == especialidad) return (JefeEnfermeria) enfermero;
        return null;
    }
}
