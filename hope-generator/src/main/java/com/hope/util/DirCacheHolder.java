package com.hope.util;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 目录缓存工具类
 */
public class DirCacheHolder {
    private static ThreadLocal<ConcurrentHashMap> threadLocal = new ThreadLocal<>();


    public static void init() {
        ConcurrentHashMap<String, File> concurrentHashMap = new ConcurrentHashMap();
        threadLocal.set(concurrentHashMap);
    }

    public static void clear() {
        threadLocal.remove();
    }
    public static void add(String key,File dir) {
        ConcurrentHashMap<String,File> concurrentHashMap = threadLocal.get();
        if (concurrentHashMap == null) {
            concurrentHashMap = new ConcurrentHashMap<>();
        }
        concurrentHashMap.putIfAbsent(key,dir);
        threadLocal.set(concurrentHashMap);
    }

    public static File get(String key) throws Exception {
        ConcurrentHashMap<String,File> concurrentHashMap = threadLocal.get();
        if (concurrentHashMap == null || !concurrentHashMap.containsKey(key)) {
            throw new Exception("不包含key{" + key + "}的目录" );
        }
        return concurrentHashMap.get(key);
    }

}
