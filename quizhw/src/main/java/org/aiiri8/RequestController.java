package org.aiiri8;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestController {
  private static final Logger logger = LogManager.getLogger(RequestController.class);

  private static final String REQUEST_URI = "https://jservice.io/api/random";
  private static final HttpClient client = HttpClient.newHttpClient();

  public Question getQuestion() throws URISyntaxException, IOException, InterruptedException {
    logger.debug("Отправлен запрос на получение попроса");
    HttpRequest request = HttpRequest.newBuilder().uri(new URI(REQUEST_URI))
        .version(HttpClient.Version.HTTP_1_1).GET().build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() == 200) {
      logger.debug("Ответ успешно получен");
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(response.body(), Question[].class)[0];
    } else {
      logger.error(String.format("Ошибка при выполнении запроса, код %d", response.statusCode()));
      throw new RuntimeException(String.format("Ошибка при получении вопроса викторины, код %d.", response.statusCode()));
    }
  }
}
