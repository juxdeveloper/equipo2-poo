import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, op;
        double n1, n2;

        System.out.println("¿Qué operación deseas realizar?");
        System.out.println("1.Suma 2.Resta 3.Multiplicación 4.División 5.Módulo 6.Potencia 7.Raíz");
        op = scanner.nextInt();

        switch (op) {
            case 1: // Suma
                System.out.print("Dame el primer entero: ");
                a = scanner.nextInt();
                System.out.print("Dame el segundo entero: ");
                b = scanner.nextInt();
                System.out.println("El resultado es " + (a + b));
                break;

            case 2: // Resta
                System.out.print("Dame el primer entero: ");
                a = scanner.nextInt();
                System.out.print("Dame el segundo entero: ");
                b = scanner.nextInt();
                System.out.println("El resultado es " + (a - b));
                break;

            case 3: // Multiplicación
                System.out.print("Ingresa un primer número: ");
                n1 = scanner.nextDouble();
                System.out.print("Ingresa un segundo número: ");
                n2 = scanner.nextDouble();
                double multi = n1 * n2;
                System.out.println("El resultado del producto de " + n1 + " y " + n2 + " es " + multi);
                break;

            case 4: // División
                System.out.print("Ingresa n1: ");
                n1 = scanner.nextDouble();
                System.out.print("Ingresa n2: ");
                n2 = scanner.nextDouble();
                if (n2 == 0)
                    System.out.println("No se puede dividir entre cero");
                else
                    System.out.println("El resultado de la división entre " + n1 + " y " + n2 + " es " + (n1 / n2));
                break;

            case 5: // Módulo
                System.out.print("Ingresa n1: ");
                a = scanner.nextInt();
                System.out.print("Ingresa n2: ");
                b = scanner.nextInt();
                if (b == 0) {
                    System.out.println("No se puede dividir entre cero");
                } else {
                    int r1 = a / b;
                    int r2 = a - (b * r1);
                    System.out.println("El resultado de la división entre " + a + " y " + b + " es " + r1 + " con resto " + r2);
                }
                break;

            case 6: // Potencia
                System.out.print("Ingresa la base: ");
                a = scanner.nextInt();
                System.out.print("Ingresa el exponente: ");
                b = scanner.nextInt();
                int resultado = 1;
                for (int i = 1; i <= b; i++)
                    resultado = a * resultado;
                if (b == 0)
                    resultado = 1;
                System.out.println("El resultado es " + resultado);
                break;

            case 7: // Raíz
                System.out.print("Ingresa un número: ");
                n1 = scanner.nextDouble();
                if (n1 < 0)
                    System.out.println("No existe raíz real de un número negativo");
                else
                    System.out.println("La raíz de " + n1 + " es " + Math.sqrt(n1));
                break;

            default:
                System.out.println("Opción no válida");
        }
    }
}
