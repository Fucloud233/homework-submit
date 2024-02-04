package com.sse.homeworkSubmit.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private Integer Id;
    private String Name;
    private String Password;
    private Integer Grade;
    private Short Gender;

    public Student(Integer id, String name, Integer grade, Short gender) {
        this.Id = id;
        this.Name = name;
        this.Grade = grade;
        this.Gender = gender;
    }
}
