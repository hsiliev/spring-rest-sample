package hsiliev.tasks.dependencies;

import hsiliev.tasks.model.Job;
import hsiliev.tasks.model.Task;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DependenciesService {

  public Resolver build(Job job) {
    Graph<Task, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

    createVertices(graph, job);
    addEdges(graph, job);

    checkForCycles(graph);

    return new Resolver(graph);
  }

  private void createVertices(Graph<Task, DefaultEdge> graph, Job job) {
    job.getTasks().forEach(graph::addVertex);
  }

  private void addEdges(Graph<Task, DefaultEdge> graph, Job job) {
    Map<String, Task> nameToTaskMapping = job.getTasks().stream().collect(Collectors.toMap(Task::getName, task -> task));

    for (Task task : job.getTasks()) {
      if (task.getDependentTaskNames() != null) {
        for (String requiredTaskName : task.getDependentTaskNames()) {
          try {
            graph.addEdge(nameToTaskMapping.get(requiredTaskName), task);
          } catch (IllegalArgumentException e) {
            throw new DependencyCycleException("Job contains looping task", e);
          }
        }
      }
    }
  }

  private void checkForCycles(Graph<Task, DefaultEdge> graph) {
    CycleDetector<Task, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
    if (cycleDetector.detectCycles()) {
      Set<Task> cycleVertices = cycleDetector.findCycles();
      String message = cycleVertices.stream().map(Task::toString).collect(Collectors.joining(",", "Job contains cycles: ", "\n"));
      throw new DependencyCycleException(message);
    }
  }

  public static class Resolver {
    private final Graph<Task, DefaultEdge> graph;

    public Resolver(Graph<Task, DefaultEdge> graph) {
      this.graph = graph;
    }

    public List<Task> order() {
      TopologicalOrderIterator<Task, DefaultEdge> orderIterator = new TopologicalOrderIterator<>(graph);

      List<Task> result = new ArrayList<>();
      orderIterator.forEachRemaining(result::add);

      return result;
    }
  }
}
