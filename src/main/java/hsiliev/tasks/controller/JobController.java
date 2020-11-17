package hsiliev.tasks.controller;

import hsiliev.tasks.model.Job;
import hsiliev.tasks.model.Task;
import hsiliev.tasks.resolver.DependenciesResolver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

  @PostMapping("/jobs")
  public List<Task> create(@RequestBody Job job) {
    DependenciesResolver dependenciesResolver = new DependenciesResolver(job);
    return dependenciesResolver.sort();
  }

}