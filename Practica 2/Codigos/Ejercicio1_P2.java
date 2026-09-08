import java.util.Scanner;

public class AnalisisResultados {

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
