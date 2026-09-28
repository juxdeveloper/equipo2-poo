import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// Scanner para leer del teclado
		Scanner sc = new Scanner(System.in);
		
		// El hospital inicia vacio hasta que el usuario lo cree o cargue la demo
		Sistema hospital = null;
		int opcion = -1;

		// Ciclo para mantener abierto el menu hasta que el usuario elija 0
		while (opcion != 0) {
			System.out.println("\n==========================================");
			System.out.println("           SISTEMA HOSPITALARIO           ");
			System.out.println("==========================================");
			System.out.println("  [1]  Cargar datos demo (3 de cada tipo)");
			System.out.println("------------------------------------------");
			System.out.println("  [2]  Agregar hospital");
			System.out.println("  [3]  Agregar especialidad");
			System.out.println("  [4]  Agregar medico");
			System.out.println("  [5]  Agregar enfermero");
			System.out.println("  [6]  Agregar paciente");
			System.out.println("------------------------------------------");
			System.out.println("  [7]  Medico solicita paciente");
			System.out.println("  [8]  Paciente solicita consulta");
			System.out.println("  [9]  Medico da tratamiento");
			System.out.println("------------------------------------------");
			System.out.println("  [10] Ver especialidades");
			System.out.println("  [11] Ver medicos");
			System.out.println("  [12] Ver enfermeros");
			System.out.println("  [13] Ver pacientes");
			System.out.println("------------------------------------------");
			System.out.println("  [0]  Salir");
			System.out.println("==========================================");
			System.out.print("Selecciona una opcion: ");

			// Lectura normal de entero y limpieza de salto de linea
			opcion = sc.nextInt();
			sc.nextLine();

			// Cargar los 3 datos demo para no escribir tanto
			if (opcion == 1) {
				if (hospital == null) {
					hospital = new Sistema("Hospital Central Universitario");
				}

				// 3 Especialidades
				hospital.setEspecialidad("Cardiologia");
				hospital.setEspecialidad("Pediatria");
				hospital.setEspecialidad("Traumatologia");

				// 3 Medicos
				Medico m1 = new Medico("Dr. Carlos Lopez", 101, 1);
				Medico m2 = new Medico("Dra. Laura Gomez", 102, 2);
				Medico m3 = new Medico("Dr. Juan Ramirez", 103, 3);
				m1.registroEnSistema(hospital);
				m2.registroEnSistema(hospital);
				m3.registroEnSistema(hospital);

				// 3 Enfermeros
				Enfermero e1 = new Enfermero("Enf. Ana Sanchez", 201, 1);
				Enfermero e2 = new Enfermero("Enf. Sofia Torres", 202, 2);
				Enfermero e3 = new Enfermero("Enf. Diego Vargas", 203, 3);
				e1.registroEnSistema(hospital);
				e2.registroEnSistema(hospital);
				e3.registroEnSistema(hospital);

				// 3 Pacientes (Garcia Sanchez queda antes que Soriano Mendez de A a Z)
				Paciente p1 = new Paciente("Gabriel", "Soriano Mendez", 1);
				Paciente p2 = new Paciente("Ledesma", "Garcia Sanchez", 1);
				Paciente p3 = new Paciente("Hugo", "Zapata Moran", 2);
				p1.registroEnSistema(hospital);
				p2.registroEnSistema(hospital);
				p3.registroEnSistema(hospital);

				System.out.println("Objetos demo cargados en: " + hospital.getNombreHospital());
				System.out.println("- 3 Especialidades registradas");
				System.out.println("- 3 Medicos registrados");
				System.out.println("- 3 Enfermeros registrados");
				System.out.println("- 3 Pacientes en espera");

			// Crear hospital desde cero sin especialidades
			} else if (opcion == 2) {
				System.out.print("Ingresa el nombre del hospital: ");
				String nombreH = sc.nextLine();
				hospital = new Sistema(nombreH);
				System.out.println("Hospital '" + nombreH + "' creado. Ahora puedes agregarle especialidades.");

			// Salir del programa
			} else if (opcion == 0) {
				System.out.println("Saliendo del sistema...");

			// Validar que exista un hospital antes de hacer cualquier otra cosa
			} else if (hospital == null) {
				System.out.println("Primero debes crear un hospital con [2] o cargar la demo con [1].");

			// Agregar nueva especialidad al hospital
			} else if (opcion == 3) {
				System.out.print("Nombre de la especialidad: ");
				String esp = sc.nextLine();
				hospital.setEspecialidad(esp);
				System.out.println("Especialidad agregada correctamente.");

			// Dar de alta a un medico nuevo
			} else if (opcion == 4) {
				if (hospital.getNumeroEspecialidades() == 0) {
					System.out.println("No hay especialidades todavia. Agrega una primero en la opcion [3].");
				} else {
					System.out.print("Nombre del medico: ");
					String nom = sc.nextLine();
					System.out.print("Cedula: ");
					int ced = sc.nextInt();
					sc.nextLine();
					System.out.println("Especialidades en el hospital:");
					hospital.getEspecialidad();
					System.out.print("Numero de la especialidad del medico: ");
					int esp = sc.nextInt();
					sc.nextLine();

					Medico nuevoMed = new Medico(nom, ced, esp);
					nuevoMed.registroEnSistema(hospital);
					System.out.println("Medico dado de alta en el hospital.");
				}

			// Dar de alta a un enfermero nuevo
			} else if (opcion == 5) {
				if (hospital.getNumeroEspecialidades() == 0) {
					System.out.println("No hay especialidades todavia. Agrega una primero en la opcion [3].");
				} else {
					System.out.print("Nombre del enfermero: ");
					String nom = sc.nextLine();
					System.out.print("Cedula: ");
					int ced = sc.nextInt();
					sc.nextLine();
					System.out.println("Especialidades en el hospital:");
					hospital.getEspecialidad();
					System.out.print("Numero de la especialidad del enfermero: ");
					int esp = sc.nextInt();
					sc.nextLine();

					Enfermero nuevoEnf = new Enfermero(nom, ced, esp);
					nuevoEnf.registroEnSistema(hospital);
					System.out.println("Enfermero dado de alta en el hospital.");
				}

			// Registrar paciente en el hospital
			} else if (opcion == 6) {
				if (hospital.getNumeroEspecialidades() == 0) {
					System.out.println("No hay especialidades todavia. Agrega una primero en la opcion [3].");
				} else {
					System.out.print("Nombre(s) del paciente: ");
					String nom = sc.nextLine();
					System.out.print("Apellido(s) del paciente: ");
					String ape = sc.nextLine();
					System.out.println("Especialidades disponibles:");
					hospital.getEspecialidad();
					System.out.print("Numero de especialidad requerida: ");
					int esp = sc.nextInt();
					sc.nextLine();

					Paciente nuevoPac = new Paciente(nom, ape, esp);
					boolean reg = nuevoPac.registroEnSistema(hospital);
					if (reg) {
						System.out.println("Paciente registrado en espera de atencion.");
					} else {
						System.out.println("No se pudo registrar: no hay medico disponible con cupo para esa especialidad.");
					}
				}

			// El medico pide al sistema un paciente en espera
			} else if (opcion == 7) {
				ArrayList<Medico> medicos = hospital.getMedicos();
				if (medicos.size() == 0) {
					System.out.println("No hay medicos registrados.");
				} else {
					System.out.println("\nSelecciona el medico que solicita paciente:");
					for (int i = 0; i < medicos.size(); i++) {
						Medico m = medicos.get(i);
						System.out.println("[" + (i + 1) + "] " + m.getNombre() + " (Esp: " + m.getEspecialidad() + ", Pacientes: " + m.getNoPacientes() + ")");
					}
					System.out.print("Numero: ");
					int sel = sc.nextInt() - 1;
					sc.nextLine();

					if (sel >= 0 && sel < medicos.size()) {
						Medico m = medicos.get(sel);
						boolean asignado = m.solicitarPaciente(hospital);
						if (asignado) {
							System.out.println("Paciente asignado con exito.");
						} else {
							System.out.println("No habia pacientes en espera de esa especialidad o el medico ya tiene 10.");
						}
					} else {
						System.out.println("Seleccion invalida.");
					}
				}

			// El paciente pide consulta con su medico
			} else if (opcion == 8) {
				ArrayList<Paciente> pacientes = hospital.getPacientes();
				if (pacientes.size() == 0) {
					System.out.println("No hay pacientes registrados.");
				} else {
					System.out.println("\nSelecciona el paciente que entrara a consulta:");
					for (int i = 0; i < pacientes.size(); i++) {
						Paciente p = pacientes.get(i);
						System.out.println("[" + (i + 1) + "] " + p.getApellido() + " " + p.getNombre() + " - Estado: " + p.getEstado());
					}
					System.out.print("Numero: ");
					int sel = sc.nextInt() - 1;
					sc.nextLine();

					if (sel >= 0 && sel < pacientes.size()) {
						Paciente p = pacientes.get(sel);
						boolean entro = p.solicitarConsulta();
						if (entro) {
							System.out.println("Consulta iniciada.");
							System.out.println("Estado actual de " + p.getApellido() + ": " + p.getEstado());
						} else {
							System.out.println("No se pudo iniciar: el paciente no tiene medico asignado o el medico ya esta ocupado.");
						}
					} else {
						System.out.println("Seleccion invalida.");
					}
				}

			// El medico le da tratamiento al que esta atendiendo y lo manda a hospitalizacion
			} else if (opcion == 9) {
				ArrayList<Medico> medicos = hospital.getMedicos();
				if (medicos.size() == 0) {
					System.out.println("No hay medicos en el hospital.");
				} else {
					System.out.println("\nSelecciona el medico que dara el tratamiento:");
					for (int i = 0; i < medicos.size(); i++) {
						Medico m = medicos.get(i);
						System.out.println("[" + (i + 1) + "] " + m.getNombre());
					}
					System.out.print("Numero: ");
					int sel = sc.nextInt() - 1;
					sc.nextLine();

					if (sel >= 0 && sel < medicos.size()) {
						Medico m = medicos.get(sel);
						Paciente atendido = m.getAtendiendo();
						if (atendido == null) {
							System.out.println("Este medico no tiene a nadie en consulta en este momento.");
						} else {
							System.out.println("Atendiendo a: " + atendido.getApellido() + " " + atendido.getNombre());
							System.out.print("Prescripcion medica: ");
							String prescripcion = sc.nextLine();
							boolean ok = m.darTratamiento(atendido, prescripcion);
							if (ok) {
								System.out.println("Tratamiento asignado y paciente hospitalizado.");
								System.out.println("Estado del paciente: " + atendido.getEstado());
							} else {
								System.out.println("No se pudo hospitalizar: no hay enfermeros disponibles con cupo (max 3).");
							}
						}
					} else {
						System.out.println("Seleccion invalida.");
					}
				}

			// Imprimir catalogo de especialidades
			} else if (opcion == 10) {
				System.out.println("\n--- Especialidades del Hospital ---");
				if (hospital.getNumeroEspecialidades() == 0) {
					System.out.println("No hay especialidades registradas.");
				} else {
					hospital.getEspecialidad();
				}

			// Mostrar medicos y sus pacientes asignados en A-Z
			} else if (opcion == 11) {
				ArrayList<Medico> medicos = hospital.getMedicos();
				if (medicos.size() == 0) {
					System.out.println("No hay medicos registrados.");
				} else {
					System.out.println("\n--- Medicos Registrados ---");
					for (int i = 0; i < medicos.size(); i++) {
						Medico m = medicos.get(i);
						System.out.println("\nMedico: " + m.getNombre() + " | Cedula: " + m.getCedula() + " | Esp: " + m.getEspecialidad() + " | Pacientes: " + m.getNoPacientes());
						System.out.println("Pacientes a cargo (A-Z por apellido):");
						if (m.getPacientesAsignados().size() == 0) {
							System.out.println("  (Sin pacientes asignados)");
						} else {
							m.verListaPacientes();
						}
					}
				}

			// Mostrar enfermeros y sus pacientes hospitalizados en A-Z
			} else if (opcion == 12) {
				ArrayList<Enfermero> enfermeros = hospital.getEnfermeros();
				if (enfermeros.size() == 0) {
					System.out.println("No hay enfermeros registrados.");
				} else {
					System.out.println("\n--- Enfermeros Registrados ---");
					for (int i = 0; i < enfermeros.size(); i++) {
						Enfermero enf = enfermeros.get(i);
						System.out.println("\nEnfermero: " + enf.getNombre() + " | Cedula: " + enf.getCedula() + " | Esp: " + enf.getEspecialidad() + " | Pacientes: " + enf.getNoPacientes());
						System.out.println("Pacientes hospitalizados a cargo (A-Z por apellido):");
						if (enf.getNoPacientes() == 0) {
							System.out.println("  (Sin pacientes hospitalizados)");
						} else {
							enf.verListaPacientes();
						}
					}
				}

			// Ver lista de todos los pacientes del hospital y su estado
			} else if (opcion == 13) {
				ArrayList<Paciente> pacientes = hospital.getPacientes();
				if (pacientes.size() == 0) {
					System.out.println("No hay pacientes en el hospital.");
				} else {
					System.out.println("\n--- Lista de Pacientes ---");
					for (int i = 0; i < pacientes.size(); i++) {
						Paciente p = pacientes.get(i);
						System.out.println("\n[" + (i + 1) + "] " + p.getApellido() + " " + p.getNombre() + " | Esp: " + p.getEspecialidad() + " | Estado: " + p.getEstado());
						p.verTratamiento();
					}
				}

			// Opcion que no existe en el menu
			} else {
				System.out.println("Opcion no valida.");
			}
		}
	}
}
