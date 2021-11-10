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
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class GuiController implements Initializable {

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
    Button markIncomplete;

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
    ObservableList<LocalEvent> listMaster = FXCollections.observableArrayList();
    ObservableList<LocalEvent> listFiltered = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> cbMenu;
    ObservableList<String> menuList = FXCollections.observableArrayList("Load", "Save");
    FileChooser fileChooser = new FileChooser();

    private boolean validateFields() {

            if (descriptionTextField.getText().isEmpty() || descriptionTextField.getText() == null ||
                    datePicker.getEditor().getText() == null || datePicker.getEditor().getText().isEmpty() || datePicker == null || descriptionTextField == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("A description must be entered and a date must be picked.");
                alert.showAndWait();

                return false;
            }

            return true;
    }

    @FXML
    private void addEvent() {
        if(validateFields()) {
            listMaster.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
            eventList.setItems(listMaster);
        }
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
            fileChooser.setInitialFileName("myTodo");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));

            try {
                File file = fileChooser.showSaveDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                saveFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (selectionIndex == 0){
            Window stage = cbMenu.getScene().getWindow();
            fileChooser.setTitle("Load Dialog");

            try {
                File file = fileChooser.showOpenDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                openFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFile( File file) throws IOException {

        PrintWriter pw = new PrintWriter(file);
        for (LocalEvent event: listMaster){
            pw.write(event.getDescription());
            pw.write("\n");
            pw.write(event.getDate().toString());
            pw.write("\n");
            pw.write(event.isCompleted()+ "");
            pw.write("\n");
        }
        pw.flush();
        pw.close();
    }

    public void openFile(File file) throws FileNotFoundException {

        Scanner input = new Scanner(file);
        listMaster.clear();
        while (input.hasNextLine()){
            String description = input.nextLine();
            String date = input.nextLine();
            String getCompleted = input.nextLine();
            boolean completed = getCompleted.equalsIgnoreCase("true");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate localDate = LocalDate.parse(date,formatter);
            LocalEvent localEvent = new LocalEvent(localDate,description,completed);

            listMaster.add(localEvent);
        }
        eventList.setItems(listMaster);
    }

    @FXML
    private void deleteSelectedEvent() {

        int selectedID = eventList.getSelectionModel().getSelectedIndex();
        eventList.getItems().remove(selectedID);
        listMaster.remove(selectedID);
    }

    @FXML
    private void deleteAllEvents() {

        eventList.getItems().clear();
        listMaster.clear();
    }

    @FXML
    private void editEvent() {

        eventList.setEditable(true);
        eventList.setCellFactory(new Callback<>() {

            @Override
            public ListCell<LocalEvent> call(ListView<LocalEvent> le) {
                TextFieldListCell<LocalEvent> cell = new TextFieldListCell<>() {

                    @Override
                    public void updateItem(LocalEvent item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                        }
                    }


                    @Override
                    public void commitEdit(LocalEvent newValue) {
                        super.commitEdit(newValue);
                    }
                };

                cell.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER)
                        cell.commitEdit(le.getSelectionModel().getSelectedItem());
                });

                cell.setConverter(new StringConverter<>() {

                    @Override
                    public String toString(LocalEvent object) {
                        return "";
                    }

                    @Override
                    public LocalEvent fromString(String string) {
                        return new LocalEvent(datePicker.getValue(), string, false);
                    }
                });
                return cell;
            }
        });
        eventList.setOnEditCommit(e -> eventList.getItems().set(e.getIndex(), e.getNewValue()));
        eventList.refresh();
    }

    @FXML
    private void markComplete() {

        int selectedID = eventList.getSelectionModel().getSelectedIndex();
        listMaster.get(selectedID).setCompleted();
        eventList.setItems(listMaster);
        eventList.refresh();
    }

    @FXML
    private void markIncomplete() {

        int selectedID = eventList.getSelectionModel().getSelectedIndex();
        listMaster.get(selectedID).setIncomplete();
        eventList.setItems(listMaster);
        eventList.refresh();
    }

    @FXML
    private void displayCompletedTasks(){

        listFiltered.clear();
        for(LocalEvent event: listMaster){
            if(event.isCompleted()){
                listFiltered.add(event);
            }
        }
        eventList.setItems(listFiltered);
    }

    @FXML
    private void displayNonCompletedTasks(){

        listFiltered.clear();
        for(LocalEvent event: listMaster){
            if(!event.isCompleted()){
                listFiltered.add(event);
            }
        }
        eventList.setItems(listFiltered);
    }

    @FXML
    private void displayAllTasks(){

        listFiltered.clear();
        listFiltered.addAll(listMaster);
        eventList.setItems(listFiltered);
    }
}

