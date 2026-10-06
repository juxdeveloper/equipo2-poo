package mx.unam.fi.die.poo.g7.p2;


/**
 * Analiza condiciones con estructuras iterativas y selectivas.
 *
 * <p>Clase perteneciente al recopilatorio de practicas y proyecto.</p>
 *
 * @author Equipo 2
 */
public class Ejercicio3_P2 {
    /**
     * Constructor por defecto de la clase {@code Ejercicio3_P2}.
     *
     * <p>Constructor implicito documentado en la clase.</p>
     */
    public Ejercicio3_P2() {
    }


    /**
     * Metodo {@code main} de la clase {@code Ejercicio3_P2}.
     * @param args[] argumento del metodo.
     */
    public static void main(String args[]) {
        System.out.println("n \t 10*n \t 100*n \t 1000*n");
        for(int i = 0; i < 5 ; i++){
            System.out.println( (i+1) + " \t  " + 10*(i+1) + " \t  " + 100*(i+1) + " \t  " + 1000*(i+1));
        }
    }
}
