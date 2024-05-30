package com.example.desktop.windows;

import com.example.desktop.ServerConnector;
import com.example.desktop.models.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class LoginWindow extends Stage {
    private static final String USERS_URL = ServerConnector.SERVER_URL + "api/v1/users/";

    private ServerConnector serverConnector;

    public LoginWindow() {
        this.serverConnector = new ServerConnector();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label usernameLabel = new Label("Имя пользователя:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Пароль:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Войти");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = authenticate(username, password);
            if (user != null) {
                openMainWindow(user);
                close();
            } else {
                showError("Ошибка", "Неверное имя пользователя или пароль.");
            }
        });

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        Scene scene = new Scene(grid, 300, 150);
        setScene(scene);
        setTitle("Авторизация");
    }

    private User authenticate(String username, String password) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(USERS_URL)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONArray usersArray = new JSONArray(responseBody);
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject userObject = usersArray.getJSONObject(i);
                    String userName = userObject.getString("username");
                    String role = userObject.getString("role");
                    if (userName.equals(username)) {
                        return new User(userName, role);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void openMainWindow(User user) {
        String role = user.getRole();
        if (role.equals("Admin")) {
            // Открыть окно для админа
            AdminWindow adminWindow = new AdminWindow(user.getUsername());
            adminWindow.show();
        } else if (role.equals("Waiter")) {
            // Открыть окно для официанта
            WaiterWindow waiterWindow = new WaiterWindow();
            waiterWindow.show();
        } else {
            showError("Ошибка", "Неизвестная роль пользователя.");
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
