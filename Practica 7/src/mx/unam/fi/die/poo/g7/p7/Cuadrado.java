package mx.unam.fi.die.poo.g7.p7;

/**
 * Representa un cuadrado en el plano cartesiano.
 * En la jerarquía de herencia profunda, extiende a la clase {@code Rectangulo},
 * pues un cuadrado es un caso particular de rectángulo donde base y altura son iguales (b = h).
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Cuadrado extends Rectangulo {

    /**
     * Construye un cuadrado con los cuatro vértices dados.
     *
     * @param p1 Vértice inferior izquierdo.
     * @param p2 Vértice inferior derecho.
     * @param p3 Vértice superior derecho.
     * @param p4 Vértice superior izquierdo.
     */
    public Cuadrado(Punto p1, Punto p2, Punto p3, Punto p4) {
        super(p1, p2, p3, p4);
    }

    /**
     * Obtiene la longitud de los lados del cuadrado.
     *
     * @return Valor numérico del lado.
     */
    public double getLado() {
        return getLongitud();
    }

    /**
     * Calcula el área del cuadrado aplicando la fórmula:
     * Area = Lado ^ 2.
     *
     * @return El área total calculada del cuadrado.
     */
    @Override
    public double calcularArea() {
        double lado = getLado();
        return lado * lado;
    }

    /**
     * Genera una descripción textual con la dimensión del lado y el área del cuadrado.
     *
     * @return Cadena descriptiva del cuadrado.
     */
    @Override
    public String toString() {
        return String.format("Vértices: P1=%s, P2=%s, P3=%s, P4=%s\nCuadrado [Lado=%.2f, Área=%.2f]",
                getP1(), getP2(), getP3(), getP4(), getLado(), calcularArea());
    }
}
