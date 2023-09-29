package com.bottrack.service;

import com.bottrack.model.CacheModal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationCache {
    private static volatile ApplicationCache instance;
    private Map<String, CacheModal> _cache;
    private ApplicationCache() {
        _cache = new ConcurrentHashMap<>();
    }

    public static ApplicationCache getInstance() {
        if(instance == null) {
            synchronized (ApplicationCache.class) {
                if(instance == null) {
                    instance = new ApplicationCache();
                }
            }
        }

        return instance;
    }

    public void put(String key, CacheModal value) {
        _cache.put(key, value);
    }

    public void remove(String key) {
        _cache.remove(key);
    }

    public CacheModal get(String key) {
        return _cache.get(key);
    }
}
