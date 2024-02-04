package com.sse.homeworkSubmit.service.student.implement;

import com.sse.homeworkSubmit.mapper.StudentMapper;
import com.sse.homeworkSubmit.pojo.Student;
import com.sse.homeworkSubmit.service.student.StudentService;
import com.sse.homeworkSubmit.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceA implements StudentService {
    @Autowired
    StudentMapper  studentMapper;

    static Integer defaultPageNum = 1;
    static Integer defaultPageSize = 10;


    @Override
    public Map<String, Object> query(Student student, Integer pageNum, Integer pageSize) {
        // 验证分页参数
        Pair<Integer, Integer> pageParam = wrapPageParam(pageNum, pageSize);
        pageNum = pageParam.getFirst();
        pageSize = pageParam.getSecond();

        List<Student> students =  studentMapper.query(student, pageNum, pageSize);
        Integer count = studentMapper.count(student);

        System.out.println("stu: " + students.getFirst());

        Map<String, Object> data = new HashMap<>();
        data.put("info", students);
        data.put("length", count);

        return data;
    }

    @Override
    public void add(Student student) {

    }

    private static Pair<Integer, Integer> wrapPageParam(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageNum < 1) {
            pageNum = defaultPageNum;
        }
        if(pageSize == null || pageSize < 1) {
            pageSize = defaultPageSize;
        }

        pageNum = (pageNum - 1) * pageSize;

        return new Pair<> (pageNum, pageSize);
    }


}
