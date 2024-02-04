package com.sse.homeworkSubmit.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object data;
    Integer code;
    String msg;

    public static Result createOk(Object data) {
        return new Result(data, 0, "ok");
    }

    public static Result createOk() {
        return Result.createOk(null);
    }
}
