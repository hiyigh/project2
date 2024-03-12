package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String Home() {
        return "/home";
    }
    @GetMapping("/shop")
    public String Shop() {
        return "/shop/itemList";
    }
}
