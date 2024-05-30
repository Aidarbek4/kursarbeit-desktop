package com.example.desktop;

import com.example.desktop.windows.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
