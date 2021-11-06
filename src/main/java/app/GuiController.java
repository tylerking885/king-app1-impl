/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Tyler King
 */

package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;

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

        list.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
        eventList.setItems(list);
        refresh();

        unsavedChanges = true;
    }

    private void refresh(){
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText(null);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            fileChooser.setInitialDirectory(new File("C:\\users"));
            cbMenu.setItems(menuList);
        }catch(Exception e)  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TodoFX");
            alert.setHeaderText("Events could not be loaded from file system");
        }
    }

    public void comboChanged() {

        int selectionIndex = cbMenu.getSelectionModel().getSelectedIndex();

        if (selectionIndex == 1) {
            Window stage = cbMenu.getScene().getWindow();
            fileChooser.setTitle("Save Dialog");
            fileChooser.setInitialFileName("mytodo");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));

            try {
                File file = fileChooser.showSaveDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                saveFile(file);
            } catch (Exception ignored) {

            }
        }
        else if (selectionIndex == 0){
            Window stage = cbMenu.getScene().getWindow();
            fileChooser.setTitle("Load Dialog");

            try {
                File file = fileChooser.showOpenDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                openFile(file);
            } catch (Exception ignored) {

            }
        }
    }
    public void saveFile( File file) throws IOException {

        PrintWriter pw = new PrintWriter(file);
        ObservableList<LocalEvent> list = eventList.getItems();
        for (LocalEvent event: list){
            pw.write(event.getDescription());
            pw.write("\n");
            pw.write(event.getDate().toString());
            pw.write("\n");
            pw.write(event.getCompleted()+ "");
            pw.write("\n");
        }
        pw.flush();
        pw.close();
    }

    public void openFile(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        while (input.hasNextLine()){
            String description = input.nextLine();
            String date = input.nextLine();
            boolean getCompleted = input.nextBoolean();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate localDate = LocalDate.parse(date,formatter);
            LocalEvent localEvent = new LocalEvent(localDate,description,getCompleted);
            list.addAll(localEvent);
            eventList.setItems(list);
        }
    }

    @FXML
    private void deleteSelectedEvent() {
        int selectedID = eventList.getSelectionModel().getSelectedIndex();
        eventList.getItems().remove(selectedID);
        unsavedChanges = true;
    }

    @FXML
    private void deleteAllEvents() {
        eventList.getItems().clear();
        unsavedChanges = true;
    }

    @FXML
    private void editEvent() {
        unsavedChanges = true;
        // TODO: code for editing event.
    }

    @FXML
    void mark() {

        int selectedID = eventList.getSelectionModel().getSelectedIndex();
        list.get(selectedID).setCompleted();
        eventList.setItems(list);

        unsavedChanges = true;
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
