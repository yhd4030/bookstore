package com.bookstore.haid.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class ExecuteResult {
    private boolean success;
    private String msg;

    public ExecuteResult() {
    }

    public ExecuteResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
