package me.conclure.nonamer.command;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandArguments implements Iterable<String> {
  private final List<String> arguments;

  public CommandArguments() {
    this.arguments = new ArrayList<>();
  }

  public int length() {
    return this.arguments.size();
  }

  public String get(int index) {
    return this.arguments.get(index);
  }

  @NotNull
  @Override
  public Iterator<String> iterator() {
    return this.arguments.iterator();
  }
}
