package hsiliev.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JobControllerScriptTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void correctBodyShouldReturnOrderedTasks() throws Exception {
    this.mockMvc.perform(
      post("/jobs").contentType(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN).content(TestUtils.readResource("correctRequest.json"))
    ).andDo(print()).andExpect(
      status().isOk()
    ).andExpect(
      content().string(equalToCompressingWhiteSpace(TestUtils.readResource("correctScriptResponse.txt")))
    );
  }

  @Test
  public void cycleInTasksShouldError() throws Exception {
    this.mockMvc.perform(
      post("/jobs").contentType(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN).content(TestUtils.readResource("cycleInTasksRequest.json"))
    ).andDo(print()).andExpect(
      status().is4xxClientError()
    );
  }
}