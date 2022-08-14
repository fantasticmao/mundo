package cn.fantasticmao.mundo.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.concurrent.Immutable;

/**
 * The JSON response for RESTFul APIs.
 * <p>
 * usages:
 * <ol>
 *     <li>{@code return JsonApi.ok(data)}</li>
 *     <li>{@code return JsonApi.status(HttpStatus.NOT_FOUND)}</li>
 * </ol>
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/19
 * @deprecated use {@link ResponseEntity} instead
 */
@Getter
@Immutable
@Deprecated
public final class JsonApi<T> {
    private final boolean status;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private JsonApi() {
        this(HttpStatus.OK, null);
    }

    private JsonApi(HttpStatus httpStatus, T data) {
        this.status = httpStatus.is2xxSuccessful();
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public static <T> JsonApi<T> ok(T data) {
        return new JsonApi<>(HttpStatus.OK, data);
    }

    public static <T> JsonApi<T> status(HttpStatus httpStatus) {
        return new JsonApi<>(httpStatus, null);
    }

    public static <T> JsonApi<T> status(HttpStatus httpStatus, T data) {
        return new JsonApi<>(httpStatus, data);
    }

    public ResponseEntity<JsonApi<T>> toResponseEntity() {
        return ResponseEntity.status(this.code).body(this);
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
}
