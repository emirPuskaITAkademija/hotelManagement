package ba.reservation.hotelmanagement.gui.events;

import ba.reservation.hotelmanagement.business.model.Privilege;
import ba.reservation.hotelmanagement.business.model.User;
import ba.reservation.hotelmanagement.business.service.UserServiceFactory;
import ba.reservation.hotelmanagement.gui.Controller;
import ba.reservation.hotelmanagement.gui.admin.AdminView;
import ba.reservation.hotelmanagement.gui.employee.EmployeeView;
import ba.reservation.hotelmanagement.gui.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class LoginEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        //username, password
        LoginView loginView = Controller.instance().getLoginView();
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            loginView.setLoginMessage("Nije dozvoljeno prazan unos korisničkog imena ili lozinke");
            return;
        }
        System.out.println("Poslani username: " + username);
        System.out.println("Password: " + password);
        User user = UserServiceFactory.USER_SERVICE.getUserService().login(username, password);

        if(user == null){
            loginView.setLoginMessage("Neispravna kombinacija korisničkog imena i lozinke");
        }else{
            System.out.println(user.getName()+" <----- AKTULENI UČITANI KORISNIK");
            System.out.println(user.getIdPrivilege().getName());
            Controller.instance().setLoggedUser(user);
            Privilege privilege = user.getIdPrivilege();
            BorderPane mainPanel;
            if("admin".equalsIgnoreCase(privilege.getName())){
                //admin panel
                mainPanel = new AdminView();
                Controller.instance().setAdminView((AdminView) mainPanel);
                Controller.instance().getStage().setTitle("Admin Panel: " + Controller.instance().getLoggedUser().getName());
            }else{
                //employee panel
                mainPanel = new EmployeeView();
                Controller.instance().setEmployeeView((EmployeeView) mainPanel);
                Controller.instance().getStage().setTitle("Employee Panel: " + user.getName()+" " + user.getSurname());
            }
            Scene scene = new Scene(mainPanel, 650, 300);
            Controller.instance().getStage().setScene(scene);
        }
    }
}
