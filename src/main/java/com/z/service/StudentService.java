package com.z.service;

import com.z.bean.PO.StudentPO;
import com.z.bean.VO.StudentVO;
import com.z.dao.StudentDao;
import com.z.util.RunTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<StudentVO> getAll(int page, int size) {

//        try {
//            Thread.sleep(600000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        List<StudentPO> list=studentDao.getAll(page,size);

        List<StudentVO>  vo=new ArrayList<>();

        for (StudentPO po:list){
            StudentVO studentVO=new StudentVO();
            BeanUtils.copyProperties(po,studentVO);
            vo.add(studentVO);
        }

        return vo;
    }
}
