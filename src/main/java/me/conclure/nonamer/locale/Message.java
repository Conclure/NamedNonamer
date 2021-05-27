package me.conclure.nonamer.locale;

import me.conclure.nonamer.command.sender.CommandSender;
import me.conclure.nonamer.command.sender.ConsoleCommandSender;

public interface Message {

    Args ONLY_CONSOLE = () -> "Only console can execute this command!";
    Args ONLY_USER = () -> "Only discord users can execute this command!";
    Args1<String,CommandSender> UNKNOWN_COMMAND = (prefix,sender) -> {
        prefix = sender instanceof ConsoleCommandSender ? "" : prefix;
        return String.format("Unknown command, type \"%shelp\" to see available commands.",prefix);
    };


    interface Args {
        String build();

        default void send(CommandSender sender) {
            sender.sendMessage(this.build());
        }
    }

    interface Args0<T0> {
        String build(T0 arg0);

        default void send(CommandSender sender, T0 arg0) {
            sender.sendMessage(this.build(arg0));
        }
    }

    interface Args1<T0,T1> {
        String build(T0 arg0, T1 arg1);

        default void send(CommandSender sender, T0 arg0, T1 arg1) {
            sender.sendMessage(this.build(arg0,arg1));
        }
    }
}