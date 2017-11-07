package GUI;

import Model.Implementations.TaskJournal;
import Model.Interfaces.TaskInterface;
import Util.UtilStuff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Opsymonroe on 07.11.2017.
 */
public class Controller implements Initializable{

    Stage newTaskStage;

    TaskJournal journal = (TaskJournal) UtilStuff.loadJournal();
    ObservableList<TaskInterface>tasks = FXCollections.observableArrayList(journal.getList());
    @FXML
    javafx.scene.control.ListView<TaskInterface>listView;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        newTaskStage = new Stage();

        Parent root = null;
        NewController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLS/AddWindow.fxml"));
            root = loader.load(getClass().getResource("FXMLS/AddWindow.fxml"));
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final NewController newController = controller;
        newTaskStage.setTitle("Function Parameters");
        newTaskStage.setResizable(false);
        newTaskStage.setScene(new Scene(root));
        listView.setItems(tasks);
    }


    public void onDeleteTaskButtonClicked(ActionEvent actionEvent) {
        int index = listView.getFocusModel().getFocusedIndex();
        tasks.remove(index);
        journal.removeTask(index);
        listView.setItems(tasks);
        System.out.println("DeleteClicked");
    }

    public void onAddTaskButtonClicked(ActionEvent actionEvent) {
        newTaskStage.showAndWait();
        if(NewController.WINDOW_STATUS == 1) {
            try {
                tasks.add(NewController.taskInterface);
                journal.addTask(NewController.taskInterface);
                listView.setItems(tasks);
                //listView.
            }catch (IllegalArgumentException e){
                //alert("Incorrect inputs");
            }
        }
    }
}
