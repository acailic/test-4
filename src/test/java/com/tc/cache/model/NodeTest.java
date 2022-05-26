package com.tc.cache.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tc.cache.model.DoublyLinkedList;
import com.tc.cache.model.DummyNode;
import com.tc.cache.model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeTest {
    Node<Object> testNodeA;
    Node<Object> testNodeB;

    @BeforeEach
    void setUp() {
        var list = new DoublyLinkedList<>();
        list.add("A");
        list.add("B");
        testNodeA = new Node<>("A", new DummyNode<>(), list);
        testNodeB = new Node<>("B", testNodeA, list);
    }
    @Test
    void isEmpty() {
        assertFalse(testNodeA.isEmpty());
    }

    @Test
    void getElement() {
        assertEquals("A", testNodeA.getElement());
    }

    @Test
    void detach() {
        testNodeA.detach();
        assertTrue(testNodeA.getNext().isEmpty());
    }


    @Test
    void getListReference() {
        assertEquals(testNodeA.getListReference(), testNodeB.getListReference());
    }

    @Test
    void setPrev() {
        testNodeA.setPrev(testNodeB);
        assertEquals(testNodeA.getPrev().getElement(), testNodeB.getElement());
    }

    @Test
    void setNext() {
        testNodeA.setNext(testNodeB);
        assertEquals(testNodeA.getNext().getElement(), testNodeB.getElement());
    }

    @Test
    void getPrev() {
        assertFalse(testNodeA.getPrev().isEmpty());
    }

    @Test
    void getNext() {
        assertEquals("A", testNodeB.getNext().getElement());
    }

    @Test
    void search() {
        assertEquals("A", testNodeA.search("A").getElement());
    }

    @Test
    void searchNull() {
        final var exception = assertThrows(NullPointerException.class, () -> {
            testNodeA.search("X").getElement();
        });
        assertNull(exception.getMessage());
    }

}