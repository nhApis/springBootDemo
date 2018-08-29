package com.nhapis.argus.er.message.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by nhApis on 2018/07/25.
 */
@Getter
@Setter
public class Result {
    private static final String NULL_MESSAGE = null;
    private static final String STATUS = "success";
    private static final String ERRORCODE = null;
    //状态
    private String status;

    //数据
    private Object content;

    //消息
    private String message;

    //错误码
    private String errorCode;

    //构造方法
    private Result(String status, String message, String errorCode, Object data) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.content = data;
    }

    //无数据的成功结果
    public static Result success() {
        return new Result(STATUS, NULL_MESSAGE, ERRORCODE, null);
    }

    //有数据的成功结果
    public static Result success(Object data) {
        return new Result(STATUS, NULL_MESSAGE, ERRORCODE, data);
    }

    //无消息的失败结果
    public static Result failure() {
        return new Result(STATUS, NULL_MESSAGE, ERRORCODE, null);
    }

    //有消息的失败结果
    public static Result failure(String message) {
        return new Result(STATUS, message, ERRORCODE, null);
    }

    //无消息的内部异常结果
    public static Result internalError() {
        return new Result(STATUS, NULL_MESSAGE, ERRORCODE, null);
    }

    //有消息的内部异常结果
    public static Result internalError(String message) {
        return new Result(STATUS, message, ERRORCODE, null);
    }

    //自定义构建结果
    public static Result build(String status, String message, String errorCode, Object data) {
        return new Result(status, message, errorCode, data);
    }

    //设置和获取成员变量的方法
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object data) {
        this.content = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    //json与数据转化核心类
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //没有数据的转化
    public static Result format(String json) {
        try {
            return MAPPER.readValue(json, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //数据是对象的转化
    public static Result formatToPojo(String json, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(json, Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode data = jsonNode.get("content");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").asText(), jsonNode.get("message").asText(), jsonNode.get("errorCode").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //数据是集合的转化
    public static Result formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("content");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").asText(), jsonNode.get("message").asText(), jsonNode.get("errorCode").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}