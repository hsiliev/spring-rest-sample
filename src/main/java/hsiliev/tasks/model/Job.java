package hsiliev.tasks.model;

import java.util.List;

public class Job {

  public List<Task> tasks;

  @Override
  public String toString() {
    return "Job{" +
      "tasks=" + tasks +
      '}';
  }
}
