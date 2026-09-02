package editor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public final class EditorFrame extends JFrame {
    private final CanvasPanel canvas = new CanvasPanel();
    private final JButton botonColor = new JButton("Color");
    private Color colorActual = new Color(37, 99, 235);

    public EditorFrame() {
        super("Editor de figuras");
        configurarVentana();
        add(crearBarraHerramientas(), BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 700);
        setMinimumSize(new java.awt.Dimension(700, 500));
        setLocationRelativeTo(null);
    }

    private JPanel crearBarraHerramientas() {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        barra.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));

        JComboBox<Figura.Tipo> selector = new JComboBox<>(Figura.Tipo.values());
        JCheckBox relleno = new JCheckBox("Relleno", true);
        JButton deshacer = new JButton("Deshacer");
        JButton borrar = new JButton("Borrar todo");
        JButton exportar = new JButton("Exportar PNG");

        actualizarBotonColor();
        selector.addActionListener(e -> canvas.setTipoActual((Figura.Tipo) selector.getSelectedItem()));
        relleno.addActionListener(e -> canvas.setRellena(relleno.isSelected()));
        botonColor.addActionListener(e -> elegirColor());
        deshacer.addActionListener(e -> canvas.deshacer());
        borrar.addActionListener(e -> canvas.borrarTodo());
        exportar.addActionListener(e -> exportarImagen());

        barra.add(new JLabel("Figura:"));
        barra.add(selector);
        barra.add(relleno);
        barra.add(botonColor);
        barra.add(deshacer);
        barra.add(borrar);
        barra.add(exportar);
        return barra;
    }

    private void elegirColor() {
        Color elegido = JColorChooser.showDialog(this, "Seleccionar color", colorActual);
        if (elegido != null) {
            colorActual = elegido;
            canvas.setColorActual(elegido);
            actualizarBotonColor();
        }
    }

    private void actualizarBotonColor() {
        botonColor.setBackground(colorActual);
        botonColor.setForeground(colorActual.getRed() + colorActual.getGreen() + colorActual.getBlue() < 380
                ? Color.WHITE : Color.BLACK);
        botonColor.setOpaque(true);
    }

    private void exportarImagen() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Exportar lienzo como PNG");
        selector.setFileFilter(new FileNameExtensionFilter("Imagen PNG (*.png)", "png"));
        selector.setSelectedFile(new File("dibujo.png"));

        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File destino = selector.getSelectedFile();
        if (!destino.getName().toLowerCase().endsWith(".png")) {
            destino = new File(destino.getParentFile(), destino.getName() + ".png");
        }

        if (destino.exists()) {
            int respuesta = JOptionPane.showConfirmDialog(
                    this, "El archivo ya existe. ¿Quieres reemplazarlo?",
                    "Confirmar reemplazo", JOptionPane.YES_NO_OPTION
            );
            if (respuesta != JOptionPane.YES_OPTION) return;
        }

        try {
            canvas.exportarPng(destino);
            JOptionPane.showMessageDialog(this, "Imagen guardada en:\n" + destino.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this, "No se pudo exportar la imagen:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Swing utilizará su apariencia predeterminada.
            }
            new EditorFrame().setVisible(true);
        });
    }
}
