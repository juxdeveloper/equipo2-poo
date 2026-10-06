package mx.unam.fi.die.poo.g7.p5;

/**
 * Entidad empleado con nombre, apellido y salario mensual encapsulados.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Empleado {
    /**
     * Atributo {@code nombre} de la clase {@code Empleado}.
     */
    private String nombre;
    /**
     * Atributo {@code apellido} de la clase {@code Empleado}.
     */
    private String apellido;
    /**
     * Atributo {@code salarioMensual} de la clase {@code Empleado}.
     */
    private double salarioMensual;

    /**
     * Constructor de la clase {@code Empleado}.
     * @param nombre valor inicial del parametro.
     * @param apellido valor inicial del parametro.
     * @param salarioMensual valor inicial del parametro.
     */
    public Empleado(String nombre, String apellido, double salarioMensual) {
        this.nombre = nombre;
        this.apellido = apellido;
        setSalarioMensual(salarioMensual);
    }

    /**
     * Metodo {@code getNombre} de la clase {@code Empleado}.
     * @return valor producido por el metodo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo {@code setNombre} de la clase {@code Empleado}.
     * @param nombre argumento del metodo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo {@code getApellido} de la clase {@code Empleado}.
     * @return valor producido por el metodo.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo {@code setApellido} de la clase {@code Empleado}.
     * @param apellido argumento del metodo.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Metodo {@code getSalarioMensual} de la clase {@code Empleado}.
     * @return valor producido por el metodo.
     */
    public double getSalarioMensual() {
        return salarioMensual;
    }

    /**
     * Metodo {@code setSalarioMensual} de la clase {@code Empleado}.
     * @param salarioMensual argumento del metodo.
     */
    public void setSalarioMensual(double salarioMensual) {
        if (salarioMensual > 0) {
            this.salarioMensual = salarioMensual;
        }
    }
}
