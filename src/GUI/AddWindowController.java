package GUI;

import Model.Implementations.Task;
import Model.Interfaces.TaskInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Opsymonroe on 07.11.2017.
 */
public class AddWindowController {

    public static TaskInterface taskInterface;
    public static int WINDOW_STATUS;
    public static final int OK = 1;
    public static final int CANCELLED = 0;
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField nameTF;
    @FXML
    TextArea discriptionTA;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField timePicker;
    @FXML
    TextField firstContactTF;
    @FXML
    TextField secondContactTF;
    @FXML
    TextField thirdContactTF;


    public void clearFields() {
        nameTF.clear();
        discriptionTA.clear();
        datePicker.hide();
        timePicker.clear();
        firstContactTF.clear();
        secondContactTF.clear();
        thirdContactTF.clear();
    }

    public void onOkButtonClicked(ActionEvent actionEvent) {

        try {
            if (nameTF.getText().equals("")) {
                throw new IllegalArgumentException("Empty task name field");
            }
            String name = nameTF.getText();
            String discription = discriptionTA.getText();
            String time = timePicker.getText();
            String[] hm = time.split(":");
            int[] hours = null;
            String date = null;
            date = datePicker.getValue().toString();
            if (date.trim().equals("")) {
                throw new IllegalArgumentException("Empty date field");
            }

            hours = new int[]{Integer.parseInt(hm[0]), Integer.parseInt(hm[1])};
            if (hours[0] > 23 || hours[0] < 0 || hours[1] > 59 || hours[1] < 0)
                throw new IllegalArgumentException("Wrong time format");
            String[] dateArr = date.split("-");
            int[] DateNum = new int[dateArr.length];
            for (int i = 0; i < dateArr.length; i++) {
                DateNum[i] = Integer.parseInt(dateArr[i]);
            }

            String[] contacts = {firstContactTF.getText(), secondContactTF.getText(), thirdContactTF.getText()};
            GregorianCalendar calendar = new GregorianCalendar(DateNum[0], DateNum[1]-1, DateNum[2], hours[0], hours[1]);
            if(calendar.getTimeInMillis() < System.currentTimeMillis())
                throw new IllegalArgumentException("Date cant be less than current time");
            taskInterface = new Task(name, discription, calendar, contacts);
            System.out.println(taskInterface);
            WINDOW_STATUS = OK;
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            clearFields();
            stage.hide();


        }catch (NumberFormatException exc){
            alert("Time field is empty");
        }
        catch (IllegalArgumentException e) {
            alert(e.getMessage());
        }catch (NullPointerException ex){
            alert("Date field is empty");
        }
    }

    public void onCancelButtonCLicked(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        WINDOW_STATUS = CANCELLED;
        clearFields();
        stage.hide();
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


