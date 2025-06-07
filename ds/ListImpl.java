package ds;
import adt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.*;
public class ListImpl <E> implements List<E> {
    Node <E> primero=null;
    int size=0;
    public void insert(int pos, E data) throws WrongIndexException{
        if (pos<0 || pos > size){
            throw new WrongIndexException("No existe");
        }
        Node <E> aux = primero;
        Node<E> nuevo = new Node<E>(data, pos);
        if (pos==0){
            nuevo.next=primero;
            primero = nuevo;
            
        }else {
            for(int i =0;i<pos-1;i++){
                aux = aux.next;
            }
            nuevo.next = aux.next;
            aux.next=nuevo;
        }
        Node<E> temp = nuevo.next;
        int nuevaPos = pos + 1;
        while (temp != null) {
            temp.pos = nuevaPos;
            temp = temp.next;
            nuevaPos++;
        }
        size++;

    }
    public void delete(int pos) throws WrongIndexException{
        if (pos<0 || pos > size-1){
            throw new WrongIndexException("No existe");
        }
        Node <E> aux = primero;
        if (pos==0){
            primero = aux.next;
            
        }else {
            for(int i =0;i<pos-1;i++){
                aux = aux.next;
            }
            aux.next = aux.next.next;
        }
        Node<E> temp = aux.next;
        int nuevaPos = pos - 1;
        while (temp != null) {
            temp.pos = nuevaPos;
            temp = temp.next;
            nuevaPos++;
        }
        size--;
    }
    public E get(int pos) throws WrongIndexException{
        if (pos<0 || pos > size-1){
            throw new WrongIndexException("No existe");
        }
        Node <E> aux = primero;
        if (pos==0){
            return primero.data;
            
        }else {
            for(int i =0;i<pos;i++){
                aux = aux.next;
            }
            return aux.data;
        }
    }
    @Override
    public int search(E data) {
        Iterator<E> it = this.iterator();
        int pos = 0;
        while (it.hasNext()) {
            E actual = it.next();
            if (actual.equals(data)) {
                return pos;
            }
            pos++;
    }
    return -1; // si no lo encuentra
}

    public Iterator<E> iterator(){
        return new CIterator();
    }
    public int size(){
        return size;
    }

    private static class Node<E>{
    E data;
    int pos;
    Node<E> next;
    Node(E data,int pos) {
        this.data = data;
        this.pos=pos;
        this.next = null;
    }
}
private class CIterator implements Iterator<E>{
    private Node<E> actual= primero;
    @Override
    public boolean hasNext() {
        if (actual==null){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public E next() {
        if (!hasNext()){
            throw new NoSuchElementException("No existe");
        }
        E temp = actual.data;
        actual = actual.next;
        return temp; 
    }
}

    
}
