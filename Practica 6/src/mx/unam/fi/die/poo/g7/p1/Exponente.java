package mx.unam.fi.die.poo.g7.p1;

import java.util.Scanner;
/**
 * Lee base y exponente y calcula la potencia con ciclo.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Exponente
{
    /**
     * Constructor por defecto de la clase {@code Exponente}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Exponente() {
    }

    /**
     * Metodo {@code main} de la clase {@code Exponente}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {

        //Crear objeto tipo scanner
        Scanner scanner = new Scanner(System.in);

        //Pedir la base y el exponente
        System.out.println("Favor de ingresar la base de la potencia");
        float base = scanner.nextInt();
        System.out.println("Ingresar exponente");
        float exp = scanner.nextInt();
        float res = 1;
        //Calcular por un bucle for
        if(exp != 0)
        {
            for (float i = 1; i <= exp; i++)
            {
                res = res * base;
            }
        }
        System.out.println("El resultado de tu potencia" + base + "^" + exp + "es: " + res);
    }
}