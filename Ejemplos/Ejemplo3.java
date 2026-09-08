import java.util.ArrayList;

public class Ejemplo3 {
    public static void main(String[] args) {
        ArrayList<Integer> estructura = new ArrayList<>();
        estructura.add(1);
        estructura.add(10);
        estructura.add(30);
        System.out.println("Tamaño de la estructura => "+estructura.size());
        System.out.println("Elemento 3 => "+estructura.get(2));
        for(Integer elemento : estructura){
            System.out.println(elemento);
        }
    }
}
