package com.z.controller;
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

        return "student/list";
    }

    @RequestMapping("/getall.ajax/{page}/{size}")
    @ResponseBody
    public List<StudentVO> getAllAjax(@PathVariable("page") int page, @PathVariable("size") int size , Model model) throws BusinessException {

        List<StudentVO> students= studentService.getAll(page,size);

        return students;
    }

    @RequestMapping("/test")
    @ResponseBody
    public void get() throws InterruptedException {
        Thread.sleep(100L);
        return;
    }

}
