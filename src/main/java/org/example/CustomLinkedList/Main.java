package org.example.CustomLinkedList;

public class Main {
    public static void main(String[] args) {
        CustomLinkedListContract<Integer> list = new CustomLinkedList<>();
        list.addFirst(1);
        System.out.println(list);
        list.addFirst(2);
        System.out.println(list);
        list.addLast(10);
        System.out.println(list);
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();

        list.addLast(11);
        System.out.println(list);

        list.add(1, 7);
        System.out.println(list);

        list.add(0, 7);
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

    }
}