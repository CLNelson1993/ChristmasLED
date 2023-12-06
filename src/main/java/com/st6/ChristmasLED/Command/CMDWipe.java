package com.st6.ChristmasLED.Command;

public class CMDWipe implements Command {
    private Receiver receiver;

    public CMDWipe(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdWipe();
    }
}