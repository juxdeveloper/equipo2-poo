package mx.unam.fi.die.poo.g7.p4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Analiza una oracion y cuenta frecuencias de palabras con colecciones.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class AnalizadorPalabras {
    /**
     * Atributo {@code oracion} de la clase {@code AnalizadorPalabras}.
     */
    public String oracion;
    /**
     * Atributo {@code frecuencias} de la clase {@code AnalizadorPalabras}.
     */
    public Map<String, Integer> frecuencias;

    /**
     * Constructor de la clase {@code AnalizadorPalabras}.
     * @param nuevaOracion valor inicial del parametro.
     */
    public AnalizadorPalabras(String nuevaOracion) {
        oracion = nuevaOracion;
        frecuencias = new HashMap<>();
    }

    /**
     * Metodo {@code contarPalabras} de la clase {@code AnalizadorPalabras}.
     */
    public void contarPalabras() {
        frecuencias.clear();
        String palabra = "";
        for (int i = 0; i <= oracion.length(); i++) {
            if (i < oracion.length() && Character.isLetterOrDigit(oracion.charAt(i))) {
                palabra += Character.toLowerCase(oracion.charAt(i));
            } else if (!palabra.isEmpty()) {
                if (frecuencias.containsKey(palabra)) {
                    frecuencias.put(palabra, frecuencias.get(palabra) + 1);
                } else {
                    frecuencias.put(palabra, 1);
                }
                palabra = "";
            }
        }
    }

    /**
     * Metodo {@code obtenerNumeroDuplicadas} de la clase {@code AnalizadorPalabras}.
     * @return valor producido por el metodo.
     */
    public int obtenerNumeroDuplicadas() {
        int duplicadas = 0;
        for (int frecuencia : frecuencias.values()) {
            if (frecuencia > 1) {
                duplicadas++;
            }
        }
        return duplicadas;
    }

    /**
     * Metodo {@code mostrarDuplicadas} de la clase {@code AnalizadorPalabras}.
     * @param ordenar argumento del metodo.
     */
    public void mostrarDuplicadas(boolean ordenar) {
        Set<String> palabras = frecuencias.keySet();
        if (ordenar) {
            palabras = new TreeSet<>(palabras);
        }
        for (String palabra : palabras) {
            if (frecuencias.get(palabra) > 1) {
                System.out.println(palabra + ": " + frecuencias.get(palabra));
            }
        }
    }
}
