package hsiliev.tasks.dependencies;

public class DependencyCycleException extends RuntimeException {

  public DependencyCycleException(String message, Throwable cause) {
    super(message, cause);
  }

  public DependencyCycleException(String message) {
    super(message);
  }
}
