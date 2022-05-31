package ba.reservation.hotelmanagement.gui.events;

import ba.reservation.hotelmanagement.gui.Controller;
import ba.reservation.hotelmanagement.gui.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class LogoutEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        Controller.instance().setLoggedUser(null);
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView, 600, 180);
        Controller.instance().getStage().setScene(scene);
    }
}
