package hsiliev.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Task {

  private String name;

  private String command;

  @JsonProperty("requires")
  private List<String> dependentTaskNames;

  public String getName() {
    return name;
  }

  public String getCommand() {
    return command;
  }

  @JsonIgnore
  public List<String> getDependentTaskNames() {
    return dependentTaskNames;
  }

  @JsonProperty
  public void setDependentTaskNames(List<String> dependentTaskNames) {
    this.dependentTaskNames = dependentTaskNames;
  }

  @Override
  public String toString() {
    return "Task{" +
      "name='" + name + '\'' +
      ", command='" + command + '\'' +
      ", dependentTaskNames=" + dependentTaskNames +
      '}';
  }
}
