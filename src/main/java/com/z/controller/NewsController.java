package com.z.controller;

import com.z.bean.PO.NewsPo;
import com.z.bean.jsonres.GlobalReturnCode;
import com.z.bean.jsonres.JsonResult;
import com.z.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("add")
    public JsonResult add(@RequestBody NewsPo news){
        int flag = newsService.add(news);
        if(flag==1){
            return new JsonResult(true, GlobalReturnCode.SAVE_SUCCESS);
        }else {
            return new JsonResult(false,GlobalReturnCode.OPERA_FAILURE);
        }
    };

    @PostMapping("update")
    public JsonResult update(@RequestBody NewsPo news){
        int flag = newsService.update(news);
        if (flag == 1) {
            return new JsonResult(true, GlobalReturnCode.SAVE_SUCCESS);
        } else {
            return new JsonResult(false, GlobalReturnCode.OPERA_FAILURE);
        }
    };

    @PostMapping("delete")
    public JsonResult delete(@RequestBody Map<String, String> map){
        int flag = newsService.delete(Integer.parseInt(map.get("id")));
        if (flag == 1) {
            return new JsonResult(true, GlobalReturnCode.SAVE_SUCCESS);
        } else {
            return new JsonResult(false, GlobalReturnCode.OPERA_FAILURE);
        }
    };

    @PostMapping("get")
    public JsonResult get(@RequestBody Map<String, String> map){
        NewsPo news = newsService.get(Integer.parseInt(map.get("id")));
        return new JsonResult(true, GlobalReturnCode.OPERA_SUCCESS, news);
    };

    @PostMapping("list")
    public JsonResult list(){
        List<NewsPo> list = newsService.list();
        return new JsonResult(true, GlobalReturnCode.OPERA_SUCCESS, list);
    };

}
