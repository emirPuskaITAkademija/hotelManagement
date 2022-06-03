package ba.reservation.hotelmanagement.gui.admin.room;

import ba.reservation.hotelmanagement.business.model.Privilege;
import ba.reservation.hotelmanagement.business.model.Room;
import ba.reservation.hotelmanagement.business.model.User;
import ba.reservation.hotelmanagement.business.room.RoomService;
import ba.reservation.hotelmanagement.business.room.RoomServiceFactory;
import ba.reservation.hotelmanagement.business.service.PrivilegeServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.math.BigDecimal;
import java.util.List;

public class RomAdminPanel extends VBox {
    private Label titleLabel = new Label("Administracija soba");
    private ObservableList<Room> roomObservableList;
    private TableView<Room> roomTableView = new TableView<>();

    private TextField roomCodeTextField = new TextField();
    private TextField numberOfBedsTextField = new TextField();
    private TextField priceTextField = new TextField();
    private Button addRoomButton = new Button("Dodaj sobu");

    public  RomAdminPanel(){
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        numberOfBedsTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberOfBedsTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        TableColumn<Room, String> roomCodeColumn = new TableColumn<>("Kod sobe");
        roomCodeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("code"));

        TableColumn<Room, Integer>  numberOfBedsColumn = new TableColumn<>("Broj kreveta");
        numberOfBedsColumn.setCellValueFactory(new PropertyValueFactory<Room, Integer>("numberOfBeds"));

        TableColumn<Room, BigDecimal> roomPriceColumn = new TableColumn<>("Cijena sobe");
        roomPriceColumn.setCellValueFactory(new PropertyValueFactory<Room, BigDecimal>("price"));

        RoomService roomService = RoomServiceFactory.SERVICE.getRoomService();
        roomObservableList = FXCollections.observableList(roomService.findAll());
        roomTableView.setItems(roomObservableList);
        roomTableView.getColumns().addAll(roomCodeColumn, numberOfBedsColumn, roomPriceColumn);

        getChildren().addAll(roomTableView, getForm());
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);
        roomCodeTextField.setPromptText("Kod sobe..");
        numberOfBedsTextField.setPromptText("Broj kreveta u sobi..");
        priceTextField.setPromptText("Cijena sobe..");
        addRoomButton.setOnAction(this::addRoom);
        form.getChildren().addAll(roomCodeTextField, numberOfBedsTextField, priceTextField, addRoomButton);
        return form;
    }

    private void addRoom(ActionEvent actionEvent){
        Room room = new Room();
        room.setCode(roomCodeTextField.getText());
        room.setNumberOfBeds(Integer.parseInt(numberOfBedsTextField.getText()));
        room.setPrice(new BigDecimal(priceTextField.getText()));
        RoomService roomService = RoomServiceFactory.SERVICE.getRoomService();
        roomService.create(room);
        roomObservableList.add(room);
        roomCodeTextField.setText("");
        numberOfBedsTextField.clear();
        priceTextField.setText("");
    }
}
