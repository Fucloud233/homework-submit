package com.sse.homeworkSubmit.service.student.implement;

import com.sse.homeworkSubmit.mapper.StudentMapper;
import com.sse.homeworkSubmit.pojo.Student;
import com.sse.homeworkSubmit.service.student.StudentService;
import com.sse.homeworkSubmit.utils.Pair;
import com.sse.homeworkSubmit.utils.PinyinConverter;
import com.sse.homeworkSubmit.utils.error.SystemError;
import com.sse.homeworkSubmit.utils.error.SystemErrorKind;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
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

        Map<String, Object> data = new HashMap<>();
        data.put("info", students);
        data.put("length", count);

        return data;
    }

    @Override
    public void add(Student student) throws SystemError {
        if(studentMapper.getById(student.getId()) != null) {
            throw SystemErrorKind.StudentExist.toError();
        }

        // 如果传入的密码为空，则会自动生成
        String password = student.getPassword();
        if(password == null || password.isEmpty()) {
            try {
                student.setPassword(StudentServiceA.generatePassword(student));
            } catch (Exception e) {
                throw SystemErrorKind.InternalError.toError();
            }
        }

        studentMapper.insert(student);
    }

    @Override
    public void addInBatch(List<Student> student) throws Exception {

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


    /** 根据学生信息自动生成密码
     * 生成规则：姓名拼音首字母 + 学号
     * @param student
     * @return
     * @throws InvalidPropertiesFormatException
     */
    private static String generatePassword(Student student) throws InvalidPropertiesFormatException {
        System.out.println(student);

        if(student.getName() == null || student.getName().isEmpty()
            || student.getId() == null ) {
            throw new InvalidPropertiesFormatException("The name of id of student is null.");
        }

        return PinyinConverter.getInitialLetter(student.getName()) + student.getId();
    }

}
