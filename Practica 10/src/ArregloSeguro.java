/**
 * Representa una estructura de datos basada en un arreglo de enteros que ilustra
 * el manejo seguro de operaciones y la jerarquia de excepciones en Java.
 * Permite configurar el tamano del arreglo, almacenar valores numericos a partir
 * de cadenas de texto y demostrar el orden correcto de captura en bloques
 * {@code try-catch}, transitando de excepciones especificas a generales.
 * La clase cuenta con un constructor explicito parametrizado que reserva el espacio
 * en memoria del arreglo subyacente.
 *
 * @author Equipo 2
 * @version 1.0
 */
public class ArregloSeguro {

    /**
     * Arreglo unidimensional interno para almacenar los elementos enteros.
     */
    private int[] elementos;

    /**
     * Construye una nueva instancia de {@code ArregloSeguro} con la capacidad indicada.
     * Reserva el arreglo de enteros segun el tamano recibido.
     *
     * @param tamano capacidad inicial deseada para el arreglo.
     * @throws NegativeArraySizeException si {@code tamano} es un valor negativo.
     */
    public ArregloSeguro(int tamano) {
        this.elementos = new int[tamano];
    }

    /**
     * Convierte una cadena de texto a numero entero y lo almacena en la posicion
     * indicada dentro del arreglo de elementos.
     *
     * @param indice posicion valida dentro del arreglo donde se colocara el valor.
     * @param textoValor representacion textual del numero entero a almacenar.
     * @return el valor entero que fue almacenado exitosamente en el arreglo.
     * @throws NumberFormatException si {@code textoValor} no contiene un formato de entero valido.
     * @throws ArrayIndexOutOfBoundsException si {@code indice} es menor que cero o mayor o igual al tamano del arreglo.
     * @throws NullPointerException si la referencia interna del arreglo es nula.
     */
    public int almacenarEnIndice(int indice, String textoValor) {
        int valor = Integer.parseInt(textoValor);
        elementos[indice] = valor;
        return elementos[indice];
    }

    /**
     * Evalua una operacion de asignacion sobre el arreglo aplicando un bloque
     * {@code try-catch} con orden riguroso de captura desde la excepcion mas especifica
     * hasta la superclase mas general.
     *
     * @param tamano dimension para inicializar el arreglo; un valor de {@code -999} asigna una referencia nula.
     * @param indice posicion destino para la asignacion del elemento.
     * @param textoValor cadena textual que representa el valor numerico a parsear.
     * @return mensaje descriptivo con el resultado exitoso o la excepcion capturada.
     */
    public String probarOrdenCatch(int tamano, int indice, String textoValor) {
        try {
            if (tamano == -999) {
                this.elementos = null;
            } else {
                this.elementos = new int[tamano];
            }
            int guardado = almacenarEnIndice(indice, textoValor);
            return "Exito: valor " + guardado + " almacenado en indice " + indice;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Capturada ArrayIndexOutOfBoundsException (especifica de indice): indice " + indice + " fuera de rango";
        } catch (NegativeArraySizeException e) {
            return "Capturada NegativeArraySizeException (especifica de tamano): tamano negativo " + tamano;
        } catch (NumberFormatException e) {
            return "Capturada NumberFormatException (especifica de formato): texto invalido \"" + textoValor + "\"";
        } catch (NullPointerException e) {
            return "Capturada NullPointerException (especifica de referencia): arreglo nulo";
        } catch (IndexOutOfBoundsException e) {
            return "Capturada IndexOutOfBoundsException (intermedia de indice): " + e.getMessage();
        } catch (RuntimeException e) {
            return "Capturada RuntimeException (general de ejecucion): " + e.getMessage();
        } catch (Exception e) {
            return "Capturada Exception (superclase general): " + e.getMessage();
        }
    }

    /**
     * Obtiene la longitud actual del arreglo de enteros encapsulado.
     *
     * @return la cantidad de elementos disponibles en el arreglo, o {@code -1} si la referencia es nula.
     */
    public int obtenerTamano() {
        return elementos == null ? -1 : elementos.length;
    }
}
