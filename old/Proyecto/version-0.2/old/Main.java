import java.util.Scanner;

public class Main {
    private static void separador() {
        System.out.println("=============================================");
    }

    private static void mostrarMenu() {
        separador();
        System.out.println("\t\tSISTEMA HOSPITALARIO");
        separador();
        System.out.println("1.  Crear hospital");
        System.out.println("2.  Agregar especialidad");
        System.out.println("3.  Mostrar especialidades");
        System.out.println("4.  Eliminar especialidad");
        System.out.println("5.  Crear médico");
        System.out.println("6.  Crear enfermero");
        System.out.println("7.  Crear paciente");
        System.out.println("8.  Asignar paciente a médico");
        System.out.println("9.  Asignar paciente a enfermero");
        System.out.println("10. Ver lista de pacientes de un médico");
        System.out.println("11. Ver lista de pacientes de un enfermero");
        System.out.println("12. Ver tratamiento de un paciente");
        System.out.println("13. Salir");
        separador();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = null;

        mostrarMenu();

        do {
            System.out.print("\nIngresa una opción (1-13, 0 para ver menú): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor ingresa un número válido.");
                scanner.nextLine();
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 0) {
                mostrarMenu();
                continue;
            }

            if (opcion == 13) {
                System.out.println("Saliendo del sistema...");
                return;
            }

            if (opcion > 1 && opcion < 13 && sistema == null) {
                System.out.println("[error] Primero debes crear el hospital (Opción 1).");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre del hospital: ");
                    String nombreHospital = scanner.nextLine().trim();
                    sistema = new Sistema(nombreHospital);
                    break;

                case 2:
                    System.out.print("Ingresa el nombre de la especialidad: ");
                    String nombreEspecialidad = scanner.nextLine().trim();
                    sistema.agregarEspecialidad(nombreEspecialidad);
                    System.out.println("[ok] Especialidad registrada.");
                    break;

                case 3:
                    sistema.mostrarEspecialidades();
                    break;

                case 4:
                    sistema.mostrarEspecialidades();
                    System.out.print("Ingresa el ID de la especialidad a eliminar: ");
                    int idEspecialidad = scanner.nextInt();
                    scanner.nextLine();
                    sistema.eliminarEspecialidad(idEspecialidad);
                    break;

                case 5:
                    System.out.print("Ingresa el nombre del médico (ej. Dr. Silva Roberto): ");
                    String nombreMedico = scanner.nextLine().trim();
                    System.out.print("Ingresa la cédula del médico: ");
                    int cedulaMedico = scanner.nextInt();
                    scanner.nextLine();
                    sistema.mostrarEspecialidades();
                    System.out.print("Ingresa el ID de la especialidad del médico: ");
                    int especialidadMedico = scanner.nextInt();
                    scanner.nextLine();

                    Medico medico = new Medico(nombreMedico, cedulaMedico, especialidadMedico);
                    medico.registroEnSistema(sistema);
                    System.out.println("[ok] Médico registrado con éxito en el sistema.");
                    break;

                case 6:
                    System.out.print("Ingresa el nombre del enfermero (ej. Enf. Rivera Carlos): ");
                    String nombreEnfermero = scanner.nextLine().trim();
                    System.out.print("Ingresa la cédula del enfermero: ");
                    int cedulaEnfermero = scanner.nextInt();
                    scanner.nextLine();
                    sistema.mostrarEspecialidades();
                    System.out.print("Ingresa el ID de la especialidad del enfermero: ");
                    int especialidadEnfermero = scanner.nextInt();
                    scanner.nextLine();

                    Enfermero enfermero = new Enfermero(nombreEnfermero, cedulaEnfermero, especialidadEnfermero);
                    enfermero.registroEnSistema(sistema);
                    System.out.println("[ok] Enfermero registrado con éxito en el sistema.");
                    break;

                case 7:
                    System.out.print("Ingresa el nombre del paciente (ej. Zapata Laura): ");
                    String nombrePaciente = scanner.nextLine().trim();
                    sistema.mostrarEspecialidades();
                    System.out.print("Ingresa el ID de la especialidad de atención: ");
                    int especialidadPaciente = scanner.nextInt();
                    scanner.nextLine();

                    Paciente paciente = new Paciente(nombrePaciente, especialidadPaciente);
                    paciente.registroEnSistema(sistema);
                    System.out.println("[ok] Paciente registrado con éxito en el sistema.");
                    break;

                case 8:
                    if (sistema.p.isEmpty() || sistema.m.isEmpty()) {
                        System.out.println("[error] Se requiere al menos un paciente y un médico registrados.");
                        break;
                    }

                    System.out.println("\n--- Selecciona el Paciente ---");
                    for (int i = 0; i < sistema.p.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.p.get(i).nombre + " | Especialidad: " + sistema.p.get(i).especialidadAtencion);
                    }
                    System.out.print("Número de paciente: ");
                    int idxPacMed = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.println("\n--- Selecciona el Médico ---");
                    for (int i = 0; i < sistema.m.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.m.get(i).nombre + " | Especialidad: " + sistema.m.get(i).especialidad);
                    }
                    System.out.print("Número de médico: ");
                    int idxMed = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (idxPacMed >= 0 && idxPacMed < sistema.p.size() && idxMed >= 0 && idxMed < sistema.m.size()) {
                        Paciente pac = sistema.p.get(idxPacMed);
                        Medico med = sistema.m.get(idxMed);
                        boolean asignado = sistema.asignarPaciente(pac, med);
                        if (asignado) {
                            pac.medicoAsignado = med;
                            System.out.println("[éxito] Paciente " + pac.nombre + " asignado correctamente al Dr. " + med.nombre);
                        } else {
                            System.out.println("[rechazo] No se pudo asignar: cupo lleno (máximo 10) o no coinciden las especialidades.");
                        }
                    } else {
                        System.out.println("[error] Selección fuera de rango.");
                    }
                    break;

                case 9:
                    if (sistema.p.isEmpty() || sistema.e.isEmpty()) {
                        System.out.println("[error] Se requiere al menos un paciente y un enfermero registrados.");
                        break;
                    }

                    System.out.println("\n--- Selecciona el Paciente ---");
                    for (int i = 0; i < sistema.p.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.p.get(i).nombre + " | Tratamiento actual: " + sistema.p.get(i).tratamiento);
                    }
                    System.out.print("Número de paciente: ");
                    int idxPacEnf = scanner.nextInt() - 1;
                    scanner.nextLine();

                    System.out.println("\n--- Selecciona el Enfermero ---");
                    for (int i = 0; i < sistema.e.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.e.get(i).nombre + " | Especialidad: " + sistema.e.get(i).especialidad);
                    }
                    System.out.print("Número de enfermero: ");
                    int idxEnf = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (idxPacEnf >= 0 && idxPacEnf < sistema.p.size() && idxEnf >= 0 && idxEnf < sistema.e.size()) {
                        Paciente pac = sistema.p.get(idxPacEnf);
                        Enfermero enf = sistema.e.get(idxEnf);

                        System.out.print("Ingresa el tratamiento o cuidado para este paciente: ");
                        String descTratamiento = scanner.nextLine().trim();
                        pac.tratamiento = descTratamiento;

                        boolean asignado = sistema.asignarPaciente(pac, enf);
                        if (asignado) {
                            pac.enfermeroAsignado = enf;
                            pac.estado = Paciente.statuses.EN_TRATAMIENTO;
                            System.out.println("[éxito] Paciente " + pac.nombre + " puesto en tratamiento con " + enf.nombre);
                        } else {
                            System.out.println("[rechazo] No se pudo asignar: cupo lleno (máximo 3) o no coinciden las especialidades.");
                        }
                    } else {
                        System.out.println("[error] Selección fuera de rango.");
                    }
                    break;

                case 10:
                    if (sistema.m.isEmpty()) {
                        System.out.println("[aviso] No hay médicos registrados en el sistema.");
                        break;
                    }
                    System.out.println("\n--- Médicos Disponibles ---");
                    for (int i = 0; i < sistema.m.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.m.get(i).nombre);
                    }
                    System.out.print("Selecciona el médico para ver su lista: ");
                    int selMed = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (selMed >= 0 && selMed < sistema.m.size()) {
                        separador();
                        sistema.m.get(selMed).verListaPacientes();
                        separador();
                    } else {
                        System.out.println("[error] Índice de médico no válido.");
                    }
                    break;

                case 11:
                    if (sistema.e.isEmpty()) {
                        System.out.println("[aviso] No hay enfermeros registrados en el sistema.");
                        break;
                    }
                    System.out.println("\n--- Enfermeros Disponibles ---");
                    for (int i = 0; i < sistema.e.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.e.get(i).nombre);
                    }
                    System.out.print("Selecciona el enfermero para ver su lista: ");
                    int selEnf = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (selEnf >= 0 && selEnf < sistema.e.size()) {
                        separador();
                        sistema.e.get(selEnf).verListaPacientes();
                        separador();
                    } else {
                        System.out.println("[error] Índice de enfermero no válido.");
                    }
                    break;

                case 12:
                    if (sistema.p.isEmpty()) {
                        System.out.println("[aviso] No hay pacientes registrados en el sistema.");
                        break;
                    }
                    System.out.println("\n--- Pacientes Registrados ---");
                    for (int i = 0; i < sistema.p.size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.p.get(i).nombre);
                    }
                    System.out.print("Selecciona el paciente para ver su tratamiento: ");
                    int selPac = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (selPac >= 0 && selPac < sistema.p.size()) {
                        separador();
                        sistema.p.get(selPac).verTratamiento();
                        separador();
                    } else {
                        System.out.println("[error] Índice de paciente no válido.");
                    }
                    break;

                default:
                    System.out.println("Opción inválida. Ingresa 0 para consultar el menú de nuevo.");
            }
        } while (true);
    }
}
