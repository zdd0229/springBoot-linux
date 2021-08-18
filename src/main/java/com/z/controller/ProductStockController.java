package com.z.controller;

import com.z.bean.jsonres.JsonResult;
import com.z.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("stock")
public class ProductStockController {

    @Autowired
    private ProductStockService stockService;

    @PostMapping("deduct")
    public JsonResult deductStock(@RequestBody Map req) {

        return new JsonResult(stockService.deductStock((Integer) req.get("pid"), (Integer) req.get("num")));

    }

}
