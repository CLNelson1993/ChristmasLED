//Tested by running on Windows 10 and doing localhost:8080/test/100.
//this definitely works, so it's safe to say the Pi isn't properly set up for Spring-boot/tomcat9. 
package com.st6.ChristmasLED;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChristmasLEDController {
    //passing a variable through the path mapping. typing localhost:8080/test/100 will give you a white webpage that says "Variable test: var = 100"
    @GetMapping("test/{var}")
    public String getString(@PathVariable String var) {
        return "Variable test: var = " + var;
    }
}
