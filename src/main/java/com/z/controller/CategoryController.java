package com.z.controller;

import com.z.bean.dto.CategoryNodeDto;
import com.z.bean.jsonres.JsonResult;
import com.z.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取三级分类
     */
    @GetMapping("three-level-category")
    public JsonResult getThreeLevelCategory() throws InterruptedException {
        List<CategoryNodeDto> node = categoryService.getThreeLevelCategory();
        return JsonResult.success(node);
    }

    /**
     * redis test
     */
    @GetMapping("redis-test")
    public JsonResult testRedis() {
        Object res = categoryService.testRedis();
        return JsonResult.success(res);
    }
}
