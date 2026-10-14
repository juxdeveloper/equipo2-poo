package mx.unam.fi.die.poo.g7.p7;

/**
 * Superclase que modela un cuadrilátero genérico en el plano cartesiano.
 * Contiene como variables de instancia privadas los cuatro vértices representados mediante objetos de tipo {@code Punto}.
 * Esta clase no calcula área por ser una abstracción geométrica general de cuatro lados.
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Cuadrilatero {
    /** Primer vértice del cuadrilátero. */
    private Punto p1;

    /** Segundo vértice del cuadrilátero. */
    private Punto p2;

    /** Tercer vértice del cuadrilátero. */
    private Punto p3;

    /** Cuarto vértice del cuadrilátero. */
    private Punto p4;

    /**
     * Construye un cuadrilátero asignando sus cuatro vértices en orden coplanar.
     *
     * @param p1 Primer punto coordenado del cuadrilátero.
     * @param p2 Segundo punto coordenado del cuadrilátero.
     * @param p3 Tercer punto coordenado del cuadrilátero.
     * @param p4 Cuarto punto coordenado del cuadrilátero.
     */
    public Cuadrilatero(Punto p1, Punto p2, Punto p3, Punto p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    /**
     * Obtiene el primer punto del cuadrilátero.
     *
     * @return Objeto {@code Punto} correspondiente al vértice 1.
     */
    public Punto getP1() {
        return p1;
    }

    /**
     * Establece el primer punto del cuadrilátero.
     *
     * @param p1 Nuevo vértice 1.
     */
    public void setP1(Punto p1) {
        this.p1 = p1;
    }

    /**
     * Obtiene el segundo punto del cuadrilátero.
     *
     * @return Objeto {@code Punto} correspondiente al vértice 2.
     */
    public Punto getP2() {
        return p2;
    }

    /**
     * Establece el segundo punto del cuadrilátero.
     *
     * @param p2 Nuevo vértice 2.
     */
    public void setP2(Punto p2) {
        this.p2 = p2;
    }

    /**
     * Obtiene el tercer punto del cuadrilátero.
     *
     * @return Objeto {@code Punto} correspondiente al vértice 3.
     */
    public Punto getP3() {
        return p3;
    }

    /**
     * Establece el tercer punto del cuadrilátero.
     *
     * @param p3 Nuevo vértice 3.
     */
    public void setP3(Punto p3) {
        this.p3 = p3;
    }

    /**
     * Obtiene el cuarto punto del cuadrilátero.
     *
     * @return Objeto {@code Punto} correspondiente al vértice 4.
     */
    public Punto getP4() {
        return p4;
    }

    /**
     * Establece el cuarto punto del cuadrilátero.
     *
     * @param p4 Nuevo vértice 4.
     */
    public void setP4(Punto p4) {
        this.p4 = p4;
    }

    /**
     * Genera una representación textual legible de los vértices del cuadrilátero.
     *
     * @return Cadena que describe las coordenadas de los cuatro vértices.
     */
    @Override
    public String toString() {
        return String.format("Vértices: P1=%s, P2=%s, P3=%s, P4=%s", p1, p2, p3, p4);
    }
}
