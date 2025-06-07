package adt;
import exceptions.*;
import java.util.*;
public interface List<E> extends Iterable<E> {
    void insert(int pos, E data) throws WrongIndexException;
    void delete(int pos) throws WrongIndexException;
    E get(int pos) throws WrongIndexException;
    int search(E data);
    Iterator<E> iterator();
    int size();
}
