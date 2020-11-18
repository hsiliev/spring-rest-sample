package hsiliev.tasks.controller;

import hsiliev.tasks.dependencies.Dependencies;
import hsiliev.tasks.model.Job;
import hsiliev.tasks.model.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JobController {

  public static final String SCRIPT_HEADER = "#!/usr/bin/env bash\n\nset +x\n\n";

  @PostMapping(
    value = "/jobs",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Task> createJson(@RequestBody Job job) {
    return Dependencies.build(job).order();
  }

  @PostMapping(
    value = "/jobs",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.TEXT_PLAIN_VALUE)
  public String createShellScript(@RequestBody Job job) {
    List<Task> sortedTasks = Dependencies.build(job).order();
    return buildScript(sortedTasks);
  }

  private String buildScript(List<Task> sortedTasks) {
    return sortedTasks.stream().map(Task::getCommand).collect(Collectors.joining("\n", SCRIPT_HEADER, "\n"));
  }

}