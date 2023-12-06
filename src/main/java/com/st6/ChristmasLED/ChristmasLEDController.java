package com.st6.ChristmasLED;

//Command Design Pattern package
import com.st6.ChristmasLED.Command.*;

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.rpiws281x.WS281x;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChristmasLEDController {
    //This serves as the "Client" portion of our Command Design Pattern.

    //create receiver (you only need one of these for all commands)
    Receiver receiver = new Receiver();

    //create new command
    Command cmdClose = new CMDClose(receiver);
    //create invoker to pass the command
    Invoker closeInvoker = new Invoker(cmdClose);

    //create new command
    Command cmdRainbow = new CMDRainbow(receiver);
    //create invoker to pass the command
    Invoker rainbowInvoker = new Invoker(cmdRainbow);

    //create new command
    Command cmdStatic = new CMDStatic(receiver);
    //create invoker to pass the command
    Invoker staticInvoker = new Invoker(cmdStatic);

    //create new command
    Command cmdWipe = new CMDWipe(receiver);
    //create invoker to pass the command
    Invoker wipeInvoker = new Invoker(cmdWipe);

    //create new command
    Command cmdRainbowCycle = new CMDRainbowCycle(receiver);
    //create invoker to pass the command
    Invoker rainbowCycleInvoker = new Invoker(cmdRainbowCycle);

    //create new command
    Command cmdStrobe = new CMDStrobe(receiver);
    //create invoker to pass the command
    Invoker strobeInvoker = new Invoker(cmdStrobe);

    //Holy molly this is gonna get messy





    @RequestMapping("/on")
    @ResponseBody
    public String on() {
        rainbowInvoker.execute();
        return "LED on";
    }
    @RequestMapping("/off")
    @ResponseBody
    public String off() {
        //nice and clean one-liner
        closeInvoker.execute();
        return "LED off";
    }
}
