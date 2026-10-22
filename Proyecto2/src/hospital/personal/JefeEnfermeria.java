package hospital.personal;
import java.util.ArrayList;
import java.util.List;
import hospital.pacientes.Paciente;
/** Supervisor de turnos, asignaciones y medicamentos de una especialidad. */
public class JefeEnfermeria extends Enfermero {
    private final int areaEspecialidad;
    /** Crea un supervisor con sus datos iniciales. */
    public JefeEnfermeria(String nombre, int cedula, int areaEspecialidad) {
        super(nombre, cedula);
        if (areaEspecialidad < 1 || areaEspecialidad > 99) throw new IllegalArgumentException("Área inválida.");
        this.areaEspecialidad = areaEspecialidad;
    }
    /** Obtiene el ID del área supervisada. */
    public int getAreaEspecialidad() { return areaEspecialidad; }
    @Override public int getEspecialidad() { return areaEspecialidad; }
    @Override protected boolean puedeAtender(Paciente paciente) { return false; }
    /** El jefe coordina; el tratamiento corresponde al enfermero asignado. */
    @Override public boolean darTratamiento(Paciente paciente) { return false; }
    /** Comprueba si el enfermero pertenece al área del jefe. */
    public boolean supervisa(Enfermero enfermero) {
        return getSistema() != null && getSistema().getEnfermeros().contains(this)
                && enfermero instanceof EnfermeroEspecialista && getSistema().getEnfermeros().contains(enfermero)
                && enfermero.getSistema() == getSistema()
                && enfermero.getEspecialidad() == areaEspecialidad;
    }
    /** Obtiene los enfermeros del área, sin incluir supervisores. */
    public List<Enfermero> verEquipo() {
        List<Enfermero> equipo = new ArrayList<>();
        if (getSistema() != null) for (Enfermero enfermero : getSistema().getEnfermeros())
            if (supervisa(enfermero)) equipo.add(enfermero);
        return List.copyOf(equipo);
    }
    /** Muestra turnos y cupos del equipo del área. */
    public void supervisarEquipo() {
        if (verEquipo().isEmpty()) System.out.println("Sin enfermeros en esta área.");
        for (Enfermero enfermero : verEquipo()) System.out.println(enfermero.getNombre() + " | " + enfermero.nombreTurno()
                + " | Pacientes: " + enfermero.getNoPacientes() + "/3");
    }
    /** Cambia el turno de un enfermero del área. */
    public boolean cambiarTurno(Enfermero enfermero, boolean turno) {
        if (!supervisa(enfermero)) return false;
        enfermero.setTurno(turno);
        return true;
    }
    /** Solicita el traslado de un paciente entre enfermeros compatibles. */
    public boolean cambiarEnfermero(Enfermero actual, Enfermero nuevo, Paciente paciente) {
        return actual != null && actual.trasladarPaciente(paciente, nuevo, this);
    }
    /** En esta simulación se aprueba toda solicitud válida del área. */
    public boolean autorizarMedicamentos(Paciente paciente) {
        return paciente != null && paciente.autorizarMedicamento(this);
    }
}
