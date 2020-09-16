package com.z.mybatis;

import com.z.bean.PO.StudentPO;
import com.z.dao.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class StudentDaoTest {

    @Test
    public void findAll(){
        SqlSession sqlSession = getSessionFactory().openSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<StudentPO> list = dao.getAll(0,10);
        System.out.println(list);
    }

    private static SqlSessionFactory getSessionFactory(){
        SqlSessionFactory sqlSessionFactory = null;
        String resource = "mybatisConfig.xml";

        try{
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sqlSessionFactory;
    }

}
