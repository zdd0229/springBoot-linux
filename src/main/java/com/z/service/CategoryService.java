package com.z.service;

import com.z.bean.entity.Category;
import com.z.controller.CategoryNodeDto;
import com.z.dao.CategoryMapper;
import com.z.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String CATEGORY = "three-level-category";

    /**
     * 查询三级分类
     *
     * @return
     */
    public List<CategoryNodeDto> getThreeLevelCategory() {

        Object o = redisTemplate.opsForValue().get(CATEGORY);
        if (!Objects.isNull(o)) {
            return JsonUtil.jsonToList((String) o, CategoryNodeDto.class);
        }

        List<Category> all = categoryMapper.getCategory(null, null, null);
        Map<Long, List<Category>> allByParentId = all.stream().collect(Collectors.groupingBy(Category::getParentCid));

        List<CategoryNodeDto> category = getCategory(0L, allByParentId);
        //写入缓存
        redisTemplate.opsForValue().set(CATEGORY, JsonUtil.toStr(category),1, TimeUnit.DAYS);

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
        res.setParentCid(category.getParentCid());
        res.setCatLevel(category.getCatLevel());
        res.setSort(category.getSort());
        res.setIcon(category.getIcon());
        return res;
    }

    public Object testRedis() {
        return redisTemplate.opsForValue().get("test");
    }
}
