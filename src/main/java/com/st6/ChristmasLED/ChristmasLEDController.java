package com.st6.ChristmasLED;

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.rpiws281x.WS281x;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChristmasLEDController {
    //Initialize LED Strip
    private LedDriverInterface ledDriver;
    private int gpioNum = 18;
    private int pixelNum = 100;
    private int brightness = 255;

    @RequestMapping("/on")
    @ResponseBody
    public String on() {
        //update ledDriver
        ledDriver = new WS281x(gpioNum, brightness, pixelNum);
		//this will serve as the static command in the future. For now it's a basic button that makes a blue light.
        int rVal = 0;
        int bVal = 255;
        int gVal = 0;

        //code for anim
        for (int i = 0; i < ledDriver.getNumPixels(); i++) {
            ledDriver.setPixelColourRGB(i, rVal, bVal, gVal); //note how it's actually RBG, not RGB.
        }
        //render
        ledDriver.render();
        return "LED on";
    }
    @RequestMapping("/off")
    @ResponseBody
    public String off() {
        //nice and clean one-liner
        ledDriver.allOff();
        return "LED off";
    }
}
