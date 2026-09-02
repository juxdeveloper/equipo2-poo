package editor;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@SuppressWarnings("serial")
public final class CanvasPanel extends JPanel {
    private final List<Figura> figuras = new ArrayList<>();
    private final Deque<List<Figura>> historial = new ArrayDeque<>();

    private Figura.Tipo tipoActual = Figura.Tipo.RECTANGULO;
    private Color colorActual = new Color(37, 99, 235);
    private boolean rellena = true;
    private Figura vistaPrevia;

    public CanvasPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(900, 580));

        MouseAdapter mouse = new MouseAdapter() {
            private int inicioX;
            private int inicioY;

            @Override
            public void mousePressed(MouseEvent e) {
                if (!javax.swing.SwingUtilities.isLeftMouseButton(e)) return;
                inicioX = e.getX();
                inicioY = e.getY();
                vistaPrevia = crearFigura(inicioX, inicioY, inicioX, inicioY);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (vistaPrevia == null) return;
                vistaPrevia = crearFigura(inicioX, inicioY, e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (vistaPrevia == null) return;

                Figura nueva = crearFigura(inicioX, inicioY, e.getX(), e.getY());
                vistaPrevia = null;

                if (esVisible(nueva)) {
                    guardarEstado();
                    figuras.add(nueva);
                }
                repaint();
            }
        };

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private Figura crearFigura(int x1, int y1, int x2, int y2) {
        return new Figura(tipoActual, x1, y1, x2, y2, colorActual, rellena);
    }

    private boolean esVisible(Figura figura) {
        int ancho = Math.abs(figura.xFinal() - figura.xInicial());
        int alto = Math.abs(figura.yFinal() - figura.yInicial());
        return figura.tipo() == Figura.Tipo.LINEA
                ? ancho > 1 || alto > 1
                : ancho > 1 && alto > 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = prepararGraficos(g);
        dibujarFiguras(g2);

        if (vistaPrevia != null) {
            vistaPrevia.dibujar(g2);
        }
        g2.dispose();
    }

    private Graphics2D prepararGraficos(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
    }

    private void dibujarFiguras(Graphics2D g2) {
        for (Figura figura : figuras) {
            figura.dibujar(g2);
        }
    }

    private void guardarEstado() {
        historial.push(new ArrayList<>(figuras));
    }

    public boolean deshacer() {
        if (historial.isEmpty()) return false;
        figuras.clear();
        figuras.addAll(historial.pop());
        repaint();
        return true;
    }

    public boolean borrarTodo() {
        if (figuras.isEmpty()) return false;
        guardarEstado();
        figuras.clear();
        vistaPrevia = null;
        repaint();
        return true;
    }

    public void exportarPng(File archivo) throws IOException {
        BufferedImage imagen = new BufferedImage(
                getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2 = imagen.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, imagen.getWidth(), imagen.getHeight());
        g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarFiguras(g2);
        g2.dispose();

        if (!ImageIO.write(imagen, "png", archivo)) {
            throw new IOException("No se encontró un codificador para PNG.");
        }
    }

    public void setTipoActual(Figura.Tipo tipoActual) {
        this.tipoActual = tipoActual;
    }

    public void setColorActual(Color colorActual) {
        this.colorActual = colorActual;
    }

    public void setRellena(boolean rellena) {
        this.rellena = rellena;
    }
}
