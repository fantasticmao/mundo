package cn.fantasticmao.mundo.web.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * General controller advice for {@code Spring-Web-MVC}.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 16/01/2018
 */
@ControllerAdvice
public class GeneralControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, false, 4);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandler(RuntimeException e) {
        LOGGER.error("Global Exception Handler", e);
        return ResponseEntity.internalServerError().body("INTERNAL SERVER ERROR");
    }
}
