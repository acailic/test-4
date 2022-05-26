package com.tc.cache.model;

import static org.junit.jupiter.api.Assertions.*;

import com.tc.cache.model.DoublyLinkedList;
import com.tc.cache.model.DummyNode;
import org.junit.jupiter.api.Test;

class DummyNodeTest {

    private static DummyNode<String> dummyNodeRef = new DummyNode<>(new DoublyLinkedList<>());
    @Test
    void getListReference() {
        assertNotEquals(null,dummyNodeRef.getListReference());
    }

    @Test
    void getNext() {
        assertEquals(dummyNodeRef,dummyNodeRef.getNext());
    }

    @Test
    void getPrev(){
        assertEquals(dummyNodeRef,dummyNodeRef.getPrev());
    }
}