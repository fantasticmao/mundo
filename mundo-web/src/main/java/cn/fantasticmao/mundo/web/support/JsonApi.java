package cn.fantasticmao.mundo.web.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
 * @since 2017-03-19
 */
public record JsonApi<T>(boolean status, int code, String message,
                         @JsonInclude(JsonInclude.Include.NON_NULL) T data) {
    private JsonApi() {
        this(HttpStatus.OK, null);
    }

    private JsonApi(HttpStatus httpStatus, T data) {
        this(httpStatus.is2xxSuccessful(), httpStatus.value(), httpStatus.getReasonPhrase(), data);
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
            "}";
    }
}
