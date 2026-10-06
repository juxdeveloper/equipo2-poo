package mx.unam.fi.die.poo.g7.p1;

import java.util.Scanner;
/**
 * Lee dos enteros y una opcion y muestra la resta en el orden elegido.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Resta {
    /**
     * Constructor por defecto de la clase {@code Resta}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Resta() {
    }

    /**
     * Metodo {@code main} de la clase {@code Resta}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el primer entero a operar => ");
        int x = scanner.nextInt();
        System.out.println("Ingresa el segundo entero a operar => ");
        int y = scanner.nextInt();
        System.out.println("Ingresa la operacion a realizar => (1) num 1 - num 2 / (2) num 2 - num 1");
        int opc = scanner.nextInt();
        if(opc == 1){
            int resultado = x - y;
            System.out.println("El resultado es => " + resultado);
        }else{
            int resultado = y - x;
            System.out.println("El resultado es => "+resultado);
        }
        scanner.close();
    }
}
