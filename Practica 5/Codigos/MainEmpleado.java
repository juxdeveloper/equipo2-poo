public class MainEmpleado {
    public static void main(String[] args) {
        Empleado empleado1 = new Empleado("Juan", "Perez", 10000);
        Empleado empleado2 = new Empleado("Ana", "Lopez", 12000);

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
    }
}
