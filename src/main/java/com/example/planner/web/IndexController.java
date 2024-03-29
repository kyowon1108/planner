package com.example.planner.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.planner.config.auth.LoginUser;
import com.example.planner.config.auth.dto.SessionUser;
import com.example.planner.service.PostsService;
import com.example.planner.web.dto.PostsResponseDto;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
    
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
    
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable(name = "id") Long postId, Model model) {
        PostsResponseDto dto = postsService.findById(postId);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
