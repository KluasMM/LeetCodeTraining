package com.mt.DoublePointer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Klaus on 2020/3/7.
 */
@RestController
public class Test1 {

    @RequestMapping("/test1")
    public String test1(){
        String a = "123";
        String text = "branch1";
        System.out.println(a);
        return a;
    }
}
