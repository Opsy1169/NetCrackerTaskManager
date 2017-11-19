package Util;

import GUI.Controller;
import Model.Interfaces.TaskJournalInterface;
import javafx.application.Platform;

import java.util.TimerTask;

/**
 * Created by Opsymonroe on 11.11.2017.
 */
public class AlertTimerTask extends TimerTask {
    private Controller controller;
    private TaskJournalInterface journalInterface;

    public AlertTimerTask(Controller controller, TaskJournalInterface journalInterface) {
        this.controller = controller;
        this.journalInterface = journalInterface;
    }

    @Override
    public void run() {
        Platform.runLater(() ->
        {
            System.out.println("выполнилось из треда" + Thread.currentThread().getId());
            controller.onDatePassed();
        });
        //controller.onDatePassed();

    }
}
