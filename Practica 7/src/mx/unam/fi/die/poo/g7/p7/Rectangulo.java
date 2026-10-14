package mx.unam.fi.die.poo.g7.p7;

/**
 * Representa un rectángulo en el plano cartesiano.
 * En la jerarquía de herencia profunda, extiende a la clase {@code Paralelogramo},
 * pues todo rectángulo es un paralelogramo cuyos cuatro ángulos internos son rectos (90 grados).
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Rectangulo extends Paralelogramo {

    /**
     * Construye un rectángulo con los cuatro vértices dados.
     *
     * @param p1 Vértice inferior izquierdo.
     * @param p2 Vértice inferior derecho.
     * @param p3 Vértice superior derecho.
     * @param p4 Vértice superior izquierdo.
     */
    public Rectangulo(Punto p1, Punto p2, Punto p3, Punto p4) {
        super(p1, p2, p3, p4);
    }

    /**
     * Obtiene la longitud de la base horizontal del rectángulo.
     *
     * @return Valor numérico de la longitud base.
     */
    public double getLongitud() {
        return getBase();
    }

    /**
     * Obtiene el ancho o altura vertical del rectángulo.
     *
     * @return Valor numérico del ancho vertical.
     */
    public double getAncho() {
        return getAltura();
    }

    /**
     * Calcula el área del rectángulo mediante el producto de longitud por ancho.
     *
     * @return El área total calculada del rectángulo.
     */
    @Override
    public double calcularArea() {
        return getLongitud() * getAncho();
    }

    /**
     * Genera una descripción textual con las dimensiones y el área del rectángulo.
     *
     * @return Cadena descriptiva del rectángulo.
     */
    @Override
    public String toString() {
        return String.format("Vértices: P1=%s, P2=%s, P3=%s, P4=%s\nRectángulo [Longitud=%.2f, Ancho=%.2f, Área=%.2f]",
                getP1(), getP2(), getP3(), getP4(), getLongitud(), getAncho(), calcularArea());
    }
}
