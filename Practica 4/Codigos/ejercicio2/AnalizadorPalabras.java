import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AnalizadorPalabras {
    public String oracion;
    public Map<String, Integer> frecuencias;

    public AnalizadorPalabras(String nuevaOracion) {
        oracion = nuevaOracion;
        frecuencias = new HashMap<>();
    }

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

    public int obtenerNumeroDuplicadas() {
        int duplicadas = 0;
        for (int frecuencia : frecuencias.values()) {
            if (frecuencia > 1) {
                duplicadas++;
            }
        }
        return duplicadas;
    }

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
