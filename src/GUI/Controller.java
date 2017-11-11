package GUI;

import Model.Implementations.TaskJournal;
import Model.Interfaces.TaskInterface;
import Util.AlertTimerTask;
import Util.UtilStuff;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.ScheduledExecutorService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

/**
 * Created by Opsymonroe on 07.11.2017.
 */
public class Controller implements Initializable {

    Stage newTaskStage;
    public Timer timer = null;

    TaskJournal journal = (TaskJournal) UtilStuff.loadJournal();
    ObservableList<TaskInterface> tasks = FXCollections.observableArrayList(journal.getList());
    @FXML
    javafx.scene.control.ListView<TaskInterface> listView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newTaskStage = new Stage();

        Parent root = null;
        Parent rootComplete = null;
        AddWindowController controller = null;
        try {
            FXMLLoader loaderAdd = new FXMLLoader(getClass().getResource("FXMLS/AddWindow.fxml"));
            root = loaderAdd.load(getClass().getResource("FXMLS/AddWindow.fxml"));
            controller = loaderAdd.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
        timer = new Timer();
        AlertTimerTask alertTimerTask = new AlertTimerTask(this, journal);
        timer.schedule(alertTimerTask, UtilStuff.getLeastTimeDelay(journal));
        //setTimer();
//        long time = UtilStuff.getLeastTimeDelay(journal);
//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.millis(time),
//                ae -> onDatePassed()
//        ));
//        timeline.play();
        newTaskStage.setTitle("Add new task");
        newTaskStage.setResizable(false);
        newTaskStage.setScene(new Scene(root));
        listView.setItems(tasks);
    }


    public void onDeleteTaskButtonClicked(ActionEvent actionEvent) {
        int index = listView.getFocusModel().getFocusedIndex();
        if (index == -1)
            return;
        tasks.remove(index);
        journal.removeTask(index);
        listView.setItems(tasks);
        System.out.println("DeleteClicked");
    }

    public void onAddTaskButtonClicked(ActionEvent actionEvent) {
        newTaskStage.showAndWait();
        if (AddWindowController.WINDOW_STATUS == 1) {
            try {
                tasks.add(AddWindowController.taskInterface);
                journal.addTask(AddWindowController.taskInterface);
                System.out.println("--------------OnAddTaskButtonClicked-------------------");
                System.out.println(journal);
                setTimer();
//                timer.cancel();
//                timer.purge();
//                AlertTimerTask alertTimerTask = new AlertTimerTask(this, journal);
//                timer.schedule(alertTimerTask, UtilStuff.getLeastTimeDelay(journal));
                listView.setItems(tasks);
                //listView.
            } catch (IllegalArgumentException e) {
                //alert("Incorrect inputs");
            }
        }
    }

    public void onDatePassed() {
        Stage confirmStage = new Stage();
        Parent rootComplete = null;
        try {
            FXMLLoader loaderConfirm = new FXMLLoader(getClass().getResource("FXMLS/Complete.fxml"));
            rootComplete = loaderConfirm.load(getClass().getResource("FXMLS/Complete.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        confirmStage.setTitle("Confirm task");
        confirmStage.setResizable(false);
        confirmStage.setScene(new Scene(rootComplete));
        confirmStage.showAndWait();
        System.out.println("Date passed");
    }

    public void setTimer() {
        timer.cancel();
        timer.purge();
        timer = new Timer();
        AlertTimerTask alertTimerTask = new AlertTimerTask(this, journal);
        System.out.println("-------------setTimer-------------------");
        long time = UtilStuff.getLeastTimeDelay(journal);
        System.out.println(time);
        timer.schedule(alertTimerTask, time);
//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.millis(time),
//                ae -> onDatePassed()
//        ));
//        timeline.play();
    }

    // public void listViewOnEdit(EditEvent<TaskInterface> taskInterfaceEditEvent) {
    //}
}
