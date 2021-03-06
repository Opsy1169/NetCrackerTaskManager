package GUI;

import Util.UtilStuff;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLS/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            UtilStuff.saveJournal(Controller.journal);
            Controller.timer.cancel();
            Controller.timer.purge();
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                System.out.println(t.getName());
                if (t.getState() == Thread.State.RUNNABLE)
                    t.interrupt();
            }
        });
    }
}
