package CommandDesignPattern;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
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



        // Scanner for testing purposes. This probably will be useless later. Too bad!
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("ChristmasFlair WS281x App");
        System.out.println("RomSimpson and CLNelson1993");
        System.out.println("");

        while (true) {
            //prompt
            System.out.println("");
            System.out.println("Enter a number to choose an animation.");
            System.out.println("1. Static (set color and brightness)");
            System.out.println("2. Rainbow");
            System.out.println("3. Color Wipe");
            System.out.println("4. Rainbow Cycle");
            System.out.println("5. Strobe");
            System.out.println("0. Close program");

            //scan for choice (integer)
            int userInput = scanner.nextInt();

            //choices
            switch (userInput) {
                case 1:
                    //execute cmdStatic()
                    staticInvoker.execute();
                    break;
                case 2:
                    //execute cmdRainbow()
                    rainbowInvoker.execute();
                    break;
                case 3:
                    wipeInvoker.execute();
                    break;
                case 4:
                    rainbowCycleInvoker.execute();
                    break;
                case 5:
                    strobeInvoker.execute();
                    break;
                case 0:
                    //execute cmdClose()
                    closeInvoker.execute();
                    scanner.close();
                    System.exit(0);
                default:
                    //invalid option
                    System.out.println("That's not an option.");
            }
        }
    }
}