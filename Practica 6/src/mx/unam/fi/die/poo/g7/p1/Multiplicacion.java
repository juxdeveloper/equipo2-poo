package mx.unam.fi.die.poo.g7.p1;

import java.util.Scanner;

/**
 * Algoritmo de Multiplicación
 * Paradigma: Estructurado (todo dentro de main, sin clases auxiliares)
 * Sigue el diagrama de flujo: INICIO -> ingresar n1 -> ingresar n2
 * -> mult = n1 * n2 -> imprimir resultado -> FIN
 */
public class Multiplicacion {
    /**
     * Constructor por defecto de la clase {@code Multiplicacion}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Multiplicacion() {
    }


    /**
     * Metodo {@code main} de la clase {@code Multiplicacion}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su primer número: ");
        int n1 = scanner.nextInt();

        System.out.print("Ingrese su segundo número: ");
        int n2 = scanner.nextInt();

        int mult = n1 * n2;

        System.out.println("El resultado del producto de " + n1 + " y " + n2 + " es: " + mult);

        scanner.close();
    }
}
