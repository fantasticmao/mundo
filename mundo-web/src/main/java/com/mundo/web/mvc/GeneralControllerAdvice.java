package com.mundo.web.mvc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * GeneralController
 *
 * @author maodh
 * @since 16/01/2018
 */
@ControllerAdvice
public class GeneralControllerAdvice {

    @InitBinder
    public void formatDate() {
        // TODO format date string
    }
}
