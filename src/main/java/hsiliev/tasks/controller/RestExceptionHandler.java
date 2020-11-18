package hsiliev.tasks.controller;

import hsiliev.tasks.dependencies.DependencyCycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(JobController.class);

  @ExceptionHandler(DependencyCycleException.class)
  protected ResponseEntity<Object> handleDependencyCycle(DependencyCycleException e) {
    logger.warn("Handling exception", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}