package solutions;

import ds.HashTable;
import exceptions.WrongIndexException;
import java.util.Iterator;

public class TestHashTable {
    public static void main(String[] args) {
        try {
            // Crear tabla hash con capacidad inicial de 4
            HashTable<String, Integer> tabla = new HashTable<>(4);

            // isEmpty() → true
            System.out.println("¿Está vacía?: " + tabla.isEmpty());

            // put() → insertar varios elementos
            tabla.put("uno", 1);
            tabla.put("dos", 2);
            tabla.put("tres", 3);
            tabla.put("cuatro", 4);
            tabla.put("cinco", 5); // debe hacer resize

            // size() → 5
            System.out.println("Tamaño: " + tabla.size());

            // contains()
            System.out.println("¿Contiene 'tres'?: " + tabla.contains("tres"));
            System.out.println("¿Contiene 'diez'?: " + tabla.contains("diez"));

            // get()
            System.out.println("Valor de 'cuatro': " + tabla.get("cuatro"));
            System.out.println("Valor de 'diez' (null): " + tabla.get("diez"));

            // put() reemplazando valor
            System.out.println("Valor previo de 'dos': " + tabla.put("dos", 22));
            System.out.println("Nuevo valor de 'dos': " + tabla.get("dos"));

            // iterator()
            for (String clave : tabla) {
                System.out.println("Clave: " + clave);
            }

            // remove()
            System.out.println("Eliminar 'tres': " + tabla.remove("tres"));
            System.out.println("¿Contiene 'tres' tras eliminar?: " + tabla.contains("tres"));
            System.out.println("Tamaño tras eliminar: " + tabla.size());

            // clear()
            tabla.clear();
            System.out.println("¿Está vacía tras clear?: " + tabla.isEmpty());
            System.out.println("Tamaño tras clear: " + tabla.size());

        } catch (WrongIndexException e) {
            System.err.println("¡Excepción al eliminar!: " + e.getMessage());
        }
    }
}
