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





    @RequestMapping("/rainbowd")
    @ResponseBody
    public String rainbow() {
        rainbowInvoker.execute();
        return "rainbow";
    }
    @RequestMapping("/rainbowc")
    @ResponseBody
    public String rainbowCycle() {
        rainbowCycleInvoker.execute();
        return "rainbowCycle";
    }
    @RequestMapping("/wipe")
    @ResponseBody
    public String wipe() {
        wipeInvoker.execute();
        return "wipe";
    }
    @RequestMapping("/cmdstatic")
    @ResponseBody
    public String cmdstatic() {
        staticInvoker.execute();
        return "static";
    }
    @RequestMapping("/strobe")
    @ResponseBody
    public String strobe() {
        strobeInvoker.execute();
        return "strobe";
    }
}
