package com.tc.cache.model;

public class Node<Object> implements LinkedListNode<Object> {
    private Object value;
    private DoublyLinkedList<Object> list;
    private LinkedListNode next;
    private LinkedListNode prev;

    public Node(Object value, LinkedListNode<Object> next, DoublyLinkedList<Object> list) {
        this.value = value;
        this.next = next;
        this.setPrev(next.getPrev());
        this.prev.setNext(this);
        this.next.setPrev(this);
        this.list = list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public Object getElement() {
        return value;
    }

    public void detach() {
        this.prev.setNext(this.getNext());
        this.next.setPrev(this.getPrev());
    }

    @Override
    public DoublyLinkedList<Object> getListReference() {
        return this.list;
    }

    @Override
    public LinkedListNode<Object> setPrev(LinkedListNode<Object> prev) {
        this.prev = prev;
        return this;
    }

    @Override
    public LinkedListNode<Object> setNext(LinkedListNode<Object> next) {
        this.next = next;
        return this;
    }

    @Override
    public LinkedListNode<Object> getPrev() {
        return this.prev;
    }

    @Override
    public LinkedListNode<Object> getNext() {
        return this.next;
    }

    @Override
    public LinkedListNode<Object> search(Object value) {
        return this.getElement() == value ? this : this.getNext().search(value);
    }

    @Override
    public String toString() {
        return "Node{" +
            "value=" + value +
            '}';
    }
}
