package com.example.desktop.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AdminWindow extends Stage {

    public AdminWindow(String username) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
        setTitle("Окно администратора");

        // Создание боковой панели
        VBox sidePanel = new VBox();
        sidePanel.setPadding(new Insets(10));
        sidePanel.setSpacing(10);

        Label nameLabel = new Label("Привет, " + username + "!");
        nameLabel.setFont(Font.font(16));
        sidePanel.getChildren().add(nameLabel);

        Button foodButton = new Button("Food");
        Button tableButton = new Button("Table");
        Button userButton = new Button("User");
        Button bookButton = new Button("Book");
        Button orderButton = new Button("Order");
        Button paymentButton = new Button("Payment");

        sidePanel.getChildren().addAll(foodButton, tableButton, userButton, bookButton, orderButton, paymentButton);

        // Создание области для вывода списка данных
        VBox dataList = new VBox();
        dataList.setPadding(new Insets(10));

        root.setLeft(sidePanel);
        root.setCenter(dataList);
    }
}
