package mx.unam.fi.die.poo.g7.p7;

/**
 * Representa un paralelogramo, cuadrilátero con lados opuestos paralelos de igual longitud.
 * En la jerarquía profunda de herencia, extiende a la clase {@code Trapezoide}, ya que un
 * paralelogramo es un caso especial de trapezoide donde la base mayor y la base menor son iguales (B = b).
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Paralelogramo extends Trapezoide {

    /**
     * Construye un paralelogramo con los cuatro vértices dados.
     *
     * @param p1 Vértice inferior izquierdo.
     * @param p2 Vértice inferior derecho.
     * @param p3 Vértice superior derecho.
     * @param p4 Vértice superior izquierdo.
     */
    public Paralelogramo(Punto p1, Punto p2, Punto p3, Punto p4) {
        super(p1, p2, p3, p4);
    }

    /**
     * Obtiene la longitud de la base del paralelogramo.
     *
     * @return Longitud de la base.
     */
    public double getBase() {
        return getBaseMayor();
    }

    /**
     * Calcula el área del paralelogramo utilizando la fórmula simplificada:
     * Area = Base * Altura.
     *
     * @return El área total calculada del paralelogramo.
     */
    @Override
    public double calcularArea() {
        return getBase() * getAltura();
    }

    /**
     * Genera una descripción textual con las dimensiones y el área del paralelogramo.
     *
     * @return Cadena descriptiva del paralelogramo.
     */
    @Override
    public String toString() {
        return String.format("Vértices: P1=%s, P2=%s, P3=%s, P4=%s\nParalelogramo [Base=%.2f, Altura=%.2f, Área=%.2f]",
                getP1(), getP2(), getP3(), getP4(), getBase(), getAltura(), calcularArea());
    }
}
