package CommandDesignPattern;

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