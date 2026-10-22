package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import hospital.pacientes.*;
import hospital.personal.*;
import hospital.sistema.Sistema;

/** Menú para registrar, atender y supervisar un hospital. */
public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final List<Sistema> hospitales = new ArrayList<>();
    private static Sistema hospital;
    private static Paciente paciente;

    /** Cancela una captura sin guardar datos incompletos. */
    private static class Cancelar extends RuntimeException {}

    /**
     * Ejecuta el menú hasta salir o cerrar la consola.
     * @param args argumentos de consola, no utilizados
     */
    public static void main(String[] args) {
        try {
            while (true) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Hospital activo: " + (hospital == null ? "Sin seleccionar" : hospital.getNombreHospital()));
                System.out.println("Paciente activo: " + (paciente == null ? "Sin seleccionar" : paciente.getNombre()));
                System.out.println("---------\nH. Cambiar hospital activo\nP. Cambiar paciente activo\n---------\nD. Precargar datos demo");
                System.out.println("---------\n1. Crear hospital\n2. Crear especialidad\n3. Registrar médico\n4. Registrar enfermero\n5. Registrar paciente");
                System.out.println("---------\n6. Solicitar consulta\n7. Dar tratamiento\n8. Solicitar paciente para un médico");
                System.out.println("---------\n9. Ver hospitales\n10. Ver especialidades\n11. Ver médicos y pacientes\n12. Ver enfermeros y pacientes\n13. Ver pacientes\n14. Ver paciente activo y tratamiento");
                System.out.println("---------\n15. Ver equipo y turnos de un jefe\n16. Cambiar turno de un enfermero\n17. Cambiar enfermero del paciente activo\n18. Autorizar medicamento controlado");
                System.out.println("---------\n19. Cambiar nombre del hospital\n20. Cambiar nombre del paciente\n---------\n0. Salir");
                System.out.print("Opción: ");
                String opcion = entrada.nextLine().trim().toUpperCase();
                if (opcion.equals("0")) return;
                try {
                    if (hospital == null && !List.of("1", "H", "D", "9").contains(opcion))
                        System.out.println("Primero crea o selecciona un hospital.");
                    else switch (opcion) {
                        case "H": hospital = elegir(hospitales, "Hospital"); paciente = null; break;
                        case "P": paciente = elegir(hospital.getPacientes(), "Paciente"); break;
                        case "D": demo(); break;
                        case "1": crearHospital(texto("Nombre del hospital")); break;
                        case "2": informar(hospital.registroEspecialidad(numero("ID de especialidad (2 a 99)", 2, 99), texto("Nombre de la especialidad"))); break;
                        case "3": registrarMedico(); break;
                        case "4": registrarEnfermero(); break;
                        case "5": registrarPaciente(); break;
                        case "6": informar(activo().solicitarConsulta()); break;
                        case "7": tratar(); break;
                        case "8": informar(elegir(hospital.getMedicos(), "Médico").solicitarPacienteDisponible()); break;
                        case "9": for (Sistema actual : hospitales) System.out.println(descripcion(actual)); break;
                        case "10": verEspecialidades(); break;
                        case "11":
                            for (Medico medico : hospital.getMedicos()) { System.out.println(descripcion(medico)); medico.verListaPacientes(); }
                            break;
                        case "12":
                            for (Enfermero enfermero : hospital.getEnfermeros()) { System.out.println(descripcion(enfermero)); enfermero.verListaPacientes(); }
                            break;
                        case "13": listar(hospital.getPacientes()); break;
                        case "14": verPaciente(activo()); break;
                        case "15": jefe().supervisarEquipo(); break;
                        case "16":
                            JefeEnfermeria supervisor = jefe();
                            Enfermero enfermero = elegir(supervisor.verEquipo(), "Enfermero");
                            informar(supervisor.cambiarTurno(enfermero, numero("1. Matutino\n2. Vespertino\nTurno", 1, 2) == 2));
                            break;
                        case "17":
                            Paciente actual = activo();
                            JefeEnfermeria responsable = jefe();
                            informar(responsable.cambiarEnfermero(actual.getEnfermero(), elegir(responsable.verEquipo(), "Nuevo enfermero"), actual));
                            break;
                        case "18": informar(jefe().autorizarMedicamentos(activo())); break;
                        case "19": hospital.setNombreHospital(texto("Nuevo nombre del hospital")); break;
                        case "20": activo().setNombre(texto("Apellidos y nombres")); break;
                        default: System.out.println("Opción no válida.");
                    }
                } catch (Cancelar cancelado) {
                    // Regresa sin guardar una captura incompleta.
                } catch (IllegalArgumentException error) {
                    System.out.println(error.getMessage());
                }
                entrada.nextLine();
            }
        } catch (NoSuchElementException fin) {
            // La consola también puede cerrarse con Ctrl+D.
        }
    }

    /** Lee texto; cero cancela la operación completa. */
    private static String texto(String campo) {
        while (true) {
            System.out.print(campo + " (0 cancelar): ");
            String valor = entrada.nextLine().trim();
            if (valor.equals("0")) throw new Cancelar();
            if (!valor.isEmpty()) return valor;
            System.out.println("Escribe un valor.");
        }
    }

    /** Pide un número válido y permite cancelar con cero. */
    private static int numero(String campo, int minimo, int maximo) {
        while (true) {
            try {
                int valor = Integer.parseInt(texto(campo));
                if (valor >= minimo && valor <= maximo) return valor;
            } catch (NumberFormatException error) {
                // Vuelve a pedir la entrada.
            }
            System.out.println("Ingresa un número entre " + minimo + " y " + maximo + ".");
        }
    }

    /** Selecciona una persona u hospital de una lista numerada. */
    private static <T> T elegir(List<T> lista, String campo) {
        if (lista.isEmpty()) throw new IllegalArgumentException("No hay registros disponibles.");
        for (int i = 0; i < lista.size(); i++) System.out.println((i + 1) + ". " + descripcion(lista.get(i)));
        return lista.get(numero(campo, 1, lista.size()) - 1);
    }

    /** Muestra el catálogo con sus identificadores reales. */
    private static void verEspecialidades() {
        for (Map.Entry<Integer, String> entrada : hospital.getEspecialidades().entrySet())
            System.out.println(entrada.getKey() + ". " + entrada.getValue());
    }

    /** Elige un ID existente, aunque los números no sean consecutivos. */
    private static int especialidad() {
        verEspecialidades();
        while (true) {
            int id = numero("ID de especialidad", 1, 99);
            if (hospital.getEspecialidades().containsKey(id)) return id;
            System.out.println("Ese ID no está registrado.");
        }
    }

    private static int cedula() {
        while (true) {
            int valor = numero("Cédula (mínimo seis dígitos)", 100000, Integer.MAX_VALUE);
            if (hospital.cedulaDisponible(valor)) return valor;
            System.out.println("La cédula ya está registrada.");
        }
    }

    private static void crearHospital(String nombre) {
        hospital = new Sistema(nombre);
        hospitales.add(hospital);
        paciente = null;
    }

    private static void registrarMedico() {
        String nombre = texto("Nombre completo del médico");
        int cedula = cedula(), id = especialidad();
        Medico medico = id == 1 ? new MedicoGeneral(nombre, cedula) : new MedicoEspecialista(nombre, cedula, id);
        informar(medico.registroEnSistema(hospital));
    }

    private static void registrarEnfermero() {
        int tipo = numero("1. Enfermero especialista\n2. Jefe de enfermería\nTipo", 1, 2);
        String nombre = texto("Nombre completo del enfermero");
        int cedula = cedula(), id = especialidad();
        boolean turno = numero("1. Matutino\n2. Vespertino\nTurno", 1, 2) == 2;
        Enfermero enfermero = tipo == 1 ? new EnfermeroEspecialista(nombre, cedula, id) : new JefeEnfermeria(nombre, cedula, id);
        enfermero.setTurno(turno);
        if (!enfermero.registroEnSistema(hospital)) System.out.println("No se pudo registrar: máximo dos jefes por especialidad.");
        else informar(true);
    }

    private static void registrarPaciente() {
        String nombre = texto("Apellidos y nombres del paciente");
        int id = especialidad();
        Paciente nuevo = id == 1 ? new PacienteGeneral(nombre, texto("Motivo de consulta")) : new PacienteEspecialidad(nombre, id);
        if (nuevo.registroEnSistema(hospital)) { paciente = nuevo; verPaciente(nuevo); }
    }

    private static Paciente activo() {
        if (paciente == null) throw new IllegalArgumentException("Selecciona un paciente con P o regístralo con 5.");
        return paciente;
    }

    private static JefeEnfermeria jefe() {
        List<JefeEnfermeria> jefes = new ArrayList<>();
        for (Enfermero enfermero : hospital.getEnfermeros()) if (enfermero instanceof JefeEnfermeria) jefes.add((JefeEnfermeria) enfermero);
        return elegir(jefes, "Jefe de enfermería");
    }

    private static void tratar() {
        Paciente actual = activo();
        if (actual.getEstado() != Paciente.Estado.EN_CONSULTA) throw new IllegalArgumentException("El paciente debe estar en consulta.");
        String indicacion = texto("Tratamiento indicado");
        hospital.interfaces.AtencionMedica atencion = actual.getMedico();
        if (atencion.darTratamiento(actual)) { actual.setTratamiento(indicacion); informar(true); verPaciente(actual); }
        else System.out.println("Sin enfermero compatible con cupo o falta un supervisor para autorizar el medicamento. El paciente sigue en consulta.");
    }

    private static void informar(boolean correcto) {
        System.out.println(correcto ? "Operación realizada." : "No se pudo realizar: revisa especialidad, estado, cupo y registros.");
    }

    private static String descripcion(Object objeto) {
        if (objeto instanceof Sistema) {
            Sistema sistema = (Sistema) objeto;
            return sistema.getNombreHospital() + " | Médicos: " + sistema.getNoMedicos() + " | Enfermeros: " + sistema.getNoEnfermeros() + " | Pacientes: " + sistema.getNoPacientes();
        }
        if (objeto instanceof Medico) {
            Medico medico = (Medico) objeto;
            return medico.getNombre() + " | Cédula: " + medico.getCedula() + " | " + hospital.nombreEspecialidad(medico.getEspecialidad())
                    + " | Pacientes: " + medico.getNoPacientes() + "/10 | En consulta: " + (medico.getEnConsulta() == null ? "Nadie" : medico.getEnConsulta().getNombre());
        }
        if (objeto instanceof Enfermero) {
            Enfermero enfermero = (Enfermero) objeto;
            return enfermero.getNombre() + " | Cédula: " + enfermero.getCedula() + " | " + hospital.nombreEspecialidad(enfermero.getEspecialidad())
                    + " | " + enfermero.nombreTurno() + (enfermero instanceof JefeEnfermeria ? " | Supervisor" : " | Pacientes: " + enfermero.getNoPacientes() + "/3");
        }
        return objeto.toString();
    }

    private static void verPaciente(Paciente actual) {
        System.out.println(actual + " | Médico: " + (actual.getMedico() == null ? "Sin asignar" : actual.getMedico().getNombre())
                + " | Enfermero: " + (actual.getEnfermero() == null ? "Sin asignar" : actual.getEnfermero().getNombre()));
        if (actual instanceof PacienteGeneral) System.out.println("Motivo: " + ((PacienteGeneral) actual).getMotivoConsulta());
        System.out.println("Medicamento controlado: " + (actual.getNecesitaMedicamento() ? "Necesario" : "No necesario")
                + " | Autorizado: " + (actual.getMedicamentoAutorizado() ? "Sí" : "No"));
        actual.verTratamiento();
    }

    private static void listar(List<Paciente> pacientes) {
        List<Paciente> lista = new ArrayList<>(pacientes);
        lista.sort((a, b) -> b.getNombre().compareToIgnoreCase(a.getNombre()));
        for (Paciente actual : lista) verPaciente(actual);
        if (lista.isEmpty()) System.out.println("Sin pacientes.");
    }

    /** Crea datos independientes para demostrar atención y supervisión. */
    private static void demo() {
        crearHospital("Hospital Demo " + (hospitales.size() + 1));
        hospital.registroEspecialidad(2, "Cardiología");
        hospital.registroEspecialidad(7, "Neurología");
        new MedicoGeneral("Ana Torres", 100001).registroEnSistema(hospital);
        new MedicoEspecialista("Luis Herrera", 100002, 2).registroEnSistema(hospital);
        new MedicoEspecialista("Julia Mora", 100003, 7).registroEnSistema(hospital);
        int cedula = 200001;
        for (int id : hospital.getEspecialidades().keySet()) {
            new EnfermeroEspecialista("Enfermero matutino " + id, cedula++, id).registroEnSistema(hospital);
            Enfermero tarde = new EnfermeroEspecialista("Enfermero vespertino " + id, cedula++, id);
            tarde.setTurno(true); tarde.registroEnSistema(hospital);
            new JefeEnfermeria("Supervisor del área " + id, cedula++, id).registroEnSistema(hospital);
        }
        Paciente[] datos = { new PacienteGeneral("López García, María"), new PacienteGeneral("Zamora Ruiz, Carlos"),
            new PacienteEspecialidad("Aguilar Pérez, Elena", 2), new PacienteEspecialidad("Torres Díaz, Pedro", 2),
            new PacienteEspecialidad("Méndez Soto, Julia", 7), new PacienteEspecialidad("Ruiz Cano, Diego", 7) };
        for (Paciente actual : datos) actual.registroEnSistema(hospital);
        paciente = datos[0];
        System.out.println("Demo lista: 3 áreas, 3 médicos, 6 enfermeros, 3 supervisores y 6 pacientes en espera.");
    }
}
