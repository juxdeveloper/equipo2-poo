import java.util.Scanner;
import java.lang.Math;

public class Ejercicio2_P2 {
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
