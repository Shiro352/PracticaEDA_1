package adt;
import java.util.Iterator;

import exceptions.WrongIndexException;
public interface Dictionary<K,V> extends Iterable<K>{
    V put(K key, V value) throws WrongIndexException;
    V get(K key);
    V remove(K key) throws WrongIndexException;
    boolean contains(K key);
    int size();
    boolean isEmpty();
    void clear();
    Iterator<K> iterator();
}
