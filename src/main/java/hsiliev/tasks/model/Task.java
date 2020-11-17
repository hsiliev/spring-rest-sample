package hsiliev.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Task {

  private String name;

  private String command;

  private List<String> requires;

  public String getName() {
    return name;
  }

  public String getCommand() {
    return command;
  }

  @JsonIgnore
  public List<String> getRequires() {
    return requires;
  }

  @JsonProperty
  public void setRequires(List<String> requires) {
    this.requires = requires;
  }

  @Override
  public String toString() {
    return "Task{" +
      "name='" + name + '\'' +
      ", command='" + command + '\'' +
      ", requires=" + requires +
      '}';
  }
}
