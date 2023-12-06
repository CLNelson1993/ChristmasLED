package com.st6.ChristmasLED.Command;

public class CMDRainbowCycle implements Command {
    private Receiver receiver;

    public CMDRainbowCycle(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdRainbowCycle();
    }
}