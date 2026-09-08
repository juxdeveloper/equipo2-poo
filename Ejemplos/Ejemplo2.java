package Ejemplos;
public class Ejemplo2 {
    public static void main(String[] args) {
        /*System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);*/
        int[] arreglo = {7,6,3,91,5,7};
        int total = 0;

        for (int i = 0; i < arreglo.length; i++){
            total += arreglo[i];
        }

        System.out.println("La suma del arreglo es => "+total);
    }
}