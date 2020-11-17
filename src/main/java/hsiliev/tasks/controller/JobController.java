package hsiliev.tasks.controller;

import hsiliev.tasks.model.Job;
import hsiliev.tasks.model.Task;
import hsiliev.tasks.resolver.DependenciesResolver;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

  public static final String SCRIPT_HEADER = "#!/usr/bin/env bash\n\nset +x\n\n";

  @PostMapping(
    value = "/jobs",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Task> createJson(@RequestBody Job job) {
    return getSortedTasks(job);
  }

  private List<Task> getSortedTasks(Job job) {
    DependenciesResolver dependenciesResolver = new DependenciesResolver(job);
    return dependenciesResolver.sort();
  }

  @PostMapping(
    value = "/jobs",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.TEXT_PLAIN_VALUE)
  public String createShellScript(@RequestBody Job job) {
    List<Task> sortedTasks = getSortedTasks(job);
    return buildScript(sortedTasks);
  }

  private String buildScript(List<Task> sortedTasks) {
    StringBuilder builder = new StringBuilder(SCRIPT_HEADER);
    for (Task sortedTask : sortedTasks) {
      builder.append(sortedTask.getCommand()).append("\n");
    }
    return builder.toString();
  }

}