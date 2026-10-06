package mx.unam.fi.die.poo.g7.p4;

import java.util.Scanner;

/**
 * Punto de entrada que coordina la interaccion por consola.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Main {
    /**
     * Constructor por defecto de la clase {@code Main}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Main() {
    }

    /**
     * Metodo {@code main} de la clase {@code Main}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Escribe una oración:");
        AnalizadorPalabras analizador = new AnalizadorPalabras(entrada.nextLine());
        analizador.contarPalabras();
        System.out.println("Número de palabras duplicadas: " + analizador.obtenerNumeroDuplicadas());
        System.out.println("Palabras duplicadas y sus frecuencias:");
        analizador.mostrarDuplicadas(true);
    }
}
