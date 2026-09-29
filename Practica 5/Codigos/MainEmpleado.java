import java.util.Scanner;

public class MainEmpleado {
    public static void main(String[] args) {
        // permite ingresar los datos desde el teclado
        Scanner entrada = new Scanner(System.in);

        // solicita los datos del primer empleado
        System.out.println("Datos del primer empleado:");
        System.out.print("Nombre: ");
        String nombre1 = entrada.nextLine();
        System.out.print("Apellido: ");
        String apellido1 = entrada.nextLine();
        System.out.print("Salario mensual (usa punto para los decimales): ");
        double salario1 = Double.parseDouble(entrada.nextLine());

        // solicita los datos del segundo empleado
        System.out.println("\nDatos del segundo empleado:");
        System.out.print("Nombre: ");
        String nombre2 = entrada.nextLine();
        System.out.print("Apellido: ");
        String apellido2 = entrada.nextLine();
        System.out.print("Salario mensual (usa punto para los decimales): ");
        double salario2 = Double.parseDouble(entrada.nextLine());

        // crea los empleados con los datos ingresados
        Empleado empleado1 = new Empleado(nombre1, apellido1, salario1);
        Empleado empleado2 = new Empleado(nombre2, apellido2, salario2);

        double salarioAnual1 = empleado1.getSalarioMensual() * 12;
        double salarioAnual2 = empleado2.getSalarioMensual() * 12;

        System.out.println("Salario anual de " + empleado1.getNombre() + ": " + salarioAnual1);
        System.out.println("Salario anual de " + empleado2.getNombre() + ": " + salarioAnual2);

        empleado1.setSalarioMensual(empleado1.getSalarioMensual() * 1.10);
        empleado2.setSalarioMensual(empleado2.getSalarioMensual() * 1.10);

        salarioAnual1 = empleado1.getSalarioMensual() * 12;
        salarioAnual2 = empleado2.getSalarioMensual() * 12;

        System.out.println("Salario anual con aumento de " + empleado1.getNombre() + ": " + salarioAnual1);
        System.out.println("Salario anual con aumento de " + empleado2.getNombre() + ": " + salarioAnual2);
        entrada.close();
    }
}
