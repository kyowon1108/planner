package com.example.planner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @GetMapping("/")
    public String index() {
        return "index";
        // 컨트롤러에서 무자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨. (index.mustache)
    }
    
}
