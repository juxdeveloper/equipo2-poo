package mx.unam.fi.die.poo.g7.p7;

/**
 * Clase principal de ejecución para la demostración de la jerarquía de herencia de cuadriláteros.
 * Instancia objetos de cada nivel de la jerarquía y muestra sus coordenadas y áreas calculadas,
 * omitiendo el cálculo de área únicamente para la clase general {@code Cuadrilatero}.
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Main {

    /**
     * Constructor por defecto de la clase principal.
     */
    public Main() {
    }

    /**
     * Punto de entrada de la aplicación. Ejecuta los casos de prueba requeridos y el caso frontera.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("   PRÁCTICA 7: JERARQUÍA DE HERENCIA DE CUADRILÁTEROS");
        System.out.println("============================================================\n");

        // 1. Instancia de la superclase Cuadrilatero (sin cálculo de área)
        System.out.println("--- 1. Cuadrilátero general (Superclase) ---");
        Cuadrilatero cuadrilatero = new Cuadrilatero(
                new Punto(1.0, 1.0),
                new Punto(5.0, 1.0),
                new Punto(4.0, 4.0),
                new Punto(2.0, 3.0)
        );
        System.out.println(cuadrilatero);
        System.out.println("(Nota: La superclase Cuadrilatero no define cálculo de área)\n");

        // 2. Trapezoide
        System.out.println("--- 2. Trapezoide (Prueba oficial) ---");
        Trapezoide trapezoide = new Trapezoide(
                new Punto(0.0, 0.0),
                new Punto(8.0, 0.0),
                new Punto(6.0, 4.0),
                new Punto(2.0, 4.0)
        );
        System.out.println(trapezoide);
        System.out.printf("Área calculada: %.2f\n\n", trapezoide.calcularArea());

        // 3. Paralelogramo
        System.out.println("--- 3. Paralelogramo (Prueba oficial) ---");
        Paralelogramo paralelogramo = new Paralelogramo(
                new Punto(0.0, 0.0),
                new Punto(6.0, 0.0),
                new Punto(8.0, 4.0),
                new Punto(2.0, 4.0)
        );
        System.out.println(paralelogramo);
        System.out.printf("Área calculada: %.2f\n\n", paralelogramo.calcularArea());

        // 4. Rectángulo
        System.out.println("--- 4. Rectángulo (Prueba oficial) ---");
        Rectangulo rectangulo = new Rectangulo(
                new Punto(0.0, 0.0),
                new Punto(6.0, 0.0),
                new Punto(6.0, 4.0),
                new Punto(0.0, 4.0)
        );
        System.out.println(rectangulo);
        System.out.printf("Área calculada: %.2f\n\n", rectangulo.calcularArea());

        // 5. Cuadrado
        System.out.println("--- 5. Cuadrado (Prueba oficial) ---");
        Cuadrado cuadrado = new Cuadrado(
                new Punto(0.0, 0.0),
                new Punto(5.0, 0.0),
                new Punto(5.0, 5.0),
                new Punto(0.0, 5.0)
        );
        System.out.println(cuadrado);
        System.out.printf("Área calculada: %.2f\n\n", cuadrado.calcularArea());

        // 6. Caso frontera: Cuadrado unitario
        System.out.println("--- 6. Caso Frontera: Cuadrado Unitario ---");
        Cuadrado cuadradoUnitario = new Cuadrado(
                new Punto(0.0, 0.0),
                new Punto(1.0, 0.0),
                new Punto(1.0, 1.0),
                new Punto(0.0, 1.0)
        );
        System.out.println(cuadradoUnitario);
        System.out.printf("Área calculada: %.2f\n\n", cuadradoUnitario.calcularArea());

        System.out.println("============================================================");
        System.out.println("   EJECUCIÓN FINALIZADA SATISFACTORIAMENTE");
        System.out.println("============================================================");
    }
}
