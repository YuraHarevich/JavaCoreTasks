package org.example.CustomLinkedList;

public class CustomLinkedList<T> implements CustomLinkedListContract<T>{

    public CustomLinkedList() {
        root = null;
        tail = null;
    }

    Node<T> root;

    Node<T> tail;

    public int size() {
        return 0;
    }

    public void addFirst(T element) {
        if(root == null) {
            root = new Node<>(element, null, null);
            tail = root;
        }
        else {
            Node<T> previousRoot = root;
            root = new Node<>(element, previousRoot, null);
            previousRoot.setPrevious(root);
        }
    }

    public void addLast(T element) {
        if(tail == null) {
            tail = new Node<>(element, null, null);
            root = tail;
        }
        else {
            Node<T> previousTail = tail;
            tail = new Node<>(element, null, previousTail);
            previousTail.setNext(tail);
        }
    }

    public void add(int index, T element) {
        Node<T> currentNode = root;
        for(int i = 0; i < index; i++) {
            if(currentNode == null) {
                throw new CustomListException("Index out of bounds");
            }
            currentNode = currentNode.getNext();
        }
        Node<T> newNode = new Node<>(element, currentNode, currentNode.getPrevious());
        if(currentNode.getPrevious()!=null) {
            currentNode.getPrevious().setNext(newNode);
        }
        currentNode.setPrevious(newNode);
    }

    public T getFirst() {
        if(root != null) {
            return root.getValue();
        }
        throw new CustomListException("No first element");
    }

    public T getLast() {
        if(tail != null) {
            return tail.getValue();
        }
        throw new CustomListException("No last element");
    }

    public T get(int index) {
        Node<T> currentNode = root;
        for(int i = 0; i < index; i++) {
            if(currentNode == null) {
                throw new CustomListException("Index out of bounds");
            }
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    public T removeFirst() {
        if(root != null) {
            if(root.getNext() != null) {
                Node<T> previousRoot = root;
                root = root.getNext();
                return previousRoot.getValue();
            }
            T tempElement = root.getValue();
            root = null;
            tail = null;
            return tempElement;
        }
        throw new CustomListException("No elements");
    }

    public T removeLast() {
        if(tail != null) {
            if(tail.getPrevious() != null) {
                Node<T> previousTail = tail;
                tail = tail.getPrevious();
                tail.setNext(null);
                return previousTail.getValue();
            }
            T tempElement = root.getValue();
            root = null;
            tail = null;
            return tempElement;
        }
        throw new CustomListException("No elements");
    }

    public T remove(int index) {
        Node<T> currentNode = root;
        for(int i = 0; i < index; i++) {
            if(currentNode == null) {
                throw new CustomListException("Index out of bounds");
            }
            currentNode = currentNode.getNext();
        }
        if(currentNode.getPrevious() != null) {
            currentNode.getPrevious().setNext(currentNode.getNext());
        }
        if(currentNode.getNext() != null) {
            currentNode.getNext().setPrevious(currentNode.getPrevious());
        }
        return currentNode.getValue();
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Node current = root;

        while (current != null) {
            sb.append(current.getValue());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }

        return sb.toString();
    }
}
