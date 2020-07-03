package com.z.dao;

import com.z.bean.PO.NewsPo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {

    @Insert("insert into t_news(title,content) values(#{title},#{content})")
    public int add(NewsPo news);

    @Update("update t_news set title=#{title},content=#{content} where id=#{id}")
    public int update(NewsPo news);

    @Delete("delete from t_news where id = #{id}")
    public int delete(int id);

    @Select("select * from t_news where id = #{id}")
    public NewsPo get(int id);

    @Select("select * from t_news")
    public List<NewsPo> list();

}
