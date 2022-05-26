package com.tc.cache.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheElement<Integer,Object> {
    private Integer key;
    private Object value;

    public String toString() {
        return "[key=" + key + ", value=" + value + "]";
    }

}