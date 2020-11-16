package hsiliev.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Task {

  public String name;

  public String command;

  @JsonIgnore
  public List<String> requires;

  @Override
  public String toString() {
    return "Task{" +
      "name='" + name + '\'' +
      ", command='" + command + '\'' +
      ", requires=" + requires +
      '}';
  }
}
