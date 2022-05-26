package com.tc.cache.service;

import com.tc.cache.model.CacheElement;
import com.tc.cache.model.DoublyLinkedList;
import com.tc.cache.model.LinkedListNode;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LRUCache<Integer, Object> implements Cache<Integer, Object> {

    @Getter
    @Value("${cache.size:10}")
    private int maxSize;

    private Map<Integer, LinkedListNode<CacheElement<Integer, Object>>> linkedListNodeMap;
    private DoublyLinkedList<CacheElement<Integer, Object>> doublyLinkedList;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache() {
        this.linkedListNodeMap = new ConcurrentHashMap<>(maxSize);
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    public LRUCache(int size) {
        this.maxSize = size;
        this.linkedListNodeMap = new ConcurrentHashMap<>(size);
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Override
    public boolean put(Integer key, Object value) {
        this.lock.writeLock().lock();
        try {
            CacheElement<Integer, Object> item = new CacheElement<>(key, value);
            LinkedListNode<CacheElement<Integer, Object>> newNode;
            if (this.linkedListNodeMap.containsKey(key)) {
                LinkedListNode<CacheElement<Integer, Object>> node = this.linkedListNodeMap.get(key);
                newNode = doublyLinkedList.updateAndMoveToFront(node, item);
            } else {
                if (this.size() >= this.maxSize) {
                    this.evictElement();
                }
                newNode = this.doublyLinkedList.add(item);
            }
            if (newNode.isEmpty()) {
                return false;
            }
            this.linkedListNodeMap.put(key, newNode);
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<Object> get(Integer key) {
        this.lock.readLock().lock();
        try {
            LinkedListNode<CacheElement<Integer, Object>> linkedListNode = this.linkedListNodeMap.get(key);
            if (linkedListNode != null && !linkedListNode.isEmpty()) {
                linkedListNodeMap.put(key, this.doublyLinkedList.moveToFront(linkedListNode));
                return Optional.of(linkedListNode.getElement().getValue());
            }
            return Optional.empty();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public int size() {
        this.lock.readLock().lock();
        try {
            return doublyLinkedList.size();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public void clear() {
        this.lock.writeLock().lock();
        try {
            linkedListNodeMap.clear();
            doublyLinkedList.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    private boolean evictElement() {
        this.lock.writeLock().lock();
        try {
            LinkedListNode<CacheElement<Integer, Object>> linkedListNode = doublyLinkedList.removeTail();
            if (linkedListNode.isEmpty()) {
                return false;
            }
            linkedListNodeMap.remove(linkedListNode.getElement().getKey());
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public String displayHeadAndTail(){
        this.lock.readLock().lock();
        try {
            return doublyLinkedList.toString() ;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public Integer getHeadKey(){
        this.lock.readLock().lock();
        try {
            return doublyLinkedList.getHead().getElement().getKey();
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
