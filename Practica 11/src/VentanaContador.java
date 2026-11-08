import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Interfaz grafica de usuario principal basada en Swing para la lectura
 * de archivos con extension {@code .txt}, visualizacion de su contenido en un
 * area de texto desplazable y despliegue del total de palabras computadas.
 *
 * <p>Esta clase no posee constructor implicito por omision del compilador, ya que
 * define explicitamente su constructor por defecto para configurar los componentes
 * visuales de Swing y enlazar el modelo {@code ContadorPalabras}.</p>
 *
 * @author Equipo 2 - Programacion Orientada a Objetos
 * @version 1.0
 */
public class VentanaContador extends JFrame {

    /**
     * Instancia del modelo encargada del almacenamiento y procesamiento del archivo.
     */
    private ContadorPalabras contador;

    /**
     * Area de texto no editable utilizada para mostrar el contenido del archivo cargado.
     */
    private JTextArea areaTexto;

    /**
     * Etiqueta informativa que despliega en pantalla la cantidad de palabras calculadas.
     */
    private JLabel etiquetaTotal;

    /**
     * Boton que activa el cuadro de dialogo de seleccion de archivo {@code .txt}.
     */
    private JButton botonCargar;

    /**
     * Contenedor con barras de desplazamiento que envuelve el area de texto.
     */
    private JScrollPane panelDesplazamiento;

    /**
     * Constructor principal de la ventana. Establece las propiedades de la ventana,
     * instancia el modelo {@code ContadorPalabras} y construye la disposicion grafica.
     */
    public VentanaContador() {
        super("Contador de Palabras - Practica 11");
        this.contador = new ContadorPalabras();
        inicializarComponentes();
    }

    /**
     * Inicializa y organiza en el contenedor principal todos los componentes
     * visuales de la interfaz de usuario: boton de carga, area de texto y etiqueta.
     */
    public void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        this.botonCargar = new JButton("Cargar archivo .txt");
        this.botonCargar.setFont(new Font("SansSerif", Font.BOLD, 13));
        this.botonCargar.addActionListener(e -> seleccionarYCargarArchivo());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelSuperior.add(this.botonCargar);
        add(panelSuperior, BorderLayout.NORTH);

        this.areaTexto = new JTextArea(18, 50);
        this.areaTexto.setEditable(false);
        this.areaTexto.setLineWrap(true);
        this.areaTexto.setWrapStyleWord(true);
        this.areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 13));

        this.panelDesplazamiento = new JScrollPane(this.areaTexto);
        this.panelDesplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.panelDesplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelCentral.add(this.panelDesplazamiento, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        this.etiquetaTotal = new JLabel("Total de palabras: 0");
        this.etiquetaTotal.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        panelInferior.add(this.etiquetaTotal);
        add(panelInferior, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Despliega un cuadro de dialogo interactivo {@code JFileChooser} filtrado
     * para archivos con extension {@code .txt} y procesa la seleccion del usuario.
     */
    public void seleccionarYCargarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione un archivo de texto (.txt)");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        selector.setFileFilter(filtro);
        selector.setAcceptAllFileFilterUsed(false);

        int seleccion = selector.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selector.getSelectedFile();
            procesarArchivo(archivoSeleccionado);
        }
    }

    /**
     * Envia el archivo seleccionado al modelo {@code ContadorPalabras} para su lectura
     * y actualiza los componentes de la interfaz grafica con el contenido y resultado.
     *
     * @param archivo archivo {@code .txt} que se desea procesar y desplegar.
     */
    public void procesarArchivo(File archivo) {
        if (archivo == null) {
            return;
        }
        try {
            this.contador.cargarArchivo(archivo);
            if (this.areaTexto != null) {
                this.areaTexto.setText(this.contador.getContenido());
                this.areaTexto.setCaretPosition(0);
            }
            if (this.etiquetaTotal != null) {
                this.etiquetaTotal.setText("Total de palabras: " + this.contador.getTotalPalabras());
            }
        } catch (IOException | IllegalArgumentException e) {
            if (!GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(this,
                        "Error al cargar el archivo:\n" + e.getMessage(),
                        "Error de Lectura",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }
        }
    }

    /**
     * Retorna el modelo de conteo de palabras asociado a la ventana.
     *
     * @return objeto {@code ContadorPalabras} en uso.
     */
    public ContadorPalabras getContador() {
        return this.contador;
    }

    /**
     * Retorna el area de texto empleada para visualizar el contenido.
     *
     * @return componente visual {@code JTextArea}.
     */
    public JTextArea getAreaTexto() {
        return this.areaTexto;
    }

    /**
     * Retorna la etiqueta grafica que muestra el total de palabras.
     *
     * @return componente visual {@code JLabel}.
     */
    public JLabel getEtiquetaTotal() {
        return this.etiquetaTotal;
    }

    /**
     * Retorna el boton que desencadena la seleccion del archivo.
     *
     * @return componente visual {@code JButton}.
     */
    public JButton getBotonCargar() {
        return this.botonCargar;
    }

    /**
     * Retorna el panel con barras de desplazamiento del area de texto.
     *
     * @return componente visual {@code JScrollPane}.
     */
    public JScrollPane getPanelDesplazamiento() {
        return this.panelDesplazamiento;
    }

    /**
     * Metodo principal de ejecucion. Proporciona soporte para ejecucion con paso de
     * argumentos desde la consola imprimiendo el resultado directamente a la salida
     * estandar, o bien la apertura interactiva de la ventana grafica en un entorno apto.
     *
     * @param args argumentos pasados desde la linea de comandos; de contener la ruta
     *             hacia un archivo {@code .txt}, este se procesa e imprime en terminal.
     */
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            File archivo = new File(args[0]);
            ContadorPalabras contadorDirecto = new ContadorPalabras();
            try {
                contadorDirecto.cargarArchivo(archivo);
                System.out.println("=== CONTADOR DE PALABRAS GUI ===");
                System.out.println("Archivo procesado: " + archivo.getName());
                System.out.println("Contenido:");
                System.out.println("----------------------------------------");
                String texto = contadorDirecto.getContenido();
                if (texto != null && !texto.isEmpty()) {
                    System.out.print(texto);
                    if (!texto.endsWith("\n")) {
                        System.out.println();
                    }
                }
                System.out.println("----------------------------------------");
                System.out.println("Total de palabras: " + contadorDirecto.getTotalPalabras());
            } catch (IOException | IllegalArgumentException e) {
                System.err.println("Error al procesar el archivo: " + e.getMessage());
            }

            if (!GraphicsEnvironment.isHeadless()) {
                SwingUtilities.invokeLater(() -> {
                    VentanaContador ventana = new VentanaContador();
                    ventana.procesarArchivo(archivo);
                    ventana.setVisible(true);
                });
            }
        } else {
            if (!GraphicsEnvironment.isHeadless()) {
                SwingUtilities.invokeLater(() -> {
                    VentanaContador ventana = new VentanaContador();
                    ventana.setVisible(true);
                });
            } else {
                System.out.println("=== CONTADOR DE PALABRAS GUI ===");
                System.out.println("Modo grafico no disponible en entorno sin pantalla (headless).");
                System.out.println("Uso: java -jar practica11.jar <ruta-archivo.txt>");
            }
        }
    }
}
