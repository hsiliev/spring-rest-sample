package hsiliev.tasks.model;

import java.util.List;

public class Job {

  private List<Task> tasks;

  public List<Task> getTasks() {
    return tasks;
  }

  @Override
  public String toString() {
    return "Job{" +
      "tasks=" + tasks +
      '}';
  }
}
