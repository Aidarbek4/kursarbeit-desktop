package com.example.desktop.windows;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WaiterWindow extends Stage {

    public WaiterWindow() {
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Это окно официанта"));

        Scene scene = new Scene(root, 300, 200);
        setScene(scene);
        setTitle("Окно официанта");
    }
}
