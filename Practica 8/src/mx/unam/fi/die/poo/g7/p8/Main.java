package mx.unam.fi.die.poo.g7.p8;

import java.util.ArrayList;

/**
 * Clase principal que demuestra interfaces y polimorfismo con tres objetos
 * de huella de carbono.
 *
 * <p>La clase crea un edificio, un automóvil, una bicicleta y un caso frontera
 * de consumo eléctrico nulo. Todos se almacenan mediante referencias del tipo
 * {@code HuellaCarbono}; el recorrido de la lista invoca el mismo método y
 * cada objeto resuelve su cálculo con su propia implementación.</p>
 *
 * @author juxdeveloper
 * @version 1.0
 */
public class Main {

    /**
     * Constructor por defecto de la clase principal.
     */
    public Main() {
    }

    /**
     * Punto de entrada de la demostración de la práctica.
     *
     * @param args Argumentos de la línea de comandos, no utilizados.
     */
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("       PRÁCTICA 8: HUELLAS DE CARBONO - INTERFACES");
        System.out.println("============================================================");
        System.out.println("Modelo didáctico expresado en kg CO2e\n");

        Edificio edificio = new Edificio("Escuela de Ingeniería", 2400.0, 0.40);
        Auto auto = new Auto("Sedán compacto", 480.0, 7.0, 2.31);
        Bicicleta bicicleta = new Bicicleta("Urbana", 12.0);
        Edificio edificioFrontera = new Edificio("Edificio sin consumo", 0.0, 0.40);

        ArrayList<HuellaCarbono> huellas = new ArrayList<>();
        huellas.add(edificio);
        huellas.add(auto);
        huellas.add(bicicleta);
        huellas.add(edificioFrontera);

        System.out.println("--- Recorrido polimórfico ---");
        for (HuellaCarbono huella : huellas) {
            System.out.println(huella);
            System.out.printf("Huella calculada: %.2f kg CO2e%n%n", huella.getHuellaCarbono());
        }

        System.out.println("La lista se recorrió mediante la interfaz HuellaCarbono.");
        System.out.println("============================================================");
    }
}
