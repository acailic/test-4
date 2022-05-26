package com.tc.cache.model;

import static org.junit.jupiter.api.Assertions.*;

import com.tc.cache.model.DoublyLinkedList;
import com.tc.cache.model.LinkedListNode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

    private static DoublyLinkedList<String> list;

    @BeforeEach
    void setUpBeforeClass() {
        list = new DoublyLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Test
    void clear() {
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void size() {
        assertEquals(3, list.size());
    }

    @Test
    void removeTail() {
        list.removeTail();
        assertEquals(2, list.size());
    }

    @Test
    void moveToFront() {
        LinkedListNode<String> tail = list.getTail();
        list.moveToFront(tail);
        assertEquals(tail.getElement(), list.getHead().getElement());
    }

    @Test
    void updateAndMoveToFront() {
        LinkedListNode<String> dummy = list.getTail();
        list.moveToFront(dummy);
        assertEquals(dummy.getElement(), list.getHead().getElement());
    }

    @Test
    void add() {
        list.add("D");
        assertEquals(4, list.size());
    }

    @Test
    void getHead() {
        assertEquals("C", list.getHead().getElement());
    }

    @Test
    void getTail() {
        assertEquals("A", list.getTail().getElement());
    }

    @Test
    void setHead() {
        LinkedListNode<String> head = list.getHead();
        list.updateAndMoveToFront(head, "D");
        assertNotEquals(head, list.getHead());
        assertEquals("D", list.getHead().getElement());
    }

    @Test
    void setTail() {
        LinkedListNode<String> tail = list.getTail();
        list.updateAndMoveToFront(tail, "D");
        assertNotEquals(tail, list.getTail());
        assertEquals("B", list.getTail().getElement());
    }

    @Test
    void setSize() {
        var atomicInt = new AtomicInteger(4);
        list.setSize(atomicInt);
        assertEquals(4, list.size());
    }

    @Test
    void getDummyNode() {
        assertNotNull(list.getDummyNode());
    }

    @Test
    void getSize() {
        assertEquals(new AtomicInteger(3).get(), list.getSize().get());
    }

    @Test
    void getLock() {
        assertNotNull(list.getLock());
    }

}