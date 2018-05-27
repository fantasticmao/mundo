package com.mundo.web.controller;

import com.mundo.web.annotation.JsonpController;
import com.mundo.web.support.JsonApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author maodh
 * @since 16/01/2018
 */
@RestController
@JsonpController
public class TestController {

    @RequestMapping(value = "/jsonp", method = RequestMethod.GET)
    @ResponseBody
    public JsonApi test() {
        return JsonApi.success();
    }
}
