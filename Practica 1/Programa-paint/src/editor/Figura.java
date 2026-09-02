package editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/** Una figura inmutable colocada sobre el lienzo. */
public record Figura(
        Tipo tipo,
        int xInicial,
        int yInicial,
        int xFinal,
        int yFinal,
        Color color,
        boolean rellena
) {
    public enum Tipo {
        RECTANGULO("Rectángulo"),
        OVALO("Óvalo"),
        TRIANGULO("Triángulo"),
        LINEA("Línea");

        private final String nombre;

        Tipo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    public void dibujar(Graphics2D g) {
        g.setColor(color);

        int x = Math.min(xInicial, xFinal);
        int y = Math.min(yInicial, yFinal);
        int ancho = Math.abs(xFinal - xInicial);
        int alto = Math.abs(yFinal - yInicial);

        switch (tipo) {
            case RECTANGULO -> {
                if (rellena) g.fillRect(x, y, ancho, alto);
                else g.drawRect(x, y, ancho, alto);
            }
            case OVALO -> {
                if (rellena) g.fillOval(x, y, ancho, alto);
                else g.drawOval(x, y, ancho, alto);
            }
            case TRIANGULO -> {
                Polygon triangulo = new Polygon(
                        new int[]{x + ancho / 2, x, x + ancho},
                        new int[]{y, y + alto, y + alto},
                        3
                );
                if (rellena) g.fillPolygon(triangulo);
                else g.drawPolygon(triangulo);
            }
            case LINEA -> g.drawLine(xInicial, yInicial, xFinal, yFinal);
        }
    }
}
