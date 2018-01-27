package com.mundo.web.mvc;

import com.mundo.core.constant.Words;
import com.mundo.web.annotation.JsonpController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * JsonpControllerAdvice
 *
 * @author maodh
 * @since 16/01/2018
 */
@ControllerAdvice(annotations = JsonpController.class)
public class JsonpControllerAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpControllerAdvice() {
        super(Words.CALLBACK);
    }
}
