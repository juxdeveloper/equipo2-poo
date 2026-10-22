package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import interfaces.AtencionMedica;
import pacientes.*;
import personal.*;

public class Main {

	private static Scanner entrada = new Scanner(System.in);
	private static List<Sistema> hospitales = new ArrayList<>();
	private static Sistema actual;
	private static Sistema demo;
	private static Paciente pacienteActual;
	private static boolean salir = false;
	private static boolean iniciado = false;

	public static void main(String[] args){
		while(!salir){
			int opcion = menu("MENU PRINCIPAL",
					"0. Demo\n7. Crear o cambiar hospital\n12. Cambiar paciente activo"
					+ "\n---------------------------------------\nFLUJO DEL PACIENTE"
					+ "\n1. Registrar paciente\n2. Solicitar consulta y asignar medico"
					+ "\n3. Iniciar consulta\n4. Indicar tratamiento y finalizar consulta"
					+ "\n5. Asignar enfermero, si requiere cuidados\n6. Aplicar tratamiento y dar salida"
					+ "\n---------------------------------------\nADMINISTRACION"
					+ "\n8. Especialidades\n9. Medicos\n10. Enfermeros\n11. Jefatura de enfermeria"
					+ "\n---------------------------------------\n13. Salir", 13);
			if(salir)
				break;
			if(opcion != 0 && opcion != 7 && opcion != 13 && actual == null){
				System.out.println("Primero crea un hospital en la opcion 7 o activa la demo con 0.");
				continue;
			}
			switch(opcion){
				case 0: activarDemo(); break;
				case 1: registrarPaciente(); break;
				case 2: case 3: case 4: case 5: case 6: flujoPaciente(opcion); break;
				case 7: hospitales(); break;
				case 8: especialidades(); break;
				case 9: medicos(); break;
				case 10: enfermeros(); break;
				case 11: jefatura(); break;
				case 12: cambiarPaciente(); break;
				case 13: salir = true; break;
			}
		}
		System.out.println("Programa finalizado.");
	}

	private static int menu(String titulo, String opciones, int maximo){
		if(iniciado && !salir){
			System.out.print("\nPresiona enter para continuar");
			if(entrada.hasNextLine())
				entrada.nextLine();
			else
				salir = true;
		}
		if(salir)
			return 0;
		iniciado = true;
		System.out.print("\033[H\033[2J\033[3J");
		System.out.flush();
		System.out.println("\n=======================================");
		if(actual == null)
			System.out.println("Hospital activo: ninguno");
		else
			System.out.println("Hospital activo: [" + actual.getId() + "] " + actual.getNombreHospital());
		if(pacienteActual != null)
			System.out.println("Paciente activo: [" + actual.getIdPaciente(pacienteActual) + "] "
					+ pacienteActual.getNombreCompleto() + " | " + pacienteActual.getEstado());
		else
			System.out.println("Paciente activo: ninguno");
		System.out.println(titulo);
		System.out.println("=======================================");
		System.out.println(opciones);
		return leerNumero("Opcion: ", 0, maximo);
	}

	private static String leerTexto(String mensaje){
		while(!salir){
			System.out.print(mensaje);
			if(!entrada.hasNextLine()){
				salir = true;
				return null;
			}
			String texto = entrada.nextLine().trim();
			if(!texto.isBlank())
				return texto;
			System.out.println("ERROR : El dato no puede quedar vacio");
		}
		return null;
	}

	private static int leerNumero(String mensaje, int minimo, int maximo){
		while(!salir){
			String texto = leerTexto(mensaje);
			if(salir)
				return 0;
			int numero = 0;
			boolean valido = true;
			for(int i = 0; i < texto.length() && valido; i++){
				char caracter = texto.charAt(i);
				if(caracter < '0' || caracter > '9')
					valido = false;
				else {
					int digito = caracter - '0';
					if(numero > (Integer.MAX_VALUE - digito) / 10)
						valido = false;
					else
						numero = numero * 10 + digito;
				}
			}
			if(valido && numero >= minimo && numero <= maximo)
				return numero;
			System.out.println("ERROR : Ingresa un numero entre " + minimo + " y " + maximo);
		}
		return 0;
	}

	private static boolean porApellido(){
		return leerNumero("Ordenar por nombre (1) o apellido paterno (2): ", 1, 2) == 2;
	}

	private static void activarHospital(Sistema sistema){
		if(actual != sistema)
			pacienteActual = null;
		actual = sistema;
	}

	private static void hospitales(){
		int opcion = -1;
		while(!salir && opcion != 0){
			opcion = menu("HOSPITALES", "1. Crear hospital\n2. Cambiar hospital activo\n3. Resumen del hospital activo\n0. Volver", 3);
			if(salir)
				return;
			if(opcion == 1){
				String nombre = leerTexto("Nombre del hospital: ");
				if(salir)
					return;
				activarHospital(new Sistema(nombre));
				hospitales.add(actual);
				System.out.println("INFO : Hospital creado y activado con ID " + actual.getId());
			} else if(opcion == 2){
				if(hospitales.isEmpty()){
					System.out.println("Primero crea un hospital.");
					continue;
				}
				for(Sistema sistema : hospitales)
					System.out.println("[" + sistema.getId() + "] " + sistema.getNombreHospital());
				int id = leerNumero("ID del hospital (0 = cancelar): ", 0, Integer.MAX_VALUE);
				if(salir || id == 0)
					continue;
				Sistema elegido = null;
				for(Sistema sistema : hospitales)
					if(sistema.getId() == id)
						elegido = sistema;
				if(elegido == null)
					System.out.println("ERROR : Hospital no encontrado");
				else {
					activarHospital(elegido);
					System.out.println("INFO : Hospital activo cambiado a " + actual.getNombreHospital());
				}
			} else if(opcion == 3){
				if(actual == null)
					System.out.println("Primero crea un hospital.");
				else
					resumen();
			}
		}
	}

	private static void especialidades(){
		int opcion = -1;
		while(!salir && opcion != 0){
			opcion = menu("ESPECIALIDADES", "1. Registrar especialidad\n2. Mostrar catalogo\n0. Volver", 2);
			if(salir)
				return;
			if(opcion == 1){
				int numero = leerNumero("Numero de especialidad: ", 1, 99);
				String nombre = leerTexto("Nombre de especialidad: ");
				if(!salir && actual.registroEspecialidad(numero, nombre))
					System.out.println("INFO : Especialidad registrada");
			} else if(opcion == 2)
				actual.verEspecialidades();
		}
	}

	private static int elegirEspecialidad(boolean general){
		actual.verEspecialidades();
		while(!salir){
			int numero = leerNumero(general ? "Numero de especialidad: "
					: "Numero de especialidad (0 = cancelar): ", 0, 99);
			if(salir || (!general && numero == 0))
				return -1;
			if(actual.getEspecialidad(numero) != null)
				return numero;
			System.out.println("ERROR : Registra primero la especialidad en la opcion 8 del menu principal");
		}
		return -1;
	}

	private static void medicos(){
		int opcion = -1;
		while(!salir && opcion != 0){
			opcion = menu("MEDICOS", "1. Registrar medico\n2. Ver pacientes de un medico\n0. Volver", 2);
			if(salir)
				return;
			if(opcion == 1)
				registrarMedico();
			else if(opcion == 2){
				Medico medico = seleccionarMedico();
				if(medico != null){
					boolean apellido = porApellido();
					if(!salir)
						medico.verListaPacientes(apellido);
				}
			}
		}
	}

	private static void registrarMedico(){
		int tipo = leerNumero("Medico general (1) o especialista (2): ", 1, 2);
		String nombre = leerTexto("Nombre(s): ");
		String paterno = leerTexto("Apellido paterno: ");
		String materno = leerTexto("Apellido materno: ");
		int cedula = leerNumero("Cedula (al menos 6 digitos): ", 100000, Integer.MAX_VALUE);
		if(salir)
			return;
		Medico medico;
		if(tipo == 1){
			int consultorio = leerNumero("Consultorio: ", 1, 99);
			if(salir)
				return;
			MedicoGeneral general = new MedicoGeneral(nombre, paterno, materno, cedula);
			general.setNoConsultorio(consultorio);
			medico = general;
		} else {
			int especialidad = elegirEspecialidad(false);
			if(especialidad == -1)
				return;
			medico = new MedicoEspecialista(nombre, paterno, materno, cedula, especialidad);
		}
		if(!medico.registroEnSistema(actual))
			System.out.println("Verifica que la cedula no este registrada en este hospital.");
	}

	private static Medico seleccionarMedico(){
		if(actual.getNoMedicos() == 0){
			System.out.println("Primero registra medicos en este hospital.");
			return null;
		}
		boolean apellido = porApellido();
		if(salir)
			return null;
		List<Medico> lista = new ArrayList<>();
		for(int i = 0; i < actual.getNoMedicos(); i++)
			lista.add(actual.getMedico(i));
		if(apellido)
			lista.sort(Comparator.comparing(Medico::getNombreCompleto, String.CASE_INSENSITIVE_ORDER));
		else
			lista.sort(Comparator.comparing(Medico::getOrdenNombre, String.CASE_INSENSITIVE_ORDER));
		for(Medico medico : lista)
			System.out.println(medico.getCedula() + " | " + medico.getNombreCompleto() + " | "
					+ actual.getEspecialidad(medico.getEspecialidad()) + " | Cupo: " + medico.getNoPacientes() + "/10");
		int cedula = leerNumero("Cedula del medico (0 = cancelar): ", 0, Integer.MAX_VALUE);
		if(salir || cedula == 0)
			return null;
		Medico medico = actual.getMedicoPorCedula(cedula);
		if(medico == null)
			System.out.println("ERROR : Medico no encontrado en este hospital");
		return medico;
	}

	private static void indicarTratamiento(Medico medico){
		Paciente paciente = medico.getEnConsulta();
		if(paciente == null){
			System.out.println("Primero inicia una consulta.");
			return;
		}
		System.out.println("Paciente: " + paciente.getNombreCompleto());
		String tratamiento = leerTexto("Tratamiento indicado: ");
		int medicamento = leerNumero("Requiere medicamento y cuidados de enfermeria (1 = si, 0 = no): ", 0, 1);
		if(salir)
			return;
		paciente.setTratamiento(tratamiento);
		paciente.setNecesitaMedicamento(medicamento == 1);
		AtencionMedica atencion = medico;
		if(atencion.darTratamiento(paciente)){
			if(paciente.getEstado() == Paciente.Estado.ATENDIDO)
				System.out.println("Paciente atendido. No requiere los pasos 5 y 6.");
			else
				System.out.println("Continua con el paso 5: asignar enfermero.");
		}
	}

	private static void enfermeros(){
		int opcion = -1;
		while(!salir && opcion != 0){
			opcion = menu("ENFERMEROS", "1. Registrar enfermero\n2. Ver pacientes de un enfermero\n0. Volver", 2);
			if(salir)
				return;
			if(opcion == 1)
				registrarEnfermero();
			else if(opcion == 2){
				Enfermero enfermero = seleccionarEnfermero(false, -1);
				if(enfermero != null){
					boolean apellido = porApellido();
					if(!salir)
						enfermero.verListaPacientes(apellido);
				}
			}
		}
	}

	private static void registrarEnfermero(){
		int tipo = leerNumero("Enfermero general (1), especialista (2) o jefe (3): ", 1, 3);
		String nombre = leerTexto("Nombre(s): ");
		String paterno = leerTexto("Apellido paterno: ");
		String materno = leerTexto("Apellido materno: ");
		int cedula = leerNumero("Cedula (al menos 6 digitos): ", 100000, Integer.MAX_VALUE);
		if(salir)
			return;
		Enfermero enfermero;
		if(tipo == 1)
			enfermero = new Enfermero(nombre, paterno, materno, cedula);
		else {
			int especialidad = elegirEspecialidad(tipo == 3);
			if(especialidad == -1)
				return;
			if(tipo == 2)
				enfermero = new EnfermeroEspecialista(nombre, paterno, materno, cedula, especialidad);
			else
				enfermero = new JefeEnfermeria(nombre, paterno, materno, cedula, especialidad);
		}
		if(!enfermero.registroEnSistema(actual))
			System.out.println("Verifica que la cedula no este registrada en este hospital.");
	}

	private static Enfermero seleccionarEnfermero(boolean soloJefes, int area){
		List<Enfermero> lista = new ArrayList<>();
		for(int i = 0; i < actual.getNoEnfermeros(); i++){
			Enfermero enfermero = actual.getEnfermero(i);
			if((!soloJefes || enfermero instanceof JefeEnfermeria)
					&& (area == -1 || enfermero.getEspecialidad() == area))
				lista.add(enfermero);
		}
		if(lista.isEmpty()){
			System.out.println("No hay enfermeros registrados que cumplan esta seleccion.");
			return null;
		}
		boolean apellido = porApellido();
		if(salir)
			return null;
		if(apellido)
			lista.sort(Comparator.comparing(Enfermero::getNombreCompleto, String.CASE_INSENSITIVE_ORDER));
		else
			lista.sort(Comparator.comparing(Enfermero::getOrdenNombre, String.CASE_INSENSITIVE_ORDER));
		for(Enfermero enfermero : lista)
			System.out.println(enfermero.getCedula() + " | " + enfermero.getNombreCompleto() + " | "
					+ actual.getEspecialidad(enfermero.getEspecialidad()) + " | Cupo: " + enfermero.getNoPacientes()
					+ "/3 | Turno: " + (enfermero.getTurno() ? "Vespertino" : "Matutino")
					+ (enfermero instanceof JefeEnfermeria ? " | Jefe" : ""));
		int cedula = leerNumero("Cedula del enfermero (0 = cancelar): ", 0, Integer.MAX_VALUE);
		if(salir || cedula == 0)
			return null;
		Enfermero enfermero = actual.getEnfermeroPorCedula(cedula);
		if(!lista.contains(enfermero)){
			System.out.println("ERROR : El enfermero no pertenece a esta seleccion");
			return null;
		}
		return enfermero;
	}

	private static Paciente pacienteEnfermero(Enfermero enfermero){
		if(enfermero.getNoPacientes() == 0){
			System.out.println("Este enfermero no tiene pacientes asignados.");
			return null;
		}
		boolean apellido = porApellido();
		if(salir)
			return null;
		enfermero.verListaPacientes(apellido);
		Paciente paciente = seleccionarPaciente();
		if(paciente != null && !enfermero.tienePaciente(paciente)){
			System.out.println("ERROR : El paciente no pertenece a este enfermero");
			return null;
		}
		return paciente;
	}

	private static void registrarPaciente(){
		int tipo = leerNumero("Paciente general (1) o de especialidad (2): ", 1, 2);
		String nombre = leerTexto("Nombre(s): ");
		String paterno = leerTexto("Apellido paterno: ");
		String materno = leerTexto("Apellido materno: ");
		if(salir)
			return;
		Paciente paciente;
		if(tipo == 1){
			boolean urgencia = leerNumero("Es urgencia (1 = si, 0 = no): ", 0, 1) == 1;
			if(salir)
				return;
			paciente = new PacienteGeneral(nombre, paterno, materno, urgencia);
		} else {
			int especialidad = elegirEspecialidad(false);
			if(especialidad == -1)
				return;
			paciente = new PacienteEspecialidad(nombre, paterno, materno, especialidad);
		}
		if(paciente.registroEnSistema(actual)){
			pacienteActual = paciente;
			System.out.println("ID del paciente en este hospital: " + actual.getIdPaciente(paciente));
			System.out.println("Paciente seleccionado. Continua con el paso 2: solicitar consulta.");
		}
	}

	private static Paciente seleccionarPaciente(){
		if(actual.getNoPacientes() == 0){
			System.out.println("Primero registra pacientes en este hospital.");
			return null;
		}
		int id = leerNumero("ID del paciente (0 = cancelar): ", 0, actual.getNoPacientes());
		if(salir || id == 0)
			return null;
		return actual.getPaciente(id);
	}

	private static void cambiarPaciente(){
		if(actual.getNoPacientes() == 0){
			System.out.println("Primero registra pacientes en este hospital.");
			return;
		}
		boolean apellido = porApellido();
		if(salir)
			return;
		List<Paciente> lista = new ArrayList<>();
		for(int i = 1; i <= actual.getNoPacientes(); i++)
			lista.add(actual.getPaciente(i));
		if(apellido)
			lista.sort(Comparator.comparing(Paciente::getNombreCompleto, String.CASE_INSENSITIVE_ORDER));
		else
			lista.sort(Comparator.comparing(Paciente::getOrdenNombre, String.CASE_INSENSITIVE_ORDER));
		System.out.println("PACIENTES DEL HOSPITAL: " + actual.getNombreHospital());
		for(Paciente paciente : lista)
			System.out.println("ID " + actual.getIdPaciente(paciente) + " | " + paciente.getNombreCompleto()
					+ " | " + actual.getEspecialidad(paciente.getEspecialidad()) + " | " + paciente.getEstado());
		Paciente elegido = seleccionarPaciente();
		if(elegido != null){
			pacienteActual = elegido;
			verPaciente(elegido);
		}
	}

	private static void verPaciente(Paciente paciente){
		System.out.println("Paciente: " + paciente.getNombreCompleto());
		System.out.println("Especialidad: " + actual.getEspecialidad(paciente.getEspecialidad()));
		System.out.println("Estado: " + paciente.getEstado());
		if(paciente instanceof PacienteGeneral)
			System.out.println("Urgencia: " + (((PacienteGeneral) paciente).getEsUrgencia() ? "Si" : "No"));
		System.out.println("Medico: " + (paciente.getMedico() == null
				? "Sin asignar" : paciente.getMedico().getNombreCompleto()));
		System.out.println("Enfermero: " + (paciente.getEnfermero() == null
				? "Sin asignar" : paciente.getEnfermero().getNombreCompleto()));
		paciente.verTratamiento();
	}

	private static void flujoPaciente(int paso){
		if(pacienteActual == null)
			cambiarPaciente();
		if(pacienteActual == null)
			return;
		Medico medico = pacienteActual.getMedico();
		if(paso == 2){
			if(pacienteActual.solicitarConsulta())
				System.out.println("Continua con el paso 3: iniciar consulta.");
		} else if(paso == 3 || paso == 4){
			if(medico == null){
				System.out.println("Primero completa el paso 2: solicitar consulta y asignar medico.");
				return;
			}
			if(paso == 3){
				if(medico.darConsulta(pacienteActual))
					System.out.println("Continua con el paso 4: indicar tratamiento.");
			} else if(medico.getEnConsulta() == pacienteActual)
				indicarTratamiento(medico);
			else
				System.out.println("Primero completa el paso 3: iniciar la consulta de este paciente.");
		} else if(pacienteActual.getEstado() == Paciente.Estado.ATENDIDO){
			System.out.println("Este paciente ya fue atendido y sus cupos estan liberados.");
		} else if(pacienteActual.getEstado() != Paciente.Estado.EN_TRATAMIENTO){
			System.out.println("Primero completa el paso 4: indicar tratamiento y finalizar consulta.");
		} else if(paso == 5){
			Enfermero enfermero = pacienteActual.getEnfermero();
			if(enfermero == null)
				enfermero = actual.asignarEnfermero(pacienteActual);
			if(enfermero == null)
				System.out.println("No hay enfermeros compatibles con cupo. Puedes registrarlos en la opcion 10.");
			else {
				System.out.println("Enfermero asignado: " + enfermero.getNombreCompleto());
				System.out.println("Continua con el paso 6: aplicar tratamiento y dar salida.");
			}
		} else if(pacienteActual.getEnfermero() == null){
			System.out.println("Primero completa el paso 5: asignar enfermero.");
		} else {
			interfaces.AtencionMedica atencion = pacienteActual.getEnfermero();
			if(atencion.darTratamiento(pacienteActual))
				System.out.println("Flujo completo. El paciente fue atendido y puede salir del hospital.");
		}
	}

	private static void jefatura(){
		Enfermero elegido = seleccionarEnfermero(true, -1);
		if(elegido == null)
			return;
		JefeEnfermeria jefe = (JefeEnfermeria) elegido;
		int opcion = -1;
		while(!salir && opcion != 0){
			opcion = menu("JEFATURA: " + jefe.getNombreCompleto(),
					"1. Cambiar turno\n2. Transferir paciente entre enfermeros\n0. Volver", 2);
			if(salir)
				return;
			if(opcion == 1){
				Enfermero enfermero = seleccionarEnfermero(false, jefe.getEspecialidad());
				if(enfermero != null){
					boolean turno = leerNumero("Turno matutino (0) o vespertino (1): ", 0, 1) == 1;
					if(!salir)
						jefe.cambiarTurno(enfermero, turno);
				}
			} else if(opcion == 2){
				System.out.println("Selecciona al enfermero actual:");
				Enfermero origen = seleccionarEnfermero(false, jefe.getEspecialidad());
				if(origen == null)
					continue;
				Paciente paciente = pacienteEnfermero(origen);
				if(paciente == null)
					continue;
				System.out.println("Selecciona al nuevo enfermero:");
				Enfermero destino = seleccionarEnfermero(false, jefe.getEspecialidad());
				if(destino != null)
					jefe.cambiarEnfermero(origen, destino, paciente);
			}
		}
	}

	private static void resumen(){
		int espera = 0;
		int consulta = 0;
		int tratamiento = 0;
		int atendidos = 0;
		for(int i = 1; i <= actual.getNoPacientes(); i++){
			switch(actual.getPaciente(i).getEstado()){
				case EN_ESPERA: espera++; break;
				case EN_CONSULTA: consulta++; break;
				case EN_TRATAMIENTO: tratamiento++; break;
				case ATENDIDO: atendidos++; break;
			}
		}
		System.out.println("Medicos: " + actual.getNoMedicos());
		System.out.println("Enfermeros, incluyendo jefes: " + actual.getNoEnfermeros());
		System.out.println("Pacientes registrados: " + actual.getNoPacientes());
		System.out.println("En espera: " + espera + " | En consulta: " + consulta
				+ " | En tratamiento: " + tratamiento + " | Atendidos: " + atendidos);
	}

	private static void activarDemo(){
		if(demo == null){
			demo = new Sistema("Hospital Demo");
			hospitales.add(demo);
			demo.registroEspecialidad(1, "Cardiologia");
			demo.registroEspecialidad(2, "Pediatria");
			MedicoGeneral general = new MedicoGeneral("Ana", "Garcia", "Lopez", 100001);
			general.setNoConsultorio(1);
			general.registroEnSistema(demo);
			new MedicoEspecialista("Luis", "Perez", "Ruiz", 100002, 1).registroEnSistema(demo);
			new MedicoEspecialista("Elena", "Torres", "Diaz", 100003, 2).registroEnSistema(demo);
			new Enfermero("Mario", "Sanchez", "Vega", 200001).registroEnSistema(demo);
			new Enfermero("Lucia", "Mendez", "Rios", 200002).registroEnSistema(demo);
			new EnfermeroEspecialista("Sofia", "Ramirez", "Soto", 200003, 1).registroEnSistema(demo);
			new EnfermeroEspecialista("Pablo", "Flores", "Cruz", 200004, 2).registroEnSistema(demo);
			new JefeEnfermeria("Laura", "Castro", "Luna", 200005, 1).registroEnSistema(demo);
			new JefeEnfermeria("Diego", "Morales", "Leon", 200006, 0).registroEnSistema(demo);
			new PacienteGeneral("Carlos", "Zamora", "Ramos", false).registroEnSistema(demo);
			new PacienteGeneral("Beatriz", "Alonso", "Gil", true).registroEnSistema(demo);
			new PacienteEspecialidad("Daniel", "Ortega", "Reyes", 1).registroEnSistema(demo);
			new PacienteEspecialidad("Alicia", "Navarro", "Mora", 2).registroEnSistema(demo);
			new PacienteEspecialidad("Eva", "Aguilar", "Vidal", 1).registroEnSistema(demo);
			new PacienteGeneral("Felipe", "Herrera", "Nava", false).registroEnSistema(demo);
			demo.getPaciente(1).solicitarConsulta();
			demo.getPaciente(3).solicitarConsulta();
			Medico medico = demo.getPaciente(3).getMedico();
			medico.darConsulta(demo.getPaciente(3));
			demo.getPaciente(3).setTratamiento("Aplicar medicamento segun indicacion medica");
			demo.getPaciente(3).setNecesitaMedicamento(true);
			medico.darTratamiento(demo.getPaciente(3));
		}
		activarHospital(demo);
		if(pacienteActual == null)
			pacienteActual = demo.getPaciente(2);
		System.out.println("INFO : Hospital Demo activo. Medicos: 100001-100003. Enfermeros: 200001-200006.");
		System.out.println("Pacientes: ID 1-6. Puedes cambiar el paciente activo desde la opcion 12.");
		System.out.println("La opcion 0 reutiliza este hospital y conserva sus datos.");
	}
}
