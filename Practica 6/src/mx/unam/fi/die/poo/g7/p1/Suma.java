package mx.unam.fi.die.poo.g7.p1;

import java.util.Scanner;

/**
 * Lee dos enteros por consola y muestra su suma.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Suma {
    /**
     * Constructor por defecto de la clase {@code Suma}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Suma() {
    }

    /**
     * Metodo {@code main} de la clase {@code Suma}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int suma = a + b;

        System.out.println(suma);
    }
}
