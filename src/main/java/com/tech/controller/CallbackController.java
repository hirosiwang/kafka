package com.tech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.stream.Collectors;


/**
 * Created by ouhiroshi on 2017/12/11.
 */
@Controller
@RequestMapping("v1/callback")
@ResponseBody
public class CallbackController {

    private static Logger log = LoggerFactory.getLogger(CallbackController.class);

    @RequestMapping(value = "/c2b", method = RequestMethod.GET)
    public String test(HttpServletRequest request) {
        String headers = Collections.list(request.getHeaderNames()).stream().map(headerName -> headerName + ":" + request.getHeader(headerName)).collect(Collectors.joining(", "));
        log.info(headers);
        request.getParameterMap().forEach((k, v) ->{
            log.info("param : " + k + " value : " + request.getParameter(k));
        });

        return "done";
    }
}
