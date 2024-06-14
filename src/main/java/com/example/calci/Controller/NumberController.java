package com.example.calci.Controller;

import com.example.calci.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.calci.Service.*;
@RestController
@RequestMapping("/numbers")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/{numberId}")
    public NumberResponse getNumbers(@PathVariable String numberId) {
        return numberService.processRequest(numberId);
    }
}

