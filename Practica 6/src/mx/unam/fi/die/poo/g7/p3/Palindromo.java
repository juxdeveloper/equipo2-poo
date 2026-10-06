package mx.unam.fi.die.poo.g7.p3;

import java.util.Scanner;

/**
 * Lee un entero de cinco digitos y determina si es palindromo.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Palindromo {

    /**
     * Constructor por defecto de la clase {@code Palindromo}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Palindromo() {
    }

    /**
     * Metodo {@code main} de la clase {@code Palindromo}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numero = leerNumero(entrada);

        if (esPalindromo(numero)) {
            System.out.println(numero + " es un palíndromo.");
        } else {
            System.out.println(numero + " no es un palíndromo.");
        }

        entrada.close();
    }

    /**
     * Metodo {@code leerNumero} de la clase {@code Palindromo}.
     * @param entrada argumento del metodo.
     * @return valor producido por el metodo.
     */
    public static int leerNumero(Scanner entrada) {
        int numero = 0;

        do {
            System.out.print("Ingresa un número entero de cinco dígitos: ");

            if (entrada.hasNextInt()) {
                numero = entrada.nextInt();

                if (numero < 10000 || numero >= 100000) {
                    System.out.println("Error: el número ingresado no tiene cinco dígitos.");
                }
            } else {
                System.out.println("Error: debes ingresar un número entero.");
                entrada.next(); // Descarta la entrada inválida.
            }
        } while (numero < 10000 || numero >= 100000);

        return numero;
    }

    /**
     * Metodo {@code esPalindromo} de la clase {@code Palindromo}.
     * @param numero argumento del metodo.
     * @return valor producido por el metodo.
     */
    public static boolean esPalindromo(int numero) {
        int digito1 = numero / 10000;
        int digito2 = (numero / 1000) % 10;
        int digito3 = (numero / 100) % 10; // El dígito central no necesita compararse.
        int digito4 = (numero / 10) % 10;
        int digito5 = numero % 10;

        return digito1 == digito5 && digito2 == digito4;
    }
}
