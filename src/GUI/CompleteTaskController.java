package GUI;

import Model.Interfaces.TaskInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Opsymonroe on 11.11.2017.
 */
public class CompleteTaskController {

    public static TaskInterface taskInterface;
    public static int WINDOW_STATUS;
    public static final int OK = 1;
    public static final int CANCELLED = 0;
    @FXML
    DatePicker confirmDatePicker;
    @FXML
    TextField confirmTimePicker;
    @FXML
    Button confirmOkButton;
    @FXML
    AnchorPane confirmAP;

    public void onConfirmButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage)confirmAP.getScene().getWindow();
        stage.hide();
        /*Здесь мы, например, пометим задачу на удаление*/
    }

    public void onRescheduleButtonClicked(ActionEvent actionEvent) {
        confirmDatePicker.setDisable(false);
        confirmTimePicker.setDisable(false);
        confirmOkButton.setDisable(false);
    }

    public void onCoinfirmOkButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage)confirmAP.getScene().getWindow();
        stage.hide();
        /*А здесь мы сохраним введенные изменения, чтобы потом отобразить их в листе*/
    }
}
