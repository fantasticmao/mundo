package cn.fantasticmao.mundo.web.support;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;

/**
 * JsonApi
 * <p>
 * usages:
 * <ol>
 *     <li>{@code return JsonApi.success()}</li>
 *     <li>{@code return JsonApi.error(HttpStatus.NOT_FOUND)}</li>
 * </ol>
 *
 * @author MaoMao
 * @version 1.0
 * @since 2017/3/19
 */
@Immutable
public final class JsonApi<T> implements Serializable {
    private static final long serialVersionUID = 6126929533373437316L;

    private final boolean status;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private JsonApi() {
        throw new AssertionError("No JsonApi instances for you!");
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
