package mx.unam.fi.die.poo.g7.p8;

/**
 * Representa un edificio cuya huella se estima a partir de su consumo anual
 * de electricidad.
 *
 * <p>El cálculo educativo multiplica el consumo en kilowatt-hora por un
 * factor de emisión expresado en kilogramos de dióxido de carbono equivalente
 * por kilowatt-hora. No incluye materiales, construcción, agua ni residuos.</p>
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Edificio implements HuellaCarbono {

    /** Nombre que identifica al edificio. */
    private String nombre;

    /** Consumo anual de electricidad en kilowatt-hora. */
    private double consumoAnualKwh;

    /** Factor de emisión eléctrica en kilogramos de CO2e por kilowatt-hora. */
    private double factorElectricidad;

    /**
     * Construye un edificio con sus datos de consumo eléctrico.
     *
     * @param nombre Nombre descriptivo del edificio.
     * @param consumoAnualKwh Consumo anual en kilowatt-hora.
     * @param factorElectricidad Factor de emisión en kilogramos de CO2e por kilowatt-hora.
     */
    public Edificio(String nombre, double consumoAnualKwh, double factorElectricidad) {
        this.nombre = nombre;
        this.consumoAnualKwh = consumoAnualKwh;
        this.factorElectricidad = factorElectricidad;
    }

    /**
     * Obtiene el nombre del edificio.
     *
     * @return Nombre descriptivo del edificio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el consumo anual de electricidad.
     *
     * @return Consumo en kilowatt-hora.
     */
    public double getConsumoAnualKwh() {
        return consumoAnualKwh;
    }

    /**
     * Obtiene el factor de emisión eléctrica usado por el objeto.
     *
     * @return Factor en kilogramos de CO2e por kilowatt-hora.
     */
    public double getFactorElectricidad() {
        return factorElectricidad;
    }

    /**
     * Calcula la huella eléctrica anual del edificio.
     *
     * @return Consumo anual multiplicado por el factor de electricidad.
     */
    @Override
    public double getHuellaCarbono() {
        return consumoAnualKwh * factorElectricidad;
    }

    /**
     * Genera una descripción legible del edificio y su cálculo.
     *
     * @return Texto descriptivo del edificio.
     */
    @Override
    public String toString() {
        return String.format(
                "Edificio: %s | Consumo anual: %.2f kWh | Huella: %.2f kg CO2e",
                nombre, consumoAnualKwh, getHuellaCarbono());
    }
}
