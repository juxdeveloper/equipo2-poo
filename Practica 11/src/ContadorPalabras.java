import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Modelo orientado a objetos encargado de la lectura de archivos de texto
 * plano con extension {@code .txt} y del computo del numero total de palabras
 * contenidas en su texto.
 *
 * <p>Esta clase no cuenta con constructor implicito por defecto, ya que define
 * constructores explicitos tanto sin argumentos como con inicializacion a partir
 * de un descriptor {@code File}.</p>
 *
 * @author Equipo 2 - Programacion Orientada a Objetos
 * @version 1.0
 */
public class ContadorPalabras {

    /**
     * Descriptor del archivo seleccionado actualmente en el modelo.
     */
    private File archivo;

    /**
     * Contenido textual leido del archivo bajo codificacion UTF-8.
     */
    private String contenido;

    /**
     * Cantidad acumulada de palabras contabilizadas en el contenido actual.
     */
    private int totalPalabras;

    /**
     * Constructor por defecto de la clase. Inicializa el objeto con estado vacio:
     * sin archivo asociado, contenido vacio y total de palabras igual a cero.
     */
    public ContadorPalabras() {
        this.archivo = null;
        this.contenido = "";
        this.totalPalabras = 0;
    }

    /**
     * Constructor que recibe directamente un descriptor de archivo para su carga.
     * Invoca al metodo de carga para validar la extension y procesar el contenido.
     *
     * @param archivo descriptor del archivo {@code .txt} que se desea procesar.
     * @throws IOException si ocurre un fallo al leer el archivo del sistema de archivos.
     * @throws IllegalArgumentException si el archivo es nulo o su extension no es {@code .txt}.
     */
    public ContadorPalabras(File archivo) throws IOException, IllegalArgumentException {
        this();
        cargarArchivo(archivo);
    }

    /**
     * Valida la extension del archivo proporcionado, asegurando que termine
     * en {@code .txt} (sin distincion de mayusculas y minusculas), lee su
     * texto integro con codificacion UTF-8 y calcula el total de palabras.
     *
     * @param archivoSeleccionado archivo que se procesara y analizara.
     * @throws IOException si el archivo no existe o se presenta un fallo de entrada/salida.
     * @throws IllegalArgumentException si el archivo es nulo o carece de la extension {@code .txt}.
     */
    public void cargarArchivo(File archivoSeleccionado) throws IOException, IllegalArgumentException {
        if (archivoSeleccionado == null) {
            throw new IllegalArgumentException("El archivo seleccionado no puede ser nulo.");
        }
        String nombre = archivoSeleccionado.getName().toLowerCase();
        if (!nombre.endsWith(".txt")) {
            throw new IllegalArgumentException("El archivo debe contar con extension .txt: " + archivoSeleccionado.getName());
        }
        if (!archivoSeleccionado.exists()) {
            throw new FileNotFoundException("El archivo especificado no existe: " + archivoSeleccionado.getPath());
        }
        if (!archivoSeleccionado.isFile()) {
            throw new IOException("La ruta indicada no corresponde a un archivo regular: " + archivoSeleccionado.getPath());
        }

        String texto = Files.readString(archivoSeleccionado.toPath(), StandardCharsets.UTF_8);
        this.archivo = archivoSeleccionado;
        this.contenido = texto;
        this.totalPalabras = contarPalabras(texto);
    }

    /**
     * Computa la cantidad de palabras presentes en una cadena de texto.
     * Se consideran palabras a las secuencias de caracteres no espaciales
     * delimitadas por espacios en blanco, tabuladores o saltos de linea.
     *
     * @param texto cadena de texto a analizar.
     * @return numero entero que representa el total de palabras identificadas.
     */
    public int contarPalabras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0;
        }
        String[] tokens = texto.trim().split("\\s+");
        return tokens.length;
    }

    /**
     * Retorna el descriptor del archivo cargado actualmente.
     *
     * @return objeto {@code File} asociado al modelo, o {@code null} si no hay archivo.
     */
    public File getArchivo() {
        return this.archivo;
    }

    /**
     * Retorna el contenido de texto leido del archivo.
     *
     * @return cadena con el texto cargado, o cadena vacia por defecto.
     */
    public String getContenido() {
        return this.contenido;
    }

    /**
     * Retorna el numero total de palabras computadas en el archivo actual.
     *
     * @return total de palabras contabilizadas.
     */
    public int getTotalPalabras() {
        return this.totalPalabras;
    }
}
