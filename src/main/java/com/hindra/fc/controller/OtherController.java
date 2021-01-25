package com.hindra.fc.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OtherController {
    
    private String name;
    private Integer age;

    @GetMapping(value="other")
    public String getOther(
        @RequestParam(required = false, name = "name") Optional<String> name,
        @RequestParam(required = false, name = "age") Optional<Integer> age) {
        if(name.isPresent()) {
            this.name = name.get();
        }
        else {
            this.name = "`No Name`";
        }

        if(age.isPresent()) {
            this.age = age.get();
        }
        else {
            this.age = 0;
        }
        
        return "Hello! My name is " + this.name + " and i am " + this.age + " years old. Nice to meet you :)";
    }
    
}
