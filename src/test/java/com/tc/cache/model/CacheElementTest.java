package com.tc.cache.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CacheElementTest {

    @Test
    void getKey() {
        CacheElement<Integer, Object> cacheElement = new CacheElement<>(1, "value");
        assertEquals(1, cacheElement.getKey());
    }

    @Test
    void getValue() {
        CacheElement<Integer, Object> cacheElement = new CacheElement<>(2, "value");
        assertEquals("value", cacheElement.getValue());
    }

    @Test
    void testToString() {
        CacheElement<Integer, Object> cacheElement = new CacheElement<>(3, "value");
        assertEquals("[key=3, value=value]", cacheElement.toString());
    }

    @Test
    void setKey() {
        CacheElement<Integer, Object> cacheElement = new CacheElement<>(4, "value");
        cacheElement.setKey(5);
        assertEquals(5, cacheElement.getKey());
    }

    @Test
    void setValue() {
        CacheElement<Integer, Object> cacheElement = new CacheElement<>(6, "value");
        cacheElement.setValue("value2");
        assertEquals("value2", cacheElement.getValue());
    }

    @Test
    void testEquals() {
        CacheElement<Integer, Object> cacheElement1 = new CacheElement<>(7, "value");
        CacheElement<Integer, Object> cacheElement2 = new CacheElement<>(7, "value");
        assertEquals(cacheElement1, cacheElement2);
    }


    @Test
    void testHashCode() {
        CacheElement<Integer, Object> cacheElement1 = new CacheElement<>(9, "value");
        CacheElement<Integer, Object> cacheElement2 = new CacheElement<>(9, "value");
        assertEquals(cacheElement1.hashCode(), cacheElement2.hashCode());
    }

}