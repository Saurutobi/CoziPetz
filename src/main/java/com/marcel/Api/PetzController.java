package com.marcel.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetzController {

    @GetMapping("/helloWorld")
    String helloWorld() {
return "you got me";
    }

}
