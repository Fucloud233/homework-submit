package com.sse.homeworkSubmit.service.student;

import com.sse.homeworkSubmit.pojo.Student;
import com.sse.homeworkSubmit.utils.error.SystemError;

import java.util.List;
import java.util.Map;

public interface StudentService {
    /** 查询用户信息
     *
     * @param student
     * @param pageNum
     * @param pageSize
     * @return 返回类型包括两个字段，info: 列表信息, length: 实际的总长度
     */
    Map<String, Object> query(Student student, Integer pageNum, Integer pageSize);

    void add(Student student) throws SystemError;

    void addInBatch(List<Student> student) throws Exception;
}
