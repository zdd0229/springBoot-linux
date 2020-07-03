package com.z.service;

import com.z.bean.PO.NewsPo;
import com.z.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    public int add(NewsPo news){
        return newsDao.add(news);
    };

    public int update(NewsPo news){
        return newsDao.update(news);
    };

    public int delete(int id){
        return newsDao.delete(id);
    };

    public NewsPo get(int id){
        return newsDao.get(id);
    };

    public List<NewsPo> list(){
        return newsDao.list();
    };

}
