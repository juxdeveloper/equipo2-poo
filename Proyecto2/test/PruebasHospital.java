import hospital.sistema.Sistema;
import hospital.pacientes.*;
import hospital.personal.*;

/** Comprueba las reglas del hospital sin depender del azar. */
public class PruebasHospital {
    private static int casos;
    private static void comprobar(boolean resultado) {
        casos++;
        if (!resultado) throw new AssertionError("Falló el caso " + casos);
    }
    public static void main(String[] args) {
        Sistema s = new Sistema("Prueba"), otro = new Sistema("Otro");
        comprobar(s.registroEspecialidad(7, "Cardiología"));
        comprobar(!s.registroEspecialidad(7, "Neurología"));
        comprobar(!s.registroEspecialidad(8, "cardiología"));
        comprobar(!s.registroEspecialidad(0, "Inválida"));
        MedicoGeneral general = new MedicoGeneral("Médico general", 100001);
        MedicoEspecialista medico = new MedicoEspecialista("Especialista", 100002, 7);
        comprobar(general.registroEnSistema(s)); comprobar(medico.registroEnSistema(s));
        comprobar(!medico.registroEnSistema(s)); comprobar(!medico.registroEnSistema(otro));
        comprobar(!new MedicoEspecialista("Sin catálogo", 100003, 8).registroEnSistema(s));
        EnfermeroEspecialista e1 = new EnfermeroEspecialista("Enfermero 1", 200001, 7);
        EnfermeroEspecialista e2 = new EnfermeroEspecialista("Enfermero 2", 200002, 7);
        EnfermeroEspecialista eg = new EnfermeroEspecialista("General", 200003, 1);
        comprobar(e1.registroEnSistema(s)); comprobar(e2.registroEnSistema(s)); comprobar(eg.registroEnSistema(s));
        PacienteEspecialidad p = new PacienteEspecialidad("Zamora, Ana", 7);
        p.setNecesitaMedicamento(true); comprobar(p.registroEnSistema(s)); comprobar(p.solicitarConsulta());
        comprobar(!general.darConsulta(p)); comprobar(!medico.darTratamiento(p));
        comprobar(p.getEstado() == Paciente.Estado.EN_CONSULTA && p.getEnfermero() == null && medico.getEnConsulta() == p);
        JefeEnfermeria j1 = new JefeEnfermeria("Jefe 1", 300001, 7);
        JefeEnfermeria j2 = new JefeEnfermeria("Jefe 2", 300002, 7);
        comprobar(j1.registroEnSistema(s)); comprobar(j2.registroEnSistema(s));
        comprobar(!new JefeEnfermeria("Jefe 3", 300003, 7).registroEnSistema(s));
        comprobar(j1.verEquipo().size() == 2 && !j1.verEquipo().contains(j1) && !j1.verEquipo().contains(j2));
        comprobar(j1.cambiarTurno(e1, true) && e1.getTurno()); comprobar(!j1.cambiarTurno(eg, true) && !eg.getTurno());
        comprobar(medico.darTratamiento(p));
        comprobar(p.getMedicamentoAutorizado() && p.getEnfermero() == e1 && medico.getEnConsulta() == null);
        comprobar(j1.cambiarEnfermero(e1, e2, p));
        comprobar(e1.getNoPacientes() == 0 && e2.getNoPacientes() == 1 && p.getEnfermero() == e2);
        comprobar(!j1.cambiarEnfermero(e2, eg, p)); comprobar(!j1.darTratamiento(p));
        PacienteGeneral pg = new PacienteGeneral("General, Paciente");
        pg.setNecesitaMedicamento(false); comprobar(pg.registroEnSistema(s));
        comprobar(!j1.autorizarMedicamentos(pg)); comprobar(pg.solicitarConsulta()); comprobar(general.darTratamiento(pg));
        comprobar(pg.getEnfermero() == eg && !pg.getMedicamentoAutorizado());
        PacienteEspecialidad[] pacientes = new PacienteEspecialidad[10];
        for (int i = 0; i < pacientes.length; i++) {
            pacientes[i] = new PacienteEspecialidad("Paciente " + i, 7);
            pacientes[i].setNecesitaMedicamento(false); comprobar(pacientes[i].registroEnSistema(s));
        }
        for (int i = 0; i < 9; i++) comprobar(medico.solicitarPacienteDisponible());
        comprobar(!medico.solicitarPacienteDisponible() && medico.getNoPacientes() == 10);
        comprobar(pacientes[0].solicitarConsulta());
        comprobar(!pacientes[1].solicitarConsulta() && pacientes[1].getEstado() == Paciente.Estado.EN_ESPERA);
        comprobar(medico.darTratamiento(pacientes[0]));
        for (int i = 1; i < 5; i++) { comprobar(pacientes[i].solicitarConsulta()); comprobar(medico.darTratamiento(pacientes[i])); }
        comprobar(e1.getNoPacientes() == 3 && e2.getNoPacientes() == 3);
        comprobar(pacientes[5].solicitarConsulta()); comprobar(!medico.darTratamiento(pacientes[5]));
        comprobar(pacientes[5].getEstado() == Paciente.Estado.EN_CONSULTA && pacientes[5].getEnfermero() == null);
        comprobar(!j1.cambiarEnfermero(e2, e1, p) && p.getEnfermero() == e2 && e2.getNoPacientes() == 3);
        try { s.getEspecialidades().put(50, "Cambio externo"); throw new AssertionError(); } catch (UnsupportedOperationException esperado) { casos++; }
        try { e1.getPacientes().clear(); throw new AssertionError(); } catch (UnsupportedOperationException esperado) { casos++; }
        try { new MedicoGeneral("", 12); throw new AssertionError(); } catch (IllegalArgumentException esperado) { casos++; }
        comprobar(s.getNoMedicos() == 2 && s.getNoEnfermeros() == 5 && s.getNoPacientes() == 12);
        System.out.println(casos + " comprobaciones correctas.");
    }
}
