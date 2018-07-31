package com.mundo.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mundo.core.support.Constant;
import com.mundo.core.util.JsonUtil;
import com.mundo.core.util.ObjectUtil;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * JsonApi
 * <pre>
 * 成功：<code>JsonApi.success()</code>
 * 失败：<code>JsonApi.error(HttpStatus.NOT_FOUND)</code>
 * </pre>
 *
 * @author MaoMao
 * @since 2017/3/19
 */
public final class JsonApi implements Serializable {
    private static final long serialVersionUID = 6126929533373437316L;

    private boolean status;
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    private JsonApi() {
        // 默认构造器，为反射提供
    }

    private JsonApi(boolean status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static JsonApi success() {
        return new JsonApi(true, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static JsonApi error(HttpStatus httpStatus) {
        return new JsonApi(false, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public JsonApi message(String message) {
        if (message != null) {
            this.message = message;
        }
        return this;
    }

    public JsonApi data(Object data) {
        if (data != null) {
            this.data = data;
        }
        return this;
    }

    public String toJson() {
        return ObjectUtil.defaultIfNull(JsonUtil.toJson(this), Constant.Strings.EMPTY);
    }

    public JsonApi fromJson(String json) {
        return JsonUtil.toClass(json, JsonApi.class);
    }

    @Override
    public String toString() {
        return toJson();
    }

    // getter and setter

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
