module com.example.MinyoungSeol_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.SeoWooJang_comp228lab5 to javafx.fxml;
    exports com.example.SeoWooJang_comp228lab5;
}