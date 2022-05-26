package com.tc.cache.service;


import java.util.Optional;

/**
 * The interface Cache.
 *
 * @param <Integer> the type parameter
 * @param <Object>  the type parameter
 */
public interface Cache<Integer, Object> {
    boolean put(Integer key, Object value);
    Optional<Object> get(Integer key);
    int size();
    void clear();
}
