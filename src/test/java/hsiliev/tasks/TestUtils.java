package hsiliev.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {
  public static String readResource(String relativePath) throws IOException {
    return new String(Files.readAllBytes(Paths.get("src/test/resources", relativePath)));
  }

}
