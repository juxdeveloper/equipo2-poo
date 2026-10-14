package mx.unam.fi.die.poo.g7.p7;

/**
 * Representa un trapezoide con dos bases horizontales paralelas.
 * Hereda directamente de la clase {@code Cuadrilatero} y proporciona métodos
 * para calcular su base mayor, base menor, altura y área geométrica.
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Trapezoide extends Cuadrilatero {

    /**
     * Construye un trapezoide con los cuatro vértices dados.
     *
     * @param p1 Vértice inferior izquierdo.
     * @param p2 Vértice inferior derecho.
     * @param p3 Vértice superior derecho.
     * @param p4 Vértice superior izquierdo.
     */
    public Trapezoide(Punto p1, Punto p2, Punto p3, Punto p4) {
        super(p1, p2, p3, p4);
    }

    /**
     * Calcula la longitud de la base mayor del trapezoide a partir de los puntos inferiores.
     *
     * @return La longitud de la base mayor horizontal.
     */
    public double getBaseMayor() {
        return Math.abs(getP2().getX() - getP1().getX());
    }

    /**
     * Calcula la longitud de la base menor del trapezoide a partir de los puntos superiores.
     *
     * @return La longitud de la base menor horizontal.
     */
    public double getBaseMenor() {
        return Math.abs(getP3().getX() - getP4().getX());
    }

    /**
     * Calcula la altura del trapezoide a partir de la distancia vertical entre bases.
     *
     * @return La altura vertical de la figura.
     */
    public double getAltura() {
        return Math.abs(getP4().getY() - getP1().getY());
    }

    /**
     * Calcula el área del trapezoide utilizando la fórmula general:
     * Area = ((BaseMayor + BaseMenor) * Altura) / 2.
     *
     * @return El área total calculada del trapezoide.
     */
    public double calcularArea() {
        return ((getBaseMayor() + getBaseMenor()) * getAltura()) / 2.0;
    }

    /**
     * Genera una descripción textual con los vértices, dimensiones y área del trapezoide.
     *
     * @return Cadena que representa el estado del trapezoide.
     */
    @Override
    public String toString() {
        return String.format("%s\nTrapezoide [Base mayor=%.2f, Base menor=%.2f, Altura=%.2f, Área=%.2f]",
                super.toString(), getBaseMayor(), getBaseMenor(), getAltura(), calcularArea());
    }
}
