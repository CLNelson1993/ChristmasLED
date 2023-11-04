package CommandDesignPattern;

public class CMDStatic implements Command {
    private Receiver receiver;

    public CMDStatic(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdStatic();
    }
}