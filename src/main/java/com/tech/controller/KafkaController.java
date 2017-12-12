package com.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ouhiroshi on 2017/12/7.
 */
@Controller
@RequestMapping("v1/kafka")
@ResponseBody
public class KafkaController {

//    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(name = "msg") String msg) {
//        kafkaTemplate.send("my-replicated-topic", msg);
//        String url = "http://linux-1:8086/write?db=fintech";
//
//        String pointValue = "fintech_test_table,host=server02,num=0.67 value=2.0 " + System.currentTimeMillis() + "000000";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        restTemplate.postForObject(url, pointValue, Object.class);


        return "greeting";
    }
}
