package mx.unam.fi.die.poo.g7.p8;

/**
 * Define el comportamiento común para obtener una huella de carbono.
 *
 * <p>Las clases que implementan esta interfaz expresan su resultado en
 * kilogramos de dióxido de carbono equivalente ({@code kg CO2e}).</p>
 *
 * @author juxdeveloper
 * @version 1.0
 */
public interface HuellaCarbono {

    /**
     * Calcula la huella de carbono del objeto según sus propios datos.
     *
     * @return Huella calculada en {@code kg CO2e}.
     */
    double getHuellaCarbono();
}
