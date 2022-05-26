package com.tc.cache.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tc.cache.api.CacheApiDelegate;
import com.tc.cache.model.CacheData;

@RequiredArgsConstructor
@Component
@Slf4j
public class CacheService implements CacheApiDelegate {

    private final LRUCache lruCache;

    @Override
    public ResponseEntity<Object> cacheIdGet(Integer id) {
        log.info("cacheIdGet() invoked with id: {}", id);
        Optional<Object> cachedValue= lruCache.get(id);
        return new ResponseEntity<>(cachedValue.orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> cachePut(CacheData cacheData) {
        log.info("CacheService.cachePut() cacheData: {}", cacheData);
        lruCache.put(cacheData.getId(), cacheData.getData());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
