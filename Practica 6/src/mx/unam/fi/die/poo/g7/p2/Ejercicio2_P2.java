package mx.unam.fi.die.poo.g7.p2;

import java.util.Scanner;
import java.lang.Math;

/**
 * Muestra tabla de proyeccion con ciclos y formato tabular.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Ejercicio2_P2 {
    /**
     * Constructor por defecto de la clase {@code Ejercicio2_P2}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Ejercicio2_P2() {
    }

    /**
     * Metodo {@code main} de la clase {@code Ejercicio2_P2}.
     * @param args[] argumento del metodo.
     */
    public static void main(String args[]) {
        Scanner leer = new Scanner(System.in);
        int contador = 0, numero, mayor = 0;
        while(contador < 10){
            System.out.println("Ingrese su " + (contador+1)+ " numero:");
            numero = leer.nextInt();
            numero = Math.abs(numero);
            if(contador == 0){
                mayor = numero;
            }else if(contador != 0 && numero > mayor){
                mayor = numero;
            }
            contador++;
        }
        System.out.println("El numero mayor es: " + mayor);
        leer.close();
    }
}
