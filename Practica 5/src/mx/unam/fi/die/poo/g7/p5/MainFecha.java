package mx.unam.fi.die.poo.g7.p5;

import java.util.Scanner;

public class MainFecha {
    public static void main(String[] args) {
        // solicita los datos de la fecha desde el teclado
        Scanner entrada = new Scanner(System.in);
        System.out.print("Mes: ");
        int mes = entrada.nextInt();
        System.out.print("Dia: ");
        int dia = entrada.nextInt();
        System.out.print("Anio: ");
        int anio = entrada.nextInt();

        // crea y muestra la fecha ingresada
        Fecha fecha = new Fecha(mes, dia, anio);
        System.out.print("Fecha ingresada (mes/dia/anio): ");
        fecha.mostrarFecha();
        entrada.close();
    }
}
