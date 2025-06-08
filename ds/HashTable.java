package ds;
import exceptions.*;
import adt.*;
import java.util.Iterator;


public class HashTable<K, V> implements Dictionary<K,V> {
    private List<TableEntry<K,V>>[] tabla = null;
    private int size;
    private int capacidad;
    
    @SuppressWarnings("unchecked")
    public HashTable(int m) {
        tabla = new List[m];
        for (int i = 0; i < m; i++) {
            tabla[i] = new ListImpl<TableEntry<K,V>>();
        }
        this.capacidad=m;
    }
    @SuppressWarnings("unchecked")
    public HashTable() {
        int m=16;
        tabla = new List[m];
        for (int i = 0; i < m; i++) {
            tabla[i] = new ListImpl<TableEntry<K,V>>();
        }
        this.capacidad=m;
    }
    
    
    private static class TableEntry<K, V>{
        private V data;
        private K key;
        TableEntry(K key, V data){
            this.data=data;
            this.key=key;
        }
        
    }

    public V put(K key, V value) throws WrongIndexException{
        int index = Math.abs(key.hashCode())%capacidad;
        List <TableEntry<K,V>> cubeta = tabla[index];

        for(TableEntry<K,V> aux : cubeta){
            //Hacemos que sustituya el antinguo por el nuevo
            if (aux.key.equals(key)){
                V viejovalor = aux.data;
                aux.data = value;
                return viejovalor;
            }
        }
        cubeta.insert(0,new TableEntry<K,V>(key, value));
        size ++;

        //Comprobar factor de carga 
        if((float)size/capacidad >0.75){
            resize();
        }
        return null;
        
    }
    public V get(K key){
        int index = Math.abs(key.hashCode()%capacidad);
        List <TableEntry<K,V>> cubeta = tabla[index];

        for (TableEntry<K,V> aux : cubeta){
            if (key.equals(aux.key)){
                return aux.data;
            }
        }
        return null;
    }
    public V remove(K key) throws WrongIndexException{
        int index = Math.abs(key.hashCode()%capacidad);
        List <TableEntry<K,V>> cubeta = tabla[index];
        
        for (TableEntry<K,V> aux : cubeta){
            if (aux.key.equals(key)){
                V value = aux.data;
                cubeta.delete(cubeta.search(aux));
                size--;
                return value;
            }
        }
        return null;
    }
    public boolean contains (K key){
        int index = Math.abs(key.hashCode()%capacidad);
        List <TableEntry<K,V>> cubeta = tabla[index];

        for (TableEntry<K,V> aux : cubeta){
            if (aux.key.equals(key)){
                return true;
            }
        }
        return false;
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }
    public Iterator<K> iterator(){
       return new Citerator();
    }
    public void clear(){
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListImpl<>();
        }
        size = 0;
    }
    @SuppressWarnings("unchecked")
private void resize() throws WrongIndexException {
    int nuevaCapacidad = capacidad * 2;
    List<TableEntry<K, V>>[] nuevaTabla = new List[nuevaCapacidad];

    for (int i = 0; i < nuevaCapacidad; i++) {
        nuevaTabla[i] = new ListImpl<>();
    }

    // Reinsertar todos los elementos en la nueva tabla
    for (List<TableEntry<K, V>> cubeta : tabla) {
        for (TableEntry<K, V> entry : cubeta) {
            int newIndex = Math.abs(entry.key.hashCode()) % nuevaCapacidad;
            nuevaTabla[newIndex].insert(0, entry);
        }
    }

    tabla = nuevaTabla;
    capacidad = nuevaCapacidad;
}

    /*
    
    
    
    
    */
         
    
  private class Citerator implements Iterator<K> {
    private int bucketIndex;
    private Iterator<TableEntry<K, V>> bucketIterator;

    public Citerator() {
        bucketIndex = 0;
        bucketIterator = null;
        advanceToNextNonEmptyBucket();
    }

    private void advanceToNextNonEmptyBucket() {
        while (bucketIndex < capacidad) {
            List<TableEntry<K, V>> cubeta = tabla[bucketIndex];
            if (cubeta != null && cubeta.size() > 0) {
                bucketIterator = cubeta.iterator();
                return;
            }
            bucketIndex++;
        }
        bucketIterator = null; // Si no quedan más cubetas
    }

    @Override
    public boolean hasNext() {
        return bucketIterator != null && bucketIterator.hasNext();
    }

    @Override
    public K next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }

        K clave = bucketIterator.next().key;

        // Si el iterador actual ya no tiene más, avanza al siguiente
        if (!bucketIterator.hasNext()) {
            bucketIndex++;
            advanceToNextNonEmptyBucket();
        }

        return clave;
    }
}



}
