package CommandDesignPattern;

public class Invoker {
    public Command command;

    public Invoker(Command c){
        this.command=c;
    }

    public void execute(){
        this.command.execute();
    }
}
