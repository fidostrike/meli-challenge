package com.entrevista.meli.melishows.Conf;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@EnableCaching
@Configuration
@PropertySource("classpath:cacching.properties")
public class CaffeineCacheManager {
    @Autowired
    private Environment env;
    final static String CACHE_NAMES = "cacching.names";
    final static String CACHE_TIMEOUTS = "cacching.timeouts";
    final static String CACHE_MAXS = "cacching.max";

    @Bean
    public CacheManager cacheManager() {
        Map<String, CacheSpec> specs = null;

        try
        {
            //get array caches in properties
            specs = getMapCache();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        SimpleCacheManager manager = new SimpleCacheManager();

        if(specs!=null)
        {
            //execute builder
            List<CaffeineCache> caches = specs.entrySet().stream().map(entry->buildCache(entry.getKey(), entry.getValue())).collect(Collectors.toList());
            manager.setCaches(caches);
        }

        return manager;
    }

    /**
     * Build CaffeineCaches
     * @param name
     * @param cacheSpec
     * @return
     */
    private CaffeineCache buildCache(String name, CacheSpec cacheSpec)
    {
        final Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder().expireAfterAccess(cacheSpec.getTimeout(),TimeUnit.MINUTES).maximumSize(cacheSpec.getMax());
        return new CaffeineCache(name, caffeineBuilder.build());
    }

    /**
     * get HashMap with property values
     * @return
     */
    private Map<String, CacheSpec> getMapCache()
    {
        Map<String, CacheSpec> result = new HashMap<>();

        String[] cacheNames = env.getProperty(CACHE_NAMES).split("[|]");
        String[] cacheTimeouts = env.getProperty(CACHE_TIMEOUTS).split("[|]");
        String[] cacheMaxs = env.getProperty(CACHE_MAXS).split("[|]");

        for(int index=0;index<cacheNames.length;index++)
        {
            setPropertiesCacheSpec(result, cacheNames[0], cacheTimeouts[0],cacheMaxs[0]);
        }

        return result;
    }

    /**
     * Set the instance with Cache Data
     * @param result
     * @param key
     * @param timeout
     * @param max
     */
    private void setPropertiesCacheSpec(Map<String, CacheSpec> result, String key, String timeout, String max)
    {
        CacheSpec item = getCacheSpecInstance(result, key);

        item.setTimeout(Integer.valueOf(timeout));
        item.setMax(Integer.valueOf(max));
    }

    /**
     * Create the instance
     * @param result
     * @param key
     * @return
     */
    private CacheSpec getCacheSpecInstance(Map<String, CacheSpec> result, String key)
    {
        if(!result.containsKey(key))
        {
            //create new instance
            result.put(key, new CacheSpec());
        }

        //get CacheSpec instance
        CacheSpec item = result.get(key);

        return item;
    }

    /**
     * CacheSpec is the object with the data of the properties
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CacheSpec{
        private Integer timeout;
        private Integer max = 200;
    }
}
