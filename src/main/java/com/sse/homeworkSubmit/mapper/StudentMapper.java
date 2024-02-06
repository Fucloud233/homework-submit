package com.sse.homeworkSubmit.mapper;

import com.sse.homeworkSubmit.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    void insert(Student student);
    void insertInBatch(List<Student> students);

    void update(Student student);


    List<Student> list();

    // 以下这两条配合使用，query用于分页，count用于计数
    List<Student> query(Student student, Integer pageNum, Integer pageSize);

    Integer count(@Param("student") Student student);

    Student getById(Integer id);
}
