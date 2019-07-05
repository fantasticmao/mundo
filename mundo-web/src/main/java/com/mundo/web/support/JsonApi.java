package com.mundo.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mundo.core.util.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public final class JsonApi<T> implements Serializable {
    private static final long serialVersionUID = 6126929533373437316L;

    @ApiModelProperty(example = "true", required = true)
    private final boolean status;
    @ApiModelProperty(example = "200", required = true)
    private final int code;
    @ApiModelProperty(example = "OK", required = true)
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty
    private T data;

    private JsonApi() {
        throw new AssertionError("No com.mundo.web.support.JsonApi instances for you!");
    }

    private JsonApi(boolean status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static <T> JsonApi<T> success() {
        return new JsonApi<>(true, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static <T> JsonApi<T> error(HttpStatus httpStatus) {
        return new JsonApi<>(false, httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public JsonApi<T> data(T data) {
        if (this.data == null) {
            this.data = data;
            return this;
        } else {
            return new JsonApi<T>(this.status, this.code, this.message).data(data);
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

    public T getData() {
        return data;
    }
}
