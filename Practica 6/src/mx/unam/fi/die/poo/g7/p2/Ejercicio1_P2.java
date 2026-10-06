package mx.unam.fi.die.poo.g7.p2;

import java.util.Scanner;

/**
 * Cuenta aprobados y reprobados de diez estudiantes y decide bonificacion.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Ejercicio1_P2 {
    /**
     * Constructor por defecto de la clase {@code Ejercicio1_P2}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Ejercicio1_P2() {
    }


    /**
     * Metodo {@code main} de la clase {@code Ejercicio1_P2}.
     * @param args argumento del metodo.
     */
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int aprobados = 0;
        int reprobados = 0;

        for (int estudiante = 1; estudiante <= 10; estudiante++) {

            int resultado;

            do {
                System.out.print("Estudiante " + estudiante + " (1=aprobó, 2=reprobó): ");
                resultado = entrada.nextInt();
            } while (resultado != 1 && resultado != 2);

            if (resultado == 1)
                aprobados++;
            else
                reprobados++;
        }

        System.out.println("\n--- Resumen ---");
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + reprobados);

        if (aprobados >= 9)
            System.out.println("¡Se bonifica al instructor!");
    }
}
