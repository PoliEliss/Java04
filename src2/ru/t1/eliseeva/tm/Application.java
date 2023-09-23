package ru.t1.eliseeva.tm;

import ru.t1.eliseeva.tm.constant.TerminalConst;
import ru.t1.eliseeva.tm.constant.ArgumentConst;

import java.util.Scanner;

import static ru.t1.eliseeva.tm.constant.TerminalConst.*;

public class Application {
    public static void main(final String[] args) {
        parseArguments(args);
        parseCommands();
    }

    private static void parseCommands() {
        showWelcome();
        final Scanner scanner = new Scanner(System.in);
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND");
            final String command = scanner.next();
            parseCommand(command);

        }
    }

    private static void parseArguments(final String[] args) {
        if (args == null || args.length == 0) return;
        final String arg = args[0];
        parseArgument(arg);
    }

    private static void parseCommand(final String command) {
        if (command == null || command.isEmpty()) return;
        switch (command) {
            case TerminalConst.VERSION:
                showVersion();
                break;
            case TerminalConst.HELP:
                showHelp();
                break;
            case TerminalConst.INFO:
                showDeveloper();
                break;
            case TerminalConst.EXIT:
                exit();
                break;
            default:
                showCommandError();
        }
    }

    private static void showCommandError() {
        System.err.println("[ERROR]");
        System.err.println("This command is not supported..");
    }

    private static void parseArgument(final String arg) {
        if (arg == null || arg.isEmpty()) return;
        switch (arg) {
            case ArgumentConst.VERSION:
                showVersion();
                break;
            case ArgumentConst.HELP:
                showHelp();
                break;
            case ArgumentConst.INFO:
                showDeveloper();
                break;
            default:
                showArgumentError();
        }
        System.exit(0);
    }

    private static void showArgumentError() {
        System.err.println("[ERROR]");
        System.err.println("This argument is not supported..");
        System.exit(1);
    }

    private static void showDeveloper() {
        System.out.println("Сведения о разработчике");

    }

    private static void showWelcome() {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
    }

    private static void exit() {
        System.exit(0);

    }

    private static void showVersion() {
        System.out.println("VERSION:1899876");
    }

    private static void showHelp() {
        System.out.println("Help");
    }
}
