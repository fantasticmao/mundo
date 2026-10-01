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
 * @param status  {@code true} when {@code code} is a 2xx status
 * @param code    HTTP status code
 * @param message HTTP reason phrase
 * @param data    response payload, omitted from JSON when {@code null}
 * @param <T>     payload type
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

    /**
     * Creates a successful response that carries {@code data}.
     *
     * @param data payload
     * @param <T>  payload type
     * @return response with HTTP 200
     */
    public static <T> JsonApi<T> ok(T data) {
        return new JsonApi<>(HttpStatus.OK, data);
    }

    /**
     * Creates a response for the given status, without a payload.
     *
     * @param httpStatus HTTP status
     * @param <T>        payload type
     * @return response whose {@code code} is {@code httpStatus}
     */
    public static <T> JsonApi<T> status(HttpStatus httpStatus) {
        return new JsonApi<>(httpStatus, null);
    }

    /**
     * Creates a response for the given status that carries {@code data}.
     *
     * @param httpStatus HTTP status
     * @param data       payload
     * @param <T>        payload type
     * @return response whose {@code code} is {@code httpStatus}
     */
    public static <T> JsonApi<T> status(HttpStatus httpStatus, T data) {
        return new JsonApi<>(httpStatus, data);
    }

    /**
     * Wraps this response in a {@link ResponseEntity} whose status is {@link #code}.
     *
     * @return response entity
     */
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
