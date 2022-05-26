package com.tc.cache.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tc.cache.model.CacheData;
import com.tc.cache.service.CacheService;
import com.tc.cache.service.LRUCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@Slf4j
class CacheServiceTest  {
    private CacheService cacheServiceImpl;
    private LRUCache cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new LRUCache(4);
        assertEquals(4, cacheService.getMaxSize());
        cacheService.put(1, "one");
        cacheService.put(2, "two");
        cacheService.put(3, "three");
        cacheService.put(4, "four");
        cacheServiceImpl = new CacheService(cacheService);
    }

    @AfterEach
    void tearDown() {
        cacheService.clear();
        assertEquals(0, cacheService.size());
    }

    @Test
    void cacheIdGet() {
        var responseObjectOne = cacheServiceImpl.cacheIdGet(1);
        assertEquals("one", responseObjectOne.getBody());
        assertEquals(HttpStatus.OK, responseObjectOne.getStatusCode());
        var responseObjectTwo = cacheServiceImpl.cacheIdGet(2);
        assertEquals("two", responseObjectTwo.getBody());
        assertEquals(HttpStatus.OK, responseObjectTwo.getStatusCode());
    }

    @Test
    void cachePut() {
        cacheServiceImpl.cachePut(new CacheData().data("five").id(5));
        assertEquals("five", cacheService.get(5).get());
        assertTrue(cacheService.getHeadKey().equals(5));
        log.info("Cache Display: {} ",cacheService.displayHeadAndTail());
        cacheServiceImpl.cachePut(new CacheData().data("six").id(6));
        assertTrue(cacheService.getHeadKey().equals(6));
        assertEquals("six", cacheService.get(6).get());
        log.info("Cache Display: {} ",cacheService.displayHeadAndTail());
    }

    @Test
    void testCacheIdGet() {
        var responseObjectOne = cacheServiceImpl.cacheIdGet(1);
        assertEquals("one", responseObjectOne.getBody());
        assertEquals(HttpStatus.OK, responseObjectOne.getStatusCode());
        var responseObjectTwo = cacheServiceImpl.cacheIdGet(2);
        assertEquals("two", responseObjectTwo.getBody());
        assertEquals(HttpStatus.OK, responseObjectTwo.getStatusCode());
    }

    @Test
    void testCachePut() {
        cacheServiceImpl.cachePut(new CacheData().data("five").id(5));
        assertEquals("five", cacheService.get(5).get());
        assertTrue(cacheService.getHeadKey().equals(5));
        log.info("Cache Display: {} ",cacheService.displayHeadAndTail());
        cacheServiceImpl.cachePut(new CacheData().data("six").id(6));
        assertTrue(cacheService.getHeadKey().equals(6));
        assertEquals("six", cacheService.get(6).get());
        log.info("Cache Display: {} ",cacheService.displayHeadAndTail());
    }
}