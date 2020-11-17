package hsiliev.tasks.resolver;

import hsiliev.tasks.model.Job;
import hsiliev.tasks.model.Task;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.*;
import java.util.stream.Collectors;

public class DependenciesResolver {

  private final Graph<Task, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

  public DependenciesResolver(Job job) {
    createEdges(job);
    addVertexes(job);

    checkForCycles();
  }

  private void createEdges(Job job) {
    for (Task task : job.getTasks()) {
      graph.addVertex(task);
    }
  }

  private void addVertexes(Job job) {
    Map<String, Task> nameToTaskMapping = job.getTasks().stream().collect(Collectors.toMap(Task::getName, task -> task));

    for (Task task : job.getTasks()) {
      if (task.getRequires() != null) {
        for (String requiredTaskName : task.getRequires()) {
          graph.addEdge(nameToTaskMapping.get(requiredTaskName), task);
        }
      }
    }
  }

  private void checkForCycles() {
    CycleDetector<Task, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
    if (cycleDetector.detectCycles()) {
      Set<Task> cycleVertices = cycleDetector.findCycles();
      throw new IllegalArgumentException("Job contains cycles: " + Arrays.toString(cycleVertices.toArray()));
    }
  }

  public List<Task> sort() {
    TopologicalOrderIterator<Task, DefaultEdge> orderIterator = new TopologicalOrderIterator<>(graph);

    List<Task> result = new ArrayList<>();
    orderIterator.forEachRemaining(result::add);

    return result;
  }
}
