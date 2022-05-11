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
    private final T data;

    private JsonApi() {
        this(true, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    private JsonApi(boolean status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonApi<T> success(T data) {
        return new JsonApi<>(true, HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> JsonApi<T> error(HttpStatus httpStatus) {
        return new JsonApi<>(false, httpStatus.value(), httpStatus.getReasonPhrase(), null);
    }

    public static <T> JsonApi<T> error(HttpStatus httpStatus, T data) {
        return new JsonApi<>(false, httpStatus.value(), httpStatus.getReasonPhrase(), data);
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

    // getter

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
