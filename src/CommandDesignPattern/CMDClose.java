package CommandDesignPattern;

public class CMDClose implements Command {
    private Receiver receiver;

    public CMDClose(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdClose();
    }
}