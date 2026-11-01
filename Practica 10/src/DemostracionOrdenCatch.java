import java.util.Scanner;

/**
 * Clase principal que ejecuta la demostracion interactiva del orden de evaluacion
 * de bloques {@code catch} en Java al trabajar con arreglos.
 * Incluye la lectura de parametros desde la consola estandar y una bateria de casos
 * predefinidos para validar la captura de excepciones especificas y generales.
 * Dispone de un constructor parametrizado que inicializa el objeto evaluador interno.
 *
 * @author Equipo 2
 * @version 1.0
 */
public class DemostracionOrdenCatch {

    /**
     * Instancia auxiliar de {@code ArregloSeguro} encargada de realizar las pruebas
     * y el despacho ordenado de excepciones.
     */
    private ArregloSeguro evaluador;

    /**
     * Construye una nueva instancia de la clase de demostracion indicando la capacidad
     * inicial para el evaluador encapsulado.
     *
     * @param tamanoInicial tamano inicial del arreglo utilizado por el evaluador.
     * @throws NegativeArraySizeException si {@code tamanoInicial} es un numero negativo.
     */
    public DemostracionOrdenCatch(int tamanoInicial) {
        this.evaluador = new ArregloSeguro(tamanoInicial);
    }

    /**
     * Delega en el evaluador interno la ejecucion de una operacion sobre arreglo
     * con manejo estructurado de excepciones.
     *
     * @param tamano tamano asignado al arreglo para la prueba.
     * @param indice posicion del arreglo donde se intentara guardar el valor.
     * @param textoValor cadena de texto que contiene el valor a parsear.
     * @return mensaje con el resultado de la evaluacion o la excepcion capturada.
     */
    public String ejecutarCaso(int tamano, int indice, String textoValor) {
        return evaluador.probarOrdenCatch(tamano, indice, textoValor);
    }

    /**
     * Punto de entrada principal del programa. Solicita al usuario los datos del caso
     * mediante la consola estandar, muestra el resultado obtenido y a continuacion
     * ejecuta cuatro casos predeterminados para ilustrar las excepciones mas comunes
     * en arreglos segun su nivel de especificidad.
     *
     * @param args argumentos de la linea de comandos suministrados al programa.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tamano del arreglo: ");
        int tamano = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Indice a escribir: ");
        int indice = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Valor entero en texto: ");
        String textoValor = scanner.nextLine().trim();

        DemostracionOrdenCatch demo = new DemostracionOrdenCatch(3);
        System.out.println("=== Demostracion del orden de bloques catch con un arreglo ===");
        System.out.println("Caso ingresado -> " + demo.ejecutarCaso(tamano, indice, textoValor));
        System.out.println("Caso 1 (Indice fuera de rango) -> " + demo.ejecutarCaso(3, 5, "10"));
        System.out.println("Caso 2 (Tamano negativo) -> " + demo.ejecutarCaso(-2, 0, "10"));
        System.out.println("Caso 3 (Formato invalido) -> " + demo.ejecutarCaso(3, 1, "abc"));
        System.out.println("Caso 4 (Arreglo nulo) -> " + demo.ejecutarCaso(-999, 0, "10"));
        scanner.close();
    }
}
