package org.example.CustomLinkedList;


public interface CustomLinkedListContract<T> {
    int size();
    void addFirst(T element);
    void addLast(T element);
    void add(int index, T element);
    T getFirst();
    T getLast();
    T get(int index);
    T removeFirst();
    T removeLast();
    T remove(int index);
}
