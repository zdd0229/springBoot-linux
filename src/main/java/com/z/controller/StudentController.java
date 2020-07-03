package com.z.controller;
import java.util.Date;
import com.z.bean.enumm.Gender;

import com.z.bean.VO.StudentVO;
import com.z.exception.BusinessException;
import com.z.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/getall/{page}/{size}")
    public String getAll(@PathVariable("page") int page, @PathVariable("size") int size , Model model) throws BusinessException {

        List<StudentVO> students= studentService.getAll(page,size);
        model.addAttribute("students",students);

        if(students.size()<10){
            throw new BusinessException("10","数量太少");
        }

        return "student/list";
    }

    @RequestMapping("/getall.ajax/{page}/{size}")
    @ResponseBody
    public List<StudentVO> getAllAjax(@PathVariable("page") int page, @PathVariable("size") int size , Model model) throws BusinessException {

        List<StudentVO> students= studentService.getAll(page,size);

        if(students.size()<10){
            throw new BusinessException("10","数量太少了");
        }

        return students;
    }

}
