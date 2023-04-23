package org.aiiri8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuizMenu {
  private static final Logger logger = LogManager.getLogger(QuizMenu.class);

  public void run(RequestController requestController, IOController ioController)
      throws URISyntaxException, IOException, InterruptedException {
    logger.debug("Меню открыто");
    ioController.printInfo();

    boolean isRunning = true;
    QuizMenuOption option;

    while (isRunning) {
      option = ioController.getMenuOption();

      if (option == QuizMenuOption.START) {
        QuizGame quizGame = new QuizGame();
        quizGame.run(requestController, ioController);
      } else {
        isRunning = false;
        ioController.printMessage("Все игры завершены. Заходите еще!");
      }
    }
    logger.debug("Меню закрыто");
  }
}
