package mx.unam.fi.die.poo.g7.p7;

/**
 * Representa un punto en el plano cartesiano bidimensional mediante coordenadas (x, y).
 * Se utiliza para modelar los vértices de las figuras geométricas.
 * El constructor por defecto inicializa las coordenadas en el origen (0.0, 0.0).
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Punto {
    /** Coordenada horizontal en el plano cartesiano. */
    private double x;

    /** Coordenada vertical en el plano cartesiano. */
    private double y;

    /**
     * Constructor por defecto que inicializa el punto en el origen (0.0, 0.0).
     */
    public Punto() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Constructor que inicializa el punto con coordenadas específicas.
     *
     * @param x Coordenada en el eje horizontal.
     * @param y Coordenada en el eje vertical.
     */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene la coordenada horizontal del punto.
     *
     * @return El valor actual de la coordenada x.
     */
    public double getX() {
        return x;
    }

    /**
     * Establece la coordenada horizontal del punto.
     *
     * @param x El nuevo valor para la coordenada x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Obtiene la coordenada vertical del punto.
     *
     * @return El valor actual de la coordenada y.
     */
    public double getY() {
        return y;
    }

    /**
     * Establece la coordenada vertical del punto.
     *
     * @param y El nuevo valor para la coordenada y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Genera una representación textual legible de las coordenadas del punto.
     *
     * @return Cadena con el formato (x, y).
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
