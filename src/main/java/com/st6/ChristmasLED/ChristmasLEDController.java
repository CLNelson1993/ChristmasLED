package com.st6.ChristmasLED;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChristmasLEDController {

    //this definitely works. Tested by running on Windows 10 and doing localhost:8080/test/100.

    //going to localhost:8080/test/{var} should result in this.
    //we are passing a variable through the path mapping. typing localhost:8080/test/100 will give you "resourceId = 100"
    @GetMapping("test/{var}")
    public String getString(@PathVariable String var) {
        return "Variable test: var = " + var;
    }
}
