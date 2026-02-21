package com.bancodebogota.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorWeb {

    // Redirige todas las rutas que no empiecen con /api/ al index.html de Angular.
    @GetMapping({ "/{path:[^\\.]*}", "/**/{path:[^\\.]*}" })
    public String forward() {
        return "forward:/index.html";
    }
}
