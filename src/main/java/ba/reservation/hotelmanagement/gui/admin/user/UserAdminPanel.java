package ba.reservation.hotelmanagement.gui.admin.user;

import ba.reservation.hotelmanagement.business.model.Privilege;
import ba.reservation.hotelmanagement.business.model.User;
import ba.reservation.hotelmanagement.business.service.PrivilegeServiceFactory;
import ba.reservation.hotelmanagement.business.service.UserServiceFactory;
import ba.reservation.hotelmanagement.business.service.UserServiceLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.hibernate.tool.schema.Action;

import java.util.List;

public class UserAdminPanel extends VBox {
    private Label titleLabel = new Label("Administracija korisnika");
    private ObservableList<User> userObservableList;
    private TableView<User> userTableView = new TableView<>();

    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final ChoiceBox<Privilege> privilegeChoiceBox = new ChoiceBox<>();


    private Button addUserButton = new Button("Dodaj");
    private Button deleteUserButton = new Button("Obriši");

    public UserAdminPanel(){
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Korisničko ime");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));

        TableColumn<User, String> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<>("idPrivilege"));

        List<User> userList = UserServiceFactory.USER_SERVICE.getUserService().findAll();
        userObservableList = FXCollections.observableList(userList);
        userTableView.setItems(userObservableList);
        userTableView.getColumns().addAll(usernameColumn, nameColumn, surnameColumn, privilegeColumn);
        getChildren().addAll(titleLabel, userTableView, getForm());
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

        List<Privilege> privileges = PrivilegeServiceFactory.PRIVILEGE_SERVICE.getPrivilegeService().findAll();
        privilegeChoiceBox.setItems(FXCollections.observableList(privileges));
        privilegeChoiceBox.getSelectionModel().select(0);
        usernameTextField.setPromptText("Username..");
        passwordField.setPromptText("Password..");
        nameTextField.setPromptText("Ime..");
        surnameTextField.setPromptText("Prezime..");
        addUserButton.setOnAction(this::addUser);
        form.getChildren().addAll(usernameTextField, passwordField,nameTextField, surnameTextField, privilegeChoiceBox, addUserButton);
        return form;
    }

    private void addUser(ActionEvent event){
        //TRANZIJENTAN
        User user = new User();
        user.setUsername(usernameTextField.getText());
        user.setPassword(passwordField.getText());
        user.setName(nameTextField.getText());
        user.setSurname(surnameTextField.getText());
        user.setIdPrivilege(privilegeChoiceBox.getValue());
        UserServiceLocal userService = UserServiceFactory.USER_SERVICE.getUserService();
        //TRANZIJENTNOG U PERZISTENTNO STANJE
        userService.create(user);
        userObservableList.add(user);
        clearInput();
    }

    private void clearInput(){
        usernameTextField.setText("");
        passwordField.setText("");
        nameTextField.clear();
        surnameTextField.clear();

    }
}
