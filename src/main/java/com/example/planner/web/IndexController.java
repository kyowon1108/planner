package com.example.planner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.planner.service.PostsService;

@RequiredArgsConstructor
@Controller
public class IndexController {
    
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }
    
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
    

}
