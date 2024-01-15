package com.fiserv.job.file_to_database.cache;


import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.RetryContextCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RetryContextCacheImpl implements RetryContextCache {

    private final Map<Object, RetryContext> cache = new ConcurrentHashMap<>();

    @Override
    public RetryContext get(Object key) {
        return cache.get(key);
    }

    @Override
    public void put(Object key, RetryContext context) {
        cache.put(key, context);
    }

    @Override
    public void remove(Object key) {
        cache.remove(key);
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }
}