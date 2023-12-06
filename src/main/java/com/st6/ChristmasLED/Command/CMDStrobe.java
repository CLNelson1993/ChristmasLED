package com.st6.ChristmasLED.Command;

public class CMDStrobe implements Command {
    private Receiver receiver;

    public CMDStrobe(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdStrobe();
    }
}