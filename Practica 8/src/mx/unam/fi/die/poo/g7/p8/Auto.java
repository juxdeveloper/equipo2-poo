package mx.unam.fi.die.poo.g7.p8;

/**
 * Representa un automóvil cuya huella se estima mediante el combustible
 * consumido durante un recorrido.
 *
 * <p>El cálculo multiplica la distancia recorrida por el rendimiento del
 * automóvil y por un factor de emisión de gasolina. El modelo solo considera
 * la combustión del combustible utilizado en el recorrido.</p>
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Auto implements HuellaCarbono {

    /** Modelo que identifica al automóvil. */
    private String modelo;

    /** Distancia recorrida en kilómetros. */
    private double kilometrosRecorridos;

    /** Consumo del automóvil en litros por cada cien kilómetros. */
    private double litrosCada100Kilometros;

    /** Factor de emisión de gasolina en kilogramos de CO2e por litro. */
    private double factorGasolina;

    /**
     * Construye un automóvil con los datos necesarios para estimar su huella.
     *
     * @param modelo Modelo descriptivo del automóvil.
     * @param kilometrosRecorridos Distancia recorrida en kilómetros.
     * @param litrosCada100Kilometros Consumo de combustible por cada cien kilómetros.
     * @param factorGasolina Factor de emisión en kilogramos de CO2e por litro.
     */
    public Auto(String modelo, double kilometrosRecorridos,
                double litrosCada100Kilometros, double factorGasolina) {
        this.modelo = modelo;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.litrosCada100Kilometros = litrosCada100Kilometros;
        this.factorGasolina = factorGasolina;
    }

    /**
     * Obtiene el modelo del automóvil.
     *
     * @return Modelo descriptivo del automóvil.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Obtiene la distancia recorrida.
     *
     * @return Distancia en kilómetros.
     */
    public double getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    /**
     * Obtiene el consumo del automóvil.
     *
     * @return Consumo en litros por cada cien kilómetros.
     */
    public double getLitrosCada100Kilometros() {
        return litrosCada100Kilometros;
    }

    /**
     * Calcula los litros de gasolina utilizados en el recorrido.
     *
     * @return Litros consumidos durante la distancia indicada.
     */
    public double getLitrosConsumidos() {
        return kilometrosRecorridos * litrosCada100Kilometros / 100.0;
    }

    /**
     * Obtiene el factor de emisión de gasolina usado por el objeto.
     *
     * @return Factor en kilogramos de CO2e por litro.
     */
    public double getFactorGasolina() {
        return factorGasolina;
    }

    /**
     * Calcula la huella del combustible consumido por el automóvil.
     *
     * @return Litros consumidos multiplicados por el factor de gasolina.
     */
    @Override
    public double getHuellaCarbono() {
        return getLitrosConsumidos() * factorGasolina;
    }

    /**
     * Genera una descripción legible del automóvil y su cálculo.
     *
     * @return Texto descriptivo del automóvil.
     */
    @Override
    public String toString() {
        return String.format(
                "Auto: %s | Distancia: %.2f km | Combustible: %.2f L | Huella: %.2f kg CO2e",
                modelo, kilometrosRecorridos, getLitrosConsumidos(), getHuellaCarbono());
    }
}
