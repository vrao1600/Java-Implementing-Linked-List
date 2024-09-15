package DataStructures;

import Exceptions.*;
import ADTs.*;

/**
 * A linked list implementation of the ListADT interface.
 *
 * @param <T> the type of elements stored in the list; must be comparable
 */
public class LinkedList<T extends Comparable<T>> implements ListADT<T> {
    
    /**
     * The head node of the singly linked list.
     */
    SinglyLinkedNode<T> head;

    /**
     * The number of elements in the linked list.
     */
    int size;

    /**
     * Creates an empty list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public void addFirst(T element) {
        SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(element);
        newNode.setNext(head);
        head = newNode;
        size++;
    }


    public void addLast(T element) {
        SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            SinglyLinkedNode<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }


    public void addAfter(T target, T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        SinglyLinkedNode<T> current = head;
        while (current != null && !current.getData().equals(target)) {
            current = current.getNext();
        }
        if (current == null) {
            throw new ElementNotFoundException("LinkedList");
        }
        SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(element);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }


    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        T result = head.getData();
        head = head.getNext();
        size--;
        return result;
    }


    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        if (head.getNext() == null) {
            T result = head.getData();
            head = null;
            size--;
            return result;
        }
        SinglyLinkedNode<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T result = current.getNext().getData();
        current.setNext(null);
        size--;
        return result;
    }


    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        if (head.getData().equals(element)) {
            return removeFirst();
        }
        SinglyLinkedNode<T> current = head;
        SinglyLinkedNode<T> previous = null;
        while (current != null && !current.getData().equals(element)) {
            previous = current;
            current = current.getNext();
        }
        if (current == null) {
            throw new ElementNotFoundException("LinkedList");
        }
        previous.setNext(current.getNext());
        size--;
        return current.getData();
    }


    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        return head.getData();
    }


    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        SinglyLinkedNode<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getData();
    }


    public boolean contains(T target) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        SinglyLinkedNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(target)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    public int indexOf(T target) {
        if (isEmpty()) {
            return -1; // Indicate that the target is not found in an empty list
        }
        SinglyLinkedNode<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.getData().equals(target)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }


    public T get(int index) throws InvalidArgumentException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        if (index < 0 || index >= size) {
            throw new InvalidArgumentException("Invalid index");
        }
        SinglyLinkedNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }


    public void set(int index, T element) throws InvalidArgumentException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("LinkedList");
        }
        if (index < 0 || index >= size) {
            throw new InvalidArgumentException("Invalid index");
        }
        SinglyLinkedNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(element);
    }
}
