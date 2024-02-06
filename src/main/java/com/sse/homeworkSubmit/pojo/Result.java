package com.sse.homeworkSubmit.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sse.homeworkSubmit.utils.error.SystemErrorKind;
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

    public static Result createErr(String msg) {
        return new Result(null, 1, msg);
    }

    public static Result createErr(SystemErrorKind kind) {
        return new Result(null, kind.getCode(), kind.getMessage());
    }
}
