package com.endockin.patron.resource;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    private static final String JSONP_CALLBACK_FUNCTION_NAME = "callback";

    public JsonpAdvice() {
        super(JSONP_CALLBACK_FUNCTION_NAME);
    }
}
