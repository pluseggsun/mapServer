package com.bootdo.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ReturnType
 * 操作结果
 *
 * @author system
 * @date 2018.09.26
 */
public class ReturnType extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ReturnType() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static ReturnType error() {
        return error(1, "操作失败");
    }

    public static ReturnType error(String msg) {
        return error(500, msg);
    }

    public static ReturnType error(int code, String msg) {
        ReturnType returnType = new ReturnType();
        returnType.put("code", code);
        returnType.put("msg", msg);
        return returnType;
    }

    public static ReturnType ok(String msg) {
        ReturnType returnType = new ReturnType();
        returnType.put("msg", msg);
        return returnType;
    }

    public static ReturnType ok(Map<String, Object> map) {
        ReturnType returnType = new ReturnType();
        returnType.putAll(map);
        return returnType;
    }

    public static ReturnType ok() {
        return new ReturnType();
    }

    @Override
    public ReturnType put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
