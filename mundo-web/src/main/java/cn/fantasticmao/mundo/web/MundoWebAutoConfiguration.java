package cn.fantasticmao.mundo.web;

import cn.fantasticmao.mundo.web.support.JsonApiReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * MundoWebAutoConfiguration
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2022-08-14
 */
public class MundoWebAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addReturnValueHandlers(@Nonnull List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new JsonApiReturnValueHandler());
    }
}
