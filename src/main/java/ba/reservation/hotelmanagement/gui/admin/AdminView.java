package ba.reservation.hotelmanagement.gui.admin;

import ba.reservation.hotelmanagement.gui.Controller;
import ba.reservation.hotelmanagement.gui.admin.room.RomAdminPanel;
import ba.reservation.hotelmanagement.gui.admin.user.UserAdminPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminView extends BorderPane {
    private final ToggleButton userToggleButton = new ToggleButton("Korisnici");
    private final ToggleButton roomToggleButton = new ToggleButton("Sobe");
    private final Button logoutButton = new Button("Odjava");

    private UserAdminPanel userAdminPanel = new UserAdminPanel();
    private RomAdminPanel romAdminPanel = new RomAdminPanel();

    public AdminView() {
        setCenter(userAdminPanel);

        ToggleGroup menuToggleGroup = new ToggleGroup();
        userToggleButton.setToggleGroup(menuToggleGroup);
        roomToggleButton.setToggleGroup(menuToggleGroup);

        userToggleButton.setSelected(true);

        HBox mainMenu = new HBox();
        mainMenu.setSpacing(5);
        mainMenu.setPadding(new Insets(10, 10, 10, 10));
        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        logoutButton.setText("Odjava("+Controller.instance().getLoggedUser().getName()+")");
        userToggleButton.setOnAction(e->setCenter(userAdminPanel));
        roomToggleButton.setOnAction(e->setCenter(romAdminPanel));
        mainMenu.getChildren().addAll(userToggleButton, roomToggleButton);

        HBox logoutHBox = new HBox(logoutButton);
        logoutHBox.setAlignment(Pos.CENTER_RIGHT);
        logoutHBox.setPadding(new Insets(10, 10, 10, 10));

        GridPane topPane = new GridPane();
        topPane.add(mainMenu, 0, 0);
        topPane.add(logoutHBox, 1, 0);
        setTop(topPane);
    }

}
