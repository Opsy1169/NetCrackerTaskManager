package gui;

import model.interfaces.TaskInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.GregorianCalendar;

/**
 * Created by Opsymonroe on 11.11.2017.
 */
public class CompleteTaskController {

    public static TaskInterface taskInterface;
    public static int WINDOW_STATUS;
    public static final int OK = 1;
    public static final int CANCELLED = 0;
    public Controller controller;
    @FXML
    DatePicker confirmDatePicker;
    @FXML
    TextField confirmTimePicker;
    @FXML
    Button confirmOkButton;
    @FXML
    AnchorPane confirmAP;

    public void onConfirmButtonClicked(ActionEvent actionEvent) {
        Thread.currentThread().interrupt();

        Controller.timer.cancel();
        Controller.timer.purge();
        Controller.journal.getList().remove(0);
        controller.update();
        //controller.onDeleteTaskButtonClicked(actionEvent);
        Stage stage = (Stage)confirmAP.getScene().getWindow();
        WINDOW_STATUS = CANCELLED;
        stage.hide();



        /*Здесь мы, например, пометим задачу на удаление*/
    }

    public void onRescheduleButtonClicked(ActionEvent actionEvent) {
        confirmDatePicker.setDisable(false);
        confirmTimePicker.setDisable(false);
        confirmOkButton.setDisable(false);
    }

    public void onCoinfirmOkButtonClicked(ActionEvent actionEvent) {
        Thread.currentThread().interrupt();
        Controller.timer.cancel();
        Controller.timer.purge();
        try {
            Stage stage = (Stage) confirmAP.getScene().getWindow();
            String time = confirmTimePicker.getText();
            String[] hm = time.split(":");
            String date = null;
            date = confirmDatePicker.getValue().toString();
            if (date.trim().equals("")) {
                throw new IllegalArgumentException("Empty date field");
            }
            int[] hours = new int[]{Integer.parseInt(hm[0]), Integer.parseInt(hm[1])};
            if (hours[0] > 23 || hours[0] < 0 || hours[1] > 59 || hours[1] < 0)
                throw new IllegalArgumentException("Wrong time format");
            String[] dateArr = date.split("-");
            int[] DateNum = new int[dateArr.length];
            for (int i = 0; i < dateArr.length; i++) {
                DateNum[i] = Integer.parseInt(dateArr[i]);
            }
            GregorianCalendar calendar = new GregorianCalendar(DateNum[0], DateNum[1]-1, DateNum[2], hours[0], hours[1]);
            if(calendar.getTimeInMillis() < System.currentTimeMillis())
                throw new IllegalArgumentException("Date cant be less than current time");
            Controller.journal.getList().get(0).setDate(calendar);
            WINDOW_STATUS  = OK;
            controller.update();
            //taskInterface = new Task(name, discription, calendar, contacts);
            stage.hide();


        /*А здесь мы сохраним введенные изменения, чтобы потом отобразить их в листе*/
        }catch (NumberFormatException exc){
            alert("Time field is empty");
        }
        catch (IllegalArgumentException e) {
            alert(e.getMessage());
        }catch (NullPointerException ex){
            alert("Date field is empty");
        }
    }



    public void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(null);
        alert.setContentText("Wrong Input: " + message);
        alert.showAndWait();
        WINDOW_STATUS = CANCELLED;
    }
}
