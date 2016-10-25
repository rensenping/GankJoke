package com.rsp.gankjoke.api.exception;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeException extends IllegalStateException {
    public JokeException(String msg) {
        super(msg);
    }
}
