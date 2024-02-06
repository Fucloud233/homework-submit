package com.sse.homeworkSubmit.controller;

import com.sse.homeworkSubmit.mapper.StudentMapper;
import com.sse.homeworkSubmit.pojo.Result;
import com.sse.homeworkSubmit.pojo.Student;
import com.sse.homeworkSubmit.service.student.StudentService;
import com.sse.homeworkSubmit.utils.error.SystemError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student")
    public Result query(Integer id, String name, Short gender, Integer grade,
        Integer pageNum, Integer pageSize) {
        Student stu = new Student(id, name, grade, gender);

        Object result = studentService.query(stu, pageNum, pageSize);

        return Result.createOk(result);
    }

    @PostMapping("/student")
    public Result add(@RequestBody Student student) {
        try {
            studentService.add(student);
        } catch (SystemError e) {
            return Result.createErr(e.getKind());
        }

        return Result.createOk();
    }
}
