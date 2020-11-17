package hsiliev.tasks.resolver;

import hsiliev.tasks.model.Job;
import hsiliev.tasks.model.Task;
import org.apache.logging.log4j.util.Strings;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DependenciesResolver {

  private final Graph<Task, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

  public DependenciesResolver(Job job) {
    createVertices(job);
    addEdges(job);

    checkForCycles();
  }

  private void createVertices(Job job) {
    for (Task task : job.getTasks()) {
      graph.addVertex(task);
    }
  }

  private void addEdges(Job job) {
    Map<String, Task> nameToTaskMapping = job.getTasks().stream().collect(Collectors.toMap(Task::getName, task -> task));

    for (Task task : job.getTasks()) {
      if (task.getRequires() != null) {
        for (String requiredTaskName : task.getRequires()) {
          try {
            graph.addEdge(nameToTaskMapping.get(requiredTaskName), task);
          } catch (IllegalArgumentException e) {
            throw new DependencyCycleException("Job contains looping task", e);
          }
        }
      }
    }
  }

  private void checkForCycles() {
    CycleDetector<Task, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
    if (cycleDetector.detectCycles()) {
      Set<Task> cycleVertices = cycleDetector.findCycles();
      throw new DependencyCycleException("Job contains cycles: " + Strings.join(cycleVertices, ','));
    }
  }

  public List<Task> sort() {
    TopologicalOrderIterator<Task, DefaultEdge> orderIterator = new TopologicalOrderIterator<>(graph);

    List<Task> result = new ArrayList<>();
    orderIterator.forEachRemaining(result::add);

    return result;
  }
}
