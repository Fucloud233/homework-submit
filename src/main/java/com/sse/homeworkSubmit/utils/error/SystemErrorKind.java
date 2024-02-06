package com.sse.homeworkSubmit.utils.error;

import lombok.Getter;

@Getter
public enum SystemErrorKind {
    StudentExist(20001, "学生已存在"),
    InternalError(10000, "系统错误");

    final int code;
    final String message;

    SystemErrorKind(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public SystemError toError() {
        return new SystemError(this);
    }
}
