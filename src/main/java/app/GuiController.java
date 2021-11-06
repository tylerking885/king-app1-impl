/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Tyler King
 */

package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class GuiController implements Initializable {
    private boolean unsavedChanges = false;

    @FXML
    Button addButton;

    @FXML
    Button deleteButton;

    @FXML
    Button deleteAllButton;

    @FXML
    Button editButton;

    @FXML
    Button markButton;

    @FXML
    Button displayMarkButton;

    @FXML
    Button displayNoMarkButton;

    @FXML
    Button displayAllButton;

    @FXML
    TextField descriptionTextField;

    @FXML
    DatePicker datePicker;

    @FXML
    ListView<LocalEvent> eventList;
    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> cbMenu;

    ObservableList<String> menuList = FXCollections.observableArrayList("Load", "Save");

    FileChooser fileChooser = new FileChooser();

    @FXML
    private void addEventHandler() {

        // TODO: code that will an item to a list view object when the user chooses the add button.
    }

    private void refresh(){
        // TODO: code that will refresh the datePicker control and textField box.

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO: code that initializes the file chooser object and comboBox menu.
    }
    @FXML
    private void comboChanged() {

        // TODO: code that will open the file chooser based on if the user picked load or save.
    }
    public void saveFile(File file) throws IOException {

        // TODO: code for a helper method that saves the todo list to a txt file.
    }

    public void openFile(File file) throws FileNotFoundException {
        // TODO: code for a helper method that opens a file.
    }

    @FXML
    private void deleteSelectedEvent() {
        // TODO: code for deleting a particular event.
    }

    @FXML
    private void deleteAllEvents() {
        // TODO: code for deleting every event.
    }

    @FXML
    private void editEvent() {

        // TODO: code for editing event.
    }

    @FXML
    private void mark() {

        // TODO: code for marking event completed.
    }

    @FXML
    private void displayCompletedTasks(){
        // TODO: Code for filtering a list down to just completed tasks.
    }

    @FXML
    private void displayNonCompletedTasks(){
        // TODO: Code for filtering a list down to just non completed tasks.
    }

    @FXML
    private void displayAll(){
        // TODO: Code for repopulating a list with every entry.
    }

}
