package ru.t1.eliseeva.tm;

import ru.t1.eliseeva.tm.constant.TerminalConst;

import static ru.t1.eliseeva.tm.constant.TerminalConst.*;

public class Application {
    public static void main(String[] args) {
        parseArguments(args);
    }
    private static void parseArguments(final String[] args){
        if (args == null || args.length == 0) showWelcome();
        final String arg = args[0];
        parseArgument(arg);
    }
    private static void parseArgument(final String arg) {
        if (arg == null || arg.isEmpty()) return;
        switch (arg) {
            case VERSION:
                showVersion();
                break;
            case HELP:
                showHelp();
                break;
            case INFO:
                showDeveloper();
                break;
            default:
                showWelcome();
        }
    }

    private static void showDeveloper() {
        System.out.println("Сведения о разработчике");

    }

    private static void showWelcome() {
        System.out.println("Привет!");
        System.exit(0);
    }

    private static void showVersion() {
        System.out.println("VERSION:1899876");
    }

    private static void showHelp() {
        System.out.println("Help");
    }
}
