import java.util.Scanner;

public class Calculadora{
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