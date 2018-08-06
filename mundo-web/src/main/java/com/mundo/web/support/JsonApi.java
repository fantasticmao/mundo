package com.mundo.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mundo.core.util.JsonUtil;
import org.springframework.http.HttpStatus;

import javax.annotation.concurrent.Immutable;
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
@Immutable
public final class JsonApi implements Serializable {
    private static final long serialVersionUID = 6126929533373437316L;

    private final boolean status;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    private JsonApi() {
        throw new AssertionError("No com.mundo.web.support.JsonApi instances for you!");
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

    public JsonApi data(Object data) {
        if (this.data == null) {
            this.data = data;
            return this;
        } else {
            return new JsonApi(this.status, this.code, this.message).data(data);
        }
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return toJson();
    }

    // getter and setter

    public boolean isStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
