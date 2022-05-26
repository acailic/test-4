package com.tc.cache.service;

import static org.junit.jupiter.api.Assertions.*;

import com.tc.cache.service.LRUCache;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    private LRUCache<Integer, String> lruCache;

    @BeforeEach
    void setUp() {
        lruCache = new LRUCache<>(4);
        lruCache.put(1, "A");
    }

    @Test
    void testPut() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        assertEquals(4, lruCache.size());
        assertEquals("A", lruCache.get(1).get());
        assertEquals("B", lruCache.get(2).get());
        assertEquals("C", lruCache.get(3).get());
        assertEquals(Optional.empty(), lruCache.get(5));
    }

    @Test
    void testGet() {
        assertEquals("A", lruCache.get(1).get());
    }

    @Test
    void testClear() {
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        lruCache.clear();
        assertEquals(0, lruCache.size());
    }


    @Test
    void displayCache() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        assertEquals(
            "{head=[key=4, value=D], tail=[key=1, value=A]}",
            lruCache.displayHeadAndTail());
    }

    @Test
    void getHeadKey() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        assertEquals(4, lruCache.getHeadKey());
    }

    @Test
    public void addSomeDataToCache_WhenGetData_ThenIsEqualWithCacheElement() {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("1", "test1");
        lruCache.put("2", "test2");
        lruCache.put("3", "test3");
        assertEquals("test1", lruCache.get("1").get());
        assertEquals("test2", lruCache.get("2").get());
        assertEquals("test3", lruCache.get("3").get());
    }

    @Test
    public void addDataToCacheToTheNumberOfSize_WhenAddOneMoreData_ThenLeastRecentlyDataWillEvict() {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("1", "test1");
        lruCache.put("2", "test2");
        lruCache.put("3", "test3");
        lruCache.put("4", "test4");
        assertFalse(lruCache.get("1").isPresent());
    }

    @Test
    void put() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        assertEquals(4, lruCache.size());
        assertEquals("A", lruCache.get(1).get());
        assertEquals("B", lruCache.get(2).get());
        assertEquals("C", lruCache.get(3).get());
        assertEquals(Optional.empty(), lruCache.get(5));
    }


    @Test
    void size() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        assertEquals(4, lruCache.size());
    }

    @Test
    void clear() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        lruCache.clear();
        assertEquals(0, lruCache.size());
    }

    @Test
    void displayHeadAndTail() {
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        assertEquals(
            "{head=[key=4, value=D], tail=[key=1, value=A]}",
            lruCache.displayHeadAndTail());
    }

    @Test
    void getMaxSize() {
        assertEquals(4, lruCache.getMaxSize());
    }
}