package org.aiiri8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuizGame {
  private static final Logger logger = LogManager.getLogger(QuizGame.class);

  public void run(RequestController requestController, IOController ioController)
      throws URISyntaxException, IOException, InterruptedException {
    logger.debug("Игра запущена");
    int numberOfRightAnswers = 0;
    int numberOfWrongAnswers = 0;

    boolean isRunning = true;
    Answer answer;
    Question question = requestController.getQuestion();
    ioController.printMessage(question.getQuestion());

    while (isRunning) {
      answer = ioController.getAnswer();

      if (answer.isAnswer()) {
        if (checkAnswer(question, answer)) {
          numberOfRightAnswers++;
          ioController.printMessage("Все верно!");
        } else {
          numberOfWrongAnswers++;
          ioController.printMessage(String.format("Ответ неверный! Правильный ответ - %s!",
              question.getAnswer()));
        }

        question = requestController.getQuestion();
        ioController.printMessage(question.getQuestion());
      } else {
        isRunning = false;
        showScore(ioController, numberOfRightAnswers, numberOfWrongAnswers);
      }
    }
    logger.debug("Игра завершена");
  }

  private boolean checkAnswer(Question question, Answer answer) {
    logger.debug("Ответ проверяется");
    return (Objects.equals(question.getAnswer().trim().toLowerCase(), answer.answer()));
  }

  private void showScore(IOController ioController, int numberOfRightAnswers, int numberOfWrongAnswers) {
    logger.debug("Вывод результатов");
    ioController.printScore(numberOfRightAnswers, numberOfWrongAnswers);
  }
}
