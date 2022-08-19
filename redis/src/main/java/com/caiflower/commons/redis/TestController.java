//package com.caiflower.commons.redis;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author: lijinlong
// * @date: 2022/8/19 13:55
// */
//@RestController
//public class TestController {
//
//    @Resource
//    private CacheProvider redis;
//
//    @GetMapping("/test")
//    public String test() {
//        redis.set("123", "123");
//        return "test";
//    }
//}
