package GUI;

import Model.Implementations.TaskJournal;
import Model.Interfaces.TaskInterface;
import Util.AlertTimerTask;
import Util.TaskDateComparator;
import Util.UtilStuff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Opsymonroe on 07.11.2017.
 */
public class Controller implements Initializable {

    Stage newTaskStage;
    public static Timer timer = null;
    Logger log = Logger.getLogger("logger");

    public static TaskJournal journal = (TaskJournal) UtilStuff.loadJournal();
   // private ArrayList<Thread> threads =  new ArrayList<>();
    private ObservableList<TaskInterface> tasks = FXCollections.observableArrayList(journal.getList());
    @FXML
    javafx.scene.control.ListView<TaskInterface> listView;
    @FXML
    VBox mainVbox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newTaskStage = new Stage();
        Parent root = null;
        Parent rootComplete = null;
        try {
            FXMLLoader loaderAdd = new FXMLLoader(getClass().getResource("/AddWindow.fxml"));
            root = loaderAdd.load(getClass().getResource("/AddWindow.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        timer = new Timer();
        AlertTimerTask alertTimerTask = new AlertTimerTask(this, journal);
        long time = journal.getList().get(0).getDate().getTimeInMillis() - System.currentTimeMillis();
        timer.schedule(alertTimerTask, time);
        newTaskStage.setTitle("Add new task");
        newTaskStage.setResizable(false);
        newTaskStage.setScene(new Scene(root));
        newTaskStage.initModality(Modality.APPLICATION_MODAL);
        //newTaskStage.initOwner(listView.getScene().getWindow());
        listView.setItems(tasks);
        if(UtilStuff.CHANGED)
            alert("Some of your tasks were deleted due to date expiration");
    }


    public void onDeleteTaskButtonClicked(ActionEvent actionEvent) {
        int index = listView.getFocusModel().getFocusedIndex();
        if (index == -1)
            return;
        tasks.remove(index);
        journal.removeTask(index);
        Collections.sort(journal.getList(), new TaskDateComparator());
        listView.setItems(tasks);
        setTimer();
        log.logp(Level.INFO, "Controller", "OnDeleteTaskButtonClicked", "DeleteClicked");
    }

    public void onAddTaskButtonClicked(ActionEvent actionEvent) {
        //newTaskStage.initOwner(((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow());
        newTaskStage.showAndWait();
        if (AddWindowController.WINDOW_STATUS == 1) {
            try {
                //tasks.add(AddWindowController.taskInterface);
                journal.addTask(AddWindowController.taskInterface);
                Collections.sort(journal.getList(), Comparator.comparing(TaskInterface::getDate));
                tasks = FXCollections.observableArrayList(journal.getList());
                System.out.println("--------------OnAddTaskButtonClicked-------------------");
                System.out.println(journal);
                setTimer();
                listView.setItems(tasks);
            } catch (IllegalArgumentException e) {
                //alert("Incorrect inputs");
            }
        }
    }

    public void onDatePassed() {
        System.out.println("выполнилось из треда" + Thread.currentThread().getId());
        Stage confirmStage = new Stage();
        Parent rootComplete = null;
        CompleteTaskController taskController = null;
        try {
            FXMLLoader loaderConfirm = new FXMLLoader(getClass().getResource("/Complete.fxml"));
            rootComplete = loaderConfirm.load();
            taskController = loaderConfirm.<CompleteTaskController>getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        taskController.controller = this;
        confirmStage.setTitle("Confirm task");
        confirmStage.setResizable(false);
        confirmStage.setScene(new Scene(rootComplete));
        confirmStage.initModality(Modality.APPLICATION_MODAL);
        //confirmStage.initOwner(mainVbox.getScene().getWindow());
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
        System.out.println("выполнилось из треда" + Thread.currentThread().getId());
        timer.schedule(alertTimerTask, time);
    }
    private void alert(String s){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Something went wrong");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void update(){
        Collections.sort(journal.getList(), new TaskDateComparator());
        tasks = FXCollections.observableArrayList(journal.getList());
        setTimer();
        listView.setItems(tasks);
    }

}
