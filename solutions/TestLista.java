package solutions;
import ds.ListImpl;
import exceptions.WrongIndexException;

public class TestLista {
    public static void main(String[] args) {
        try {
            ListImpl<String> lista = new ListImpl<>();

            // Insertar elementos
            lista.insert(0, "A"); // ["A"]
            lista.insert(1, "B"); // ["A", "B"]
            lista.insert(1, "C"); // ["A", "C", "B"]
            lista.insert(3, "D"); // ["A", "C", "B", "D"]

            // Mostrar elementos
            System.out.println("Contenido de la lista:");
            for (String s : lista) {
                System.out.println(s);
            }

            // Obtener elementos
            System.out.println("\nElemento en posición 2: " + lista.get(2)); // Espera "B"

            // Buscar elementos
            System.out.println("\nPosición del elemento 'C': " + lista.search("C")); // Espera 1
            System.out.println("Posición del elemento 'Z': " + lista.search("Z")); // Espera -1

            // Borrar elementos
            lista.delete(1); // Elimina "C" → ["A", "B", "D"]
            lista.delete(0); // Elimina "A" → ["B", "D"]

            // Mostrar lista tras borrado
            System.out.println("\nLista tras eliminar:");
            for (String s : lista) {
                System.out.println(s);
            }

            // Tamaño
            System.out.println("\nTamaño de la lista: " + lista.size());

        } catch (WrongIndexException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
