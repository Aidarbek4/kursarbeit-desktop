package com.example.desktop.windows;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminWindow extends Stage {

    public AdminWindow() {
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Это окно администратора"));

        Scene scene = new Scene(root, 300, 200);
        setScene(scene);
        setTitle("Окно администратора");
    }
}
