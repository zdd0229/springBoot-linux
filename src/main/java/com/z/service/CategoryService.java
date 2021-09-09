package com.z.service;

import com.z.bean.entity.Category;
import com.z.controller.CategoryNodeDto;
import com.z.dao.CategoryMapper;
import com.z.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    private static final String CATEGORY = "three-level-category";

    private AtomicInteger dbThread = new AtomicInteger();
    private AtomicInteger cacheThread = new AtomicInteger();

    /**
     * 查询三级分类
     *
     * @return
     */
    public List<CategoryNodeDto> getThreeLevelCategory1() {

        Object o = redisTemplate.opsForValue().get(CATEGORY);
        if (!Objects.isNull(o)) {
            return JsonUtil.jsonToList((String) o, CategoryNodeDto.class);
        }
        //缓存击穿问题(加锁，拿到锁后，先查询缓存(防止等待锁的请求，再次请求数据库)，才可以查询数据库)
        synchronized (this) {
            Object o1 = redisTemplate.opsForValue().get(CATEGORY);
            if (!Objects.isNull(o1)) {
                return JsonUtil.jsonToList((String) o1, CategoryNodeDto.class);
            }

            List<Category> all = categoryMapper.getCategory(null, null, null);
            Map<Long, List<Category>> allByParentId = all.stream().collect(Collectors.groupingBy(Category::getParentCid));

            List<CategoryNodeDto> category = getCategory(0L, allByParentId);
            //写入缓存
            //解决缓存穿透问题，为空值设置较短的过期时间
            redisTemplate.opsForValue().set(CATEGORY, JsonUtil.toStr(category), 1, CollectionUtils.isEmpty(category) ? TimeUnit.MINUTES : TimeUnit.DAYS);

            return category;
        }

    }

    /**
     * 查询三级分类（分布式锁实现）
     *
     * @return
     */
    public List<CategoryNodeDto> getThreeLevelCategory2() {

        Object o = redisTemplate.opsForValue().get(CATEGORY);
        if (!Objects.isNull(o)) {
            return JsonUtil.jsonToList((String) o, CategoryNodeDto.class);
        }
        //缓存击穿问题(加锁，拿到锁后，先查询缓存(防止等待锁的请求，再次请求数据库)，才可以查询数据库)
        //redis锁
        while (!redisTemplate.opsForValue().setIfAbsent("three-level-category-get-lock", 1)) {
            continue;
        }
        log.info("共有{}个线程想去查询数据库", dbThread.incrementAndGet());
        Object o1 = redisTemplate.opsForValue().get(CATEGORY);
        if (!Objects.isNull(o1)) {
            log.info("共有{}个线程走了缓存", cacheThread.incrementAndGet());
            redisTemplate.delete("three-level-category-get-lock");
            return JsonUtil.jsonToList((String) o1, CategoryNodeDto.class);
        }

        List<Category> all = categoryMapper.getCategory(null, null, null);
        Map<Long, List<Category>> allByParentId = all.stream().collect(Collectors.groupingBy(Category::getParentCid));

        List<CategoryNodeDto> category = getCategory(0L, allByParentId);
        //写入缓存
        //解决缓存穿透问题，为空值设置较短的过期时间
        redisTemplate.opsForValue().set(CATEGORY, JsonUtil.toStr(category), 1, CollectionUtils.isEmpty(category) ? TimeUnit.MINUTES : TimeUnit.DAYS);

        redisTemplate.delete("three-level-category-get-lock");

        //测试环境初始化
        return category;
    }

    /**
     * 查询三级分类（redission实现）
     *
     * @return
     */
    public List<CategoryNodeDto> getThreeLevelCategory() {

        Object o = redisTemplate.opsForValue().get(CATEGORY);
        if (!Objects.isNull(o)) {
            return JsonUtil.jsonToList((String) o, CategoryNodeDto.class);
        }
        //缓存击穿问题(加锁，拿到锁后，先查询缓存(防止等待锁的请求，再次请求数据库)，才可以查询数据库)
        //redission
        RLock lock = redissonClient.getLock("three-level-category-redisson-lock");
        lock.lock();

        List<CategoryNodeDto> category = null;
        try {
            log.info("共有{}个线程想去查询数据库", dbThread.incrementAndGet());
            Object o1 = redisTemplate.opsForValue().get(CATEGORY);
            if (!Objects.isNull(o1)) {
                log.info("共有{}个线程走了缓存", cacheThread.incrementAndGet());
                return JsonUtil.jsonToList((String) o1, CategoryNodeDto.class);
            }

            List<Category> all = categoryMapper.getCategory(null, null, null);
            Map<Long, List<Category>> allByParentId = all.stream().collect(Collectors.groupingBy(Category::getParentCid));

            category = getCategory(0L, allByParentId);
            //写入缓存
            //解决缓存穿透问题，为空值设置较短的过期时间
            redisTemplate.opsForValue().set(CATEGORY, JsonUtil.toStr(category), 1, CollectionUtils.isEmpty(category) ? TimeUnit.MINUTES : TimeUnit.DAYS);
            //测试环境初始化
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return category;

    }

    /**
     * 控制层级的正向递归
     */
    public List<CategoryNodeDto> getCategory(Long parentCid, Map<Long, List<Category>> allByParentId) {

        List<CategoryNodeDto> res = new ArrayList<>();
        List<Category> category1 = allByParentId.get(parentCid);
        if (category1 == null) {
            return null;
        }
        //构造node
        for (int i = 0; i < category1.size(); i++) {
            Category e1 = category1.get(i);
            CategoryNodeDto nodeDto1 = copyCategory(e1);
            nodeDto1.setNextLevel(getCategory(e1.getCatId(), allByParentId));
            res.add(nodeDto1);
        }
        return res;
    }

    //复制对象
    private CategoryNodeDto copyCategory(Category category) {
        CategoryNodeDto res = new CategoryNodeDto();
        res.setName(category.getName());
//        res.setParentCid(category.getParentCid());
//        res.setCatLevel(category.getCatLevel());
        res.setSort(category.getSort());
//        res.setIcon(category.getIcon());
        return res;
    }

    public Object testRedis() {
        return redisTemplate.opsForValue().get("test");
    }
}
