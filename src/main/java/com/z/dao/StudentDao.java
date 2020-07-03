package com.z.dao;

import com.z.bean.PO.StudentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    public List<StudentPO> getAll(int page, int size);
}
