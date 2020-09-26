package com.joao.takayama.Carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class indexController {

    @GetMapping()
    @RequestMapping(method = RequestMethod.GET)
    private String hello(){
        return "Ola spring boot";
    }
}
