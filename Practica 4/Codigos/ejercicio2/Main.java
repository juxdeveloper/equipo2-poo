import java.util.Scanner;

public class Main {
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
