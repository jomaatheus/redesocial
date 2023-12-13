package com.example.redesocial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping("/RedeSocial")
    public String RedeSocial() {

        return "RedeSocial";
    }

    @GetMapping("/entrar")
    public String entrar() {
        return "entrar";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

}