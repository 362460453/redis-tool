package cn.springcloud.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-05-17 3:05 PM
 * @since v8.0
 **/
@RestController
public class TestController {

    @GetMapping("/test/aa")
    public String aa(HttpServletRequest request){
       String ip = request.getHeader("X-Forwarded-For-doms");
       return ip;
    }
}
