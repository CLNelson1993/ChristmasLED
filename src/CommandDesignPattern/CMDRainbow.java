package CommandDesignPattern;

public class CMDRainbow implements Command {
    private Receiver receiver;

    public CMDRainbow(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdRainbow();
    }
}