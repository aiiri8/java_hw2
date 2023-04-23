package org.aiiri8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        RequestController requestController = new RequestController();
        IOController ioController = new IOController();
        QuizMenu quiz = new QuizMenu();

        try {
            logger.debug("Приложение запущено");
            quiz.run(requestController, ioController);
        } catch (Exception e) {
            logger.error("Вылетело необработанное исключение - ", e);
            ioController.printMessage("Вылетело необработанное исключение, повторите попытку позже:с");
        }
    }
}
