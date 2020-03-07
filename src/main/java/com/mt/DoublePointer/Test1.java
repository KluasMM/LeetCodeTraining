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
        int a1 = 1;
        int a2 = 2;
        System.out.println(a1+a2);
        String text2 = "branch2";
        String abc = "abc";
        System.out.println(a);
        return a;
    }
}
