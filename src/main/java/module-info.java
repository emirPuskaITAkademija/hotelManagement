module ba.reservation.hotelmanagement {
    requires javafx.controls;
    requires java.naming;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.persistence;
    requires java.xml.bind;
    requires java.sql;
    requires java.sql.rowset;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    opens ba.reservation.hotelmanagement to javafx.fxml;
    opens ba.reservation.hotelmanagement.business.model to org.hibernate.orm.core;

    exports ba.reservation.hotelmanagement;
    exports ba.reservation.hotelmanagement.business.model;
}