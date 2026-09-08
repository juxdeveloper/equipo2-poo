import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Ejemplo5 {

    public static void main(String[] args) {
        Map<String, Integer> miMapa = new HashMap<>();

        crearMapa(miMapa);
        mostrarMapa(miMapa);
    }
    //Mapa para entrada del usuario
    public static void crearMapa(Map<String, Integer> mapa){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa la cadena => ");
        String input = scanner.nextLine();

        //Separamos la entrada
        String[] tokens = input.split(" ");

        //procesamos el texto de entrada
        for(String token : tokens){
            String palabra = token.toLowerCase();

            //Si la palabra ya esta en el mapa
            if (mapa.containsKey(palabra)) {
                int contador = mapa.get(palabra);
                mapa.put(palabra, contador++);
            }else{
                mapa.put(palabra,1);
            }
        }
        scanner.close();
    }

    //mostramos el contenido del mapa
    public static void mostrarMapa(Map<String, Integer> mapa){
        Set<String> llaves = mapa.keySet();

        //Ordenamos
        TreeSet<String> llavesOrd = new TreeSet<>(llaves);

        for(String llave : llaves){
            System.out.println(llave + " " + mapa.get(llave));
            System.out.println("Tamaño => " + mapa.size() + " vacio? " + mapa.isEmpty());
        }
    }
}
