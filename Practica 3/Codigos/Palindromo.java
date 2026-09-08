import java.util.Scanner;

public class Palindromo {

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

    public static boolean esPalindromo(int numero) {
        int digito1 = numero / 10000;
        int digito2 = (numero / 1000) % 10;
        int digito3 = (numero / 100) % 10; // El dígito central no necesita compararse.
        int digito4 = (numero / 10) % 10;
        int digito5 = numero % 10;

        return digito1 == digito5 && digito2 == digito4;
    }
}
