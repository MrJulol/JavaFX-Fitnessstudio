module org.example.fitnessstudiomanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.fitnessstudiomanagement to javafx.fxml;
    exports org.example.fitnessstudiomanagement;
    exports org.example.fitnessstudiomanagement.Helper;
    opens org.example.fitnessstudiomanagement.Helper to javafx.fxml;
    exports org.example.fitnessstudiomanagement.Data;
    opens org.example.fitnessstudiomanagement.Data to javafx.fxml;
    exports org.example.fitnessstudiomanagement.Controllers;
    opens org.example.fitnessstudiomanagement.Controllers to javafx.fxml;
    exports org.example.fitnessstudiomanagement.Model;
    opens org.example.fitnessstudiomanagement.Model to javafx.fxml;
    exports org.example.fitnessstudiomanagement.Enums;
    opens org.example.fitnessstudiomanagement.Enums to javafx.fxml;
    exports org.example.fitnessstudiomanagement.Languages;
    opens org.example.fitnessstudiomanagement.Languages to javafx.fxml;
}