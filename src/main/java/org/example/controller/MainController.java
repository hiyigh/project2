package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.Impl.ItemServImpl;
import org.example.service.Impl.UserServImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String Home(@AuthenticationPrincipal Principal principal) {
        return "/home";
    }
}
