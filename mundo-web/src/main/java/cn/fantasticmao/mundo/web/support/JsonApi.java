package cn.fantasticmao.mundo.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import javax.annotation.concurrent.Immutable;

/**
 * JsonApi
 * <p>
 * usages:
 * <ol>
 *     <li>{@code return JsonApi.success()}</li>
 *     <li>{@code return JsonApi.error(HttpStatus.NOT_FOUND)}</li>
 * </ol>
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/19
 */
@Immutable
public final class JsonApi<T> {
    private final boolean status;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public JsonApi() {
        this(true, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public JsonApi(boolean status, int code, String message) {
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

    @Override
    public String toString() {
        return "JsonApi{" +
            "status=" + status +
            ", code=" + code +
            ", message='" + message + '\'' +
            ", data=" + data +
            '}';
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
