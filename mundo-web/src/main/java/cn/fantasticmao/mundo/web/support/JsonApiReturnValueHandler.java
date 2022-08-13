package cn.fantasticmao.mundo.web.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * JsonApiReturnValueHandler
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-14
 */
public class JsonApiReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(@Nonnull MethodParameter returnType) {
        return returnType.getParameterType().isAssignableFrom(JsonApi.class);
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, @Nonnull MethodParameter returnType,
                                  @Nonnull ModelAndViewContainer mavContainer,
                                  @Nonnull NativeWebRequest webRequest) {
        if (!(returnValue instanceof JsonApi)) {
            return;
        }
        JsonApi<?> jsonApi = (JsonApi<?>) returnValue;
        mavContainer.setStatus(jsonApi.toHttpStatus());
    }
}
