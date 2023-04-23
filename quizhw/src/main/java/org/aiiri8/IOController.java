package org.aiiri8;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IOController {
  private static final Logger logger = LogManager.getLogger(IOController.class);
  private final Scanner scanner = new Scanner(System.in);

  public void printMessage(String message) {
    System.out.println(message);
    logger.debug(String.format("Выведено сообщение: %s", message));
  }

  public void printInfo() {
    System.out.println("""
        ------------------------------------------------------------------
        Игра-викторина:
        
        Находясь в меню:
        -> введите /start для начала игры;
        -> введите /quit для завершения приложения;
        -> любой другой ответ будет считаться за некорректный ввод;
        
        Находясь в текущей игре:
        -> введите /end для завершения игры и вывода результатов;
        -> любой другой ответ будет считаться за ответ на заданный вопрос;
        
        ! игре не важен регистр ввода пользователя !
        ------------------------------------------------------------------
        """);
    logger.debug("Выведена информация о приложении");
  }

  public QuizMenuOption getMenuOption() {
    logger.debug("Отправлен запрос на ввод опции меню");
    System.out.println("> Введите /start или /quit:");

    while (true) {
      String line = scanner.nextLine().trim().toLowerCase();

      if (line.equals("/start")) {
        logger.debug("Получена опция /start");
        return QuizMenuOption.START;
      } else if (line.equals("/quit")) {
        logger.debug("Получена опция /quit");
        return QuizMenuOption.QUIT;
      } else {
        logger.debug("Некорректный ввод, повтор запроса");
        System.out.println("> Некорректный ввод, повторите попытку!");
      }
    }
  }

  public Answer getAnswer() {
    logger.debug("Отправлен запрос на получения ответа на вопрос");
    System.out.println("> Введите /end или ответ на вопрос:");
    String answer = scanner.nextLine().trim().toLowerCase();
    return new Answer(!answer.equals("/end"), answer);
  }

  public void printScore(int numberOfRightAnswers, int numberOfWrongAnswers) {
    logger.debug("Выведено сообщение с результатами игры");
    System.out.printf("> Игра завершена!\nПравильных ответов: %d;\nНеправильных ответов: %d!\n",
        numberOfRightAnswers, numberOfWrongAnswers);
  }
}
