package com.sse.homeworkSubmit.utils.error;

import lombok.Getter;

@Getter
public class SystemError extends Exception{
    private final int code;
    private final SystemErrorKind kind;

    public SystemError(SystemErrorKind kind) {
        super(kind.getMessage());
        this.code = kind.getCode();
        this.kind = kind;
    }
}
