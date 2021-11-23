/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Tyler King
 */

package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class TodoListApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TodoList-2.fxml")));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 1150, 700);
        scene.getStylesheets().add((Objects.requireNonNull(getClass().getResource("Application-Style.css"))).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Todo List");
        stage.show();
    }
}
