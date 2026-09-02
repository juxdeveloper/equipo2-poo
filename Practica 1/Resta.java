import java.util.Scanner;
public class Resta {
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
