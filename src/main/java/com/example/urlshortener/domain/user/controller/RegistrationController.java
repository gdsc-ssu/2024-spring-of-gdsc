package com.example.urlshortener.domain.user.controller;

import com.example.urlshortener.domain.user.dto.UserDto;
import com.example.urlshortener.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/adduser")
    public String register(@ModelAttribute("user") UserDto user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String loginError(@ModelAttribute("user") UserDto userDto, BindingResult result) {
        if (result.hasErrors())
            return "add-user";

        userService.createUser(userDto);
        return "redirect:adduser?success";
    }
}
