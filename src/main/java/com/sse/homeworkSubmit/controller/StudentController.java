package com.sse.homeworkSubmit.controller;

import com.sse.homeworkSubmit.mapper.StudentMapper;
import com.sse.homeworkSubmit.pojo.Result;
import com.sse.homeworkSubmit.pojo.Student;
import com.sse.homeworkSubmit.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student")
    public Result query(Integer id, String name, Short gender, Integer grade,
        Integer pageNum, Integer pageSize) {
        Student stu = new Student(id, name, grade, gender);

        System.out.println(stu);

        Object result = studentService.query(stu, pageNum, pageSize);

        return Result.createOk(result);
    }
}
