package ba.reservation.hotelmanagement;

import ba.reservation.hotelmanagement.gui.Controller;
import ba.reservation.hotelmanagement.gui.login.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HotelApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView();
        Controller.instance().setStage(stage);
        Controller.instance().setLoginView(loginView);
        Scene scene = new Scene(loginView, 650, 180);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}