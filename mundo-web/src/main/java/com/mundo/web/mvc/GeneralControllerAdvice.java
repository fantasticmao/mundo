package com.mundo.web.mvc;

import com.mundo.web.support.JsonApi;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GeneralController
 *
 * @author maodh
 * @since 16/01/2018
 */
@ControllerAdvice
public class GeneralControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, false, 4);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonApi exceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return JsonApi.error(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
