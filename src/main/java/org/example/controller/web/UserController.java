package org.example.controller.web;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value="/login", method={RequestMethod.GET})
    public String loginForm(HttpServletRequest request) {
        return "/login";
    }
    @GetMapping("/join")
    public String join() {
        return "/member/join";
    }
    @GetMapping("/joinSuccess")
    public String joinSuccess() {
        return "/member/joinSuccess";
    }
    @GetMapping("/findPassword")
    public String findPassword(){
        return "/member/findPassword";
    }
    @GetMapping("/findPasswordMsg")
    public String findPasswordMsg(){
        return "/member/findPasswordMsg";
    }
}
