import java.util.Scanner;

public class Calcu {
    public static void main (String [] args){

        Scanner scanner = new Scanner (System.in);

        System.out.println ("Holaaa, en este programa se realizará la raíz de un número.");

        System.out.println ("Ingresa el radical.");
        int radical = scanner.nextInt();

        System.out.println ("Ingresa el radicando.");
        int radicando = scanner.nextInt();

        System.out.println ("Estos son los valores ingresados:");
        System.out.println ("radical = " + radical);
        System.out.println ("radicando = " + radicando);

        if (radical == 0 && radicando == 0) {
            System.out.println ("Error: No se puede realizar la operación ya que es una indeterminación.");
        }
        else if (radicando == 0){
            System.out.println ("El resultado es: 0.0");
        }
        else if (radical == 0){
            System.out.println ("Error: El radical no puede ser 0.");

        }
        else {
            double resultado = Math.pow (radicando, 1.0 / radical);
            System.out.println ("El resultado es: " + resultado);
        }       

    }
}