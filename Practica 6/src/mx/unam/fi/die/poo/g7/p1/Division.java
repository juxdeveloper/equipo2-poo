package mx.unam.fi.die.poo.g7.p1;

import java.util.Scanner;

/**
 * Lee dos decimales y muestra su division con control de divisor cero.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Division{
    /**
     * Constructor por defecto de la clase {@code Division}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Division() {
    }

    /**
     * Metodo {@code main} de la clase {@code Division}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Beinvenido a la calculadora.");
        System.out.println("Ingrese su primer numero.");
        double x = scanner.nextDouble();
        System.out.println("Ingrese su segundo numero.");
        double y = scanner.nextDouble();

        if(y == 0){
            System.out.println("No es posible hacer la division, ya que el dividendo es 0.");
        }else{
            double resultado = x/y;
            System.out.println("El resultado de dividir " + x + " entre " + y + " es " + resultado);
        }

    }
}