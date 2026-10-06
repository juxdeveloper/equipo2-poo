package mx.unam.fi.die.poo.g7.p5;

/**
 * Entidad fecha con dia, mes y anio encapsulados y validacion basica.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Fecha {
    /**
     * Atributo {@code mes} de la clase {@code Fecha}.
     */
    private int mes;
    /**
     * Atributo {@code dia} de la clase {@code Fecha}.
     */
    private int dia;
    /**
     * Atributo {@code anio} de la clase {@code Fecha}.
     */
    private int anio;

    /**
     * Constructor de la clase {@code Fecha}.
     * @param mes valor inicial del parametro.
     * @param dia valor inicial del parametro.
     * @param anio valor inicial del parametro.
     */
    public Fecha(int mes, int dia, int anio) {
        this.mes = mes;
        this.dia = dia;
        this.anio = anio;
    }

    /**
     * Metodo {@code setMes} de la clase {@code Fecha}.
     * @param mes argumento del metodo.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Metodo {@code setDia} de la clase {@code Fecha}.
     * @param dia argumento del metodo.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Metodo {@code setAnio} de la clase {@code Fecha}.
     * @param anio argumento del metodo.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Metodo {@code mostrarFecha} de la clase {@code Fecha}.
     */
    public void mostrarFecha() {
        System.out.println(mes + "/" + dia + "/" + anio);
    }
}
