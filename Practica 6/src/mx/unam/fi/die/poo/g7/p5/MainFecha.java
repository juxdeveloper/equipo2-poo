package mx.unam.fi.die.poo.g7.p5;

import java.util.Scanner;

/**
 * Programa que crea y muestra fechas de ejemplo.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class MainFecha {
    /**
     * Constructor por defecto de la clase {@code MainFecha}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public MainFecha() {
    }

    /**
     * Metodo {@code main} de la clase {@code MainFecha}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {
        // solicita los datos de la fecha desde el teclado
        Scanner entrada = new Scanner(System.in);
        System.out.print("Mes: ");
        int mes = entrada.nextInt();
        System.out.print("Dia: ");
        int dia = entrada.nextInt();
        System.out.print("Anio: ");
        int anio = entrada.nextInt();

        // crea y muestra la fecha ingresada
        Fecha fecha = new Fecha(mes, dia, anio);
        System.out.print("Fecha ingresada (mes/dia/anio): ");
        fecha.mostrarFecha();
        entrada.close();
    }
}
