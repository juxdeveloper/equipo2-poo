package mx.unam.fi.die.poo.g7.p8;

/**
 * Representa una bicicleta para estimar emisiones operativas directas.
 *
 * <p>El modelo educativo asigna un factor operativo de cero porque la
 * bicicleta no quema combustible durante el recorrido. No se incluyen
 * fabricación, mantenimiento, alimentación ni transporte de la bicicleta.</p>
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Bicicleta implements HuellaCarbono {

    /** Tipo que identifica a la bicicleta. */
    private String tipo;

    /** Distancia recorrida en kilómetros. */
    private double kilometrosRecorridos;

    /** Factor de emisiones operativas directas en kilogramos de CO2e por kilómetro. */
    private double factorEmisionOperativa;

    /**
     * Construye una bicicleta con un recorrido determinado.
     *
     * @param tipo Tipo descriptivo de la bicicleta.
     * @param kilometrosRecorridos Distancia recorrida en kilómetros.
     */
    public Bicicleta(String tipo, double kilometrosRecorridos) {
        this.tipo = tipo;
        this.kilometrosRecorridos = kilometrosRecorridos;
        this.factorEmisionOperativa = 0.0;
    }

    /**
     * Obtiene el tipo de bicicleta.
     *
     * @return Tipo descriptivo de la bicicleta.
     */
    public String getTipo() {
        return tipo;
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
     * Obtiene el factor operativo directo de la bicicleta.
     *
     * @return Factor en kilogramos de CO2e por kilómetro.
     */
    public double getFactorEmisionOperativa() {
        return factorEmisionOperativa;
    }

    /**
     * Calcula las emisiones operativas directas del recorrido.
     *
     * @return Distancia multiplicada por el factor operativo directo.
     */
    @Override
    public double getHuellaCarbono() {
        return kilometrosRecorridos * factorEmisionOperativa;
    }

    /**
     * Genera una descripción legible de la bicicleta y su cálculo.
     *
     * @return Texto descriptivo de la bicicleta.
     */
    @Override
    public String toString() {
        return String.format(
                "Bicicleta: %s | Distancia: %.2f km | Huella operativa: %.2f kg CO2e",
                tipo, kilometrosRecorridos, getHuellaCarbono());
    }
}
