module org.example.swtlabormimobaleph {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens org.example.swtlabormimobaleph to javafx.fxml;
    exports org.example.swtlabormimobaleph;
}