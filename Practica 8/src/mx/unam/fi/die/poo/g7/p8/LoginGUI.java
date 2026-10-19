package mx.unam.fi.die.poo.g7.p8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Ventana gráfica sencilla para demostrar un inicio de sesión con usuario y
 * contraseña.
 *
 * <p>La validación es únicamente didáctica: acepta el usuario
 * {@code usuario} y la contraseña {@code poo8}. El objetivo es mostrar la
 * captura de datos, el uso de un campo de contraseña y la respuesta visual
 * ante un acceso correcto o incorrecto; no representa un sistema de
 * autenticación para producción.</p>
 *
 * @author juxdeveloper
 * @version 1.0
 */
public final class LoginGUI extends JFrame implements ActionListener {

    /** Identificador de serialización de la ventana. */
    private static final long serialVersionUID = 1L;

    /** Campo de texto para capturar el nombre de usuario. */
    private JTextField campoUsuario;

    /** Campo protegido para capturar la contraseña. */
    private JPasswordField campoContrasena;

    /** Botón que solicita la validación de los datos. */
    private JButton botonIngresar;

    /** Etiqueta donde se comunica el resultado del intento de acceso. */
    private JLabel mensaje;

    /**
     * Construye y configura la ventana de inicio de sesión.
     */
    public LoginGUI() {
        super("Inicio de sesión");
        configurarVentana();
        JPanel formulario = crearPanelFormulario();
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.add(formulario, BorderLayout.CENTER);
        setContentPane(contenido);
    }

    /**
     * Configura el tamaño, la operación de cierre y la posición de la ventana.
     */
    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(360, 220);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * Crea el formulario visual con sus etiquetas, campos y botón.
     *
     * @return Panel que contiene el formulario de acceso.
     */
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campoUsuario = new JTextField();
        campoContrasena = new JPasswordField();
        botonIngresar = new JButton("Ingresar");
        mensaje = new JLabel(" ");

        botonIngresar.addActionListener(this);

        panel.add(new JLabel("Usuario:"));
        panel.add(campoUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoContrasena);
        panel.add(new JLabel(""));
        panel.add(botonIngresar);
        panel.add(new JLabel("Estado:"));
        panel.add(mensaje);

        return panel;
    }

    /**
     * Atiende el clic del botón de ingreso.
     *
     * @param evento Evento producido por el botón de ingreso.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (validarAcceso()) {
            mensaje.setText("Acceso correcto");
        } else {
            mensaje.setText("Usuario o contraseña incorrectos");
        }
    }

    /**
     * Comprueba las credenciales didácticas del formulario.
     *
     * @return {@code true} si los datos corresponden al acceso de demostración.
     */
    private boolean validarAcceso() {
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());
        return usuario.equals("usuario") && contrasena.equals("poo8");
    }

    /**
     * Punto de entrada de la ventana gráfica.
     *
     * @param args Argumentos de la línea de comandos, no utilizados.
     */
    public static void main(String[] args) {
        LoginGUI ventana = new LoginGUI();
        ventana.setVisible(true);
    }
}
