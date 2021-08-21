package com.z.dao;

import com.z.bean.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    /**
     * 根据id或层级查询
     * @return
     */
    public List<Category> getCategory(@Param("catId") Long catId,
                                      @Param("catLevel") Long catLevel,
                                      @Param("parentCid") Long parentCid);

}
