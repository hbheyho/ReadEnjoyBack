package com.ReadEnjoyBack.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author:HB
 * @Description: TOken缓存
 * @Createdata:Created in  23:22  2018/5/9.
 */
public class TokenCache {
    // 申明日志
   private  static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    // 超过10000就使用LRU算法进行清除
   // 申明一个静态的内存块(初始化容量为1000 最大为10000 有效期为12个小时 在build里面进行一个匿名实现)
    private static LoadingCache<String,String> loadingCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000)
           .expireAfterAccess(30, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
               /*默认的数据加载实现 当调用key取值的时候 如果key没有对应的值 就调用这个方法加载*/
               @Override
               public String load(String key) throws Exception {
                   return "null";
               }
           });
    /*set key and value*/
    public static  void setKey(String key,String value){

        loadingCache.put(key, value);
    }
    /*get key*/
    public  static String getKey(String key){
        String value = null;
        try{
            value = loadingCache.get(key);
            if ("null".equals(value)){
                return null;
            }
            return value;
        }catch (Exception e){
            logger.error("localcache error",e);
        }
        return null;
    }

}
