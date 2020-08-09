package com.hpedu.config.redis;

/**
 * Created by Souler on 2018/10/18.
 * 新写了一个SpringRedisCacheManager，继承自RedisCacheManager，
 * 用于对@CacheDuration解析及有效期的设置
 */

@Deprecated
public class SpringRedisCacheManager /*extends RedisCacheManager implements ApplicationContextAware, InitializingBean*/ {
    /*private ApplicationContext applicationContext;

    public SpringRedisCacheManager(RedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        parseCacheDuration(applicationContext);
    }

    private void parseCacheDuration(ApplicationContext applicationContext) {
        final Map<String, Long> cacheExpires = new HashMap<>();
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            final Class clazz = applicationContext.getType(beanName);
            Service service = findAnnotation(clazz, Service.class);
            if (null == service) {
                continue;
            }
            addCacheExpires(clazz, cacheExpires);
        }
        //设置有效期
        super.setExpires(cacheExpires);
    }

    private void addCacheExpires(final Class clazz, final Map<String, Long> cacheExpires) {
        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                ReflectionUtils.makeAccessible(method);
                //根据CacheDuration注解获取时间
                CacheDuration cacheDuration = findCacheDuration(clazz, method);
                if(cacheDuration!=null) {
                    Cacheable cacheable = findAnnotation(method, Cacheable.class);
                    CacheConfig cacheConfig = findAnnotation(clazz, CacheConfig.class);
                    Set<String> cacheNames = findCacheNames(cacheConfig, cacheable);
                    for (String cacheName : cacheNames) {
                        cacheExpires.put(cacheName, cacheDuration.duration());
                    }
                }
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                return null != findAnnotation(method, Cacheable.class);
            }
        });
    }

    *//**
     * CacheDuration标注的有效期，优先使用方法上标注的有效期
     *
     * @param clazz
     * @param method
     * @return
     *//*
    private CacheDuration findCacheDuration(Class clazz, Method method) {
        CacheDuration methodCacheDuration = findAnnotation(method, CacheDuration.class);
        if (null != methodCacheDuration) {
            return methodCacheDuration;
        }
        CacheDuration classCacheDuration = findAnnotation(clazz, CacheDuration.class);
        if (null != classCacheDuration) {
            return classCacheDuration;
        }
        return null;
    }

    private Set<String> findCacheNames(CacheConfig cacheConfig, Cacheable cacheable) {
        return isEmpty(cacheable.value()) ?
                newHashSet(cacheConfig.cacheNames()) : newHashSet(cacheable.value());
    }*/
}