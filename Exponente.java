import java.util.Scanner;
public class Exponente
{
    public static void main(String[]args) {

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