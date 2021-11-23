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
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GuiController implements Initializable {

    private boolean unsavedChanges = false;
    private boolean editModeToggle = false;

    // Movement variables.
    private double xOffset = 0;
    private double yOffset = 0;

    private LocalEvent localEventEdit;

    // GUI control declarations.
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonMin;

    @FXML
    private Button buttonClose;

    @FXML
    private MenuButton fileMenuButton;

    @FXML
    Button addButton;

    @FXML
    Button deleteButton;

    @FXML
    Button editButton;

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

    String validateTitle = "Validate Fields";
    // Validates the date picker and description text field with 3 cases.
    private boolean validateFields(String validateTitle) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
            if ((descriptionTextField.getText().isEmpty() || descriptionTextField.getText() == null || descriptionTextField == null) &&
                    (datePicker.getEditor().getText() == null || datePicker.getEditor().getText().isEmpty() || datePicker == null)) {

                alert.setTitle(validateTitle);
                alert.setHeaderText(null);
                alert.setContentText("A description must be entered and a date must be picked.");
                alert.showAndWait();

                return false;
            }

            if (descriptionTextField.getText().isEmpty() || descriptionTextField.getText() == null || descriptionTextField == null) {

                alert.setTitle(validateTitle);
                alert.setHeaderText(null);
                alert.setContentText("A description must be entered.");
                alert.showAndWait();

                return false;
            }

            if (datePicker.getEditor().getText() == null || datePicker.getEditor().getText().isEmpty() || datePicker == null){

                alert.setTitle(validateTitle);
                alert.setHeaderText(null);
                alert.setContentText("A date must be picked.");
                alert.showAndWait();

                return false;
            }

            return true;
    }

    // Makes sure there is an entry being selected.
    private boolean validateSelected() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        int selectedID = eventList.getSelectionModel().getSelectedIndex();
        if (selectedID == -1) {

            alert.setTitle("Validate Selection");
            alert.setHeaderText(null);
            alert.setContentText("A list item must be selected.");
            alert.showAndWait();

            return false;
        }

        return true;
    }

    // Adds to the observable list and get set to the listview.
    @FXML
    private void addEvent() {
        if(validateFields(validateTitle)) {
            listMaster.add(new LocalEvent(datePicker.getValue(), descriptionTextField.getText()));
            eventList.setItems(listMaster);
            refresh();
            unsavedChanges = true;
        }
    }

    // Resets the date picker and text field.
    private void refresh(){

        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText(null);
        descriptionTextField.setStyle("-fx-border-color: white;"+"-fx-background-color: #1b1b1c;"+"-fx-border-insets: 0, 0, 0, 0;"+"-fx-text-fill: white;");
    }

    // Initializes the MenuButton.
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            fileChooser.setInitialDirectory(new File("C:\\users"));
            cbMenu.setItems(menuList);
            eventList.setEditable(false);
        }catch(Exception e)  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TodoFX");
            alert.setHeaderText("Events could not be loaded from file system");
        }
    }

    public void menuSavePicked() {

        // Create a Window variable and set it to the file menu variables getScene method return.
        Window stage = fileMenuButton.getScene().getWindow();

        // Set the title/ initial file name and extension filters for the file chooser.
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("ToDoList");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text", "*.txt"));

        // try to acquire the file path to be saved from the file chooser.
        try {
            File file = fileChooser.showSaveDialog(stage);

            // If the path isn't null set the initial directory as the parent file.
            if (file != null) {

                fileChooser.setInitialDirectory(file.getParentFile());


                // Check if the string ends in a 't'.
                saveFile(file);
            }// Catch any exceptions and print the stack trace.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save method for a .txt file.
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

    public void menuLoadPicked()throws IOException{

        // Create a Window variable and set it to the file menu variables getScene method return.
        Window stage = fileMenuButton.getScene().getWindow();

        // Set the title and try to acquire the file path to be opened from the file chooser.
        fileChooser.setTitle("Load Dialog");
        File file = fileChooser.showOpenDialog(stage);

        // If the path isn't null set the initial directory as the parent file.
        if (file != null) {
            fileChooser.setInitialDirectory(file.getParentFile());

            openFile(file);
        }
    }

    // Opens a .txt file.
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
        if(validateSelected()) {

            int selectedID = eventList.getSelectionModel().getSelectedIndex();
            listMaster.remove(selectedID);
            eventList.setItems(listMaster);
            unsavedChanges = true;
        }
    }

    @FXML
    private void deleteAllEvents() {

        eventList.getItems().clear();
        listMaster.clear();
        unsavedChanges = true;
    }

    // Changes incomplete tasks to complete.
    @FXML
    private void markComplete() {
        if(validateSelected()) {

            int selectedID = eventList.getSelectionModel().getSelectedIndex();
            listMaster.get(selectedID).setCompleted();
            eventList.setItems(listMaster);
            eventList.refresh();
            unsavedChanges = true;
        }
    }
    // Opposite of markComplete.
    @FXML
    private void markIncomplete() {
        if(validateSelected()) {

            int selectedID = eventList.getSelectionModel().getSelectedIndex();
            listMaster.get(selectedID).setIncomplete();
            eventList.setItems(listMaster);
            eventList.refresh();
            unsavedChanges = true;
        }
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

    @FXML
    protected void handleMovementAction(MouseEvent event) {

        // Logic for tracking movement.
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }
    // Hover methods are for styling of buttons.
    @FXML
    public void hoverOnCloseBt(){

        // Sets the color of the button to red when hovering on it.
        buttonClose.setStyle("-fx-background-color: #fc0303;"+"-fx-border-insets: 0, 0, 0, 0;");
    }

    @FXML
    public void hoverOffCloseBt(){

        // Revert to normal when not hovering on the button.
        buttonClose.setStyle("-fx-background-color: black;"+"-fx-border-insets: 0, 0, 0, 0;");
    }

    @FXML
    public void hoverOnMinBt(){

        // Sets the color of the button to red when hovering on it.
        buttonMin.setStyle("-fx-background-color: #fc0303;"+"-fx-border-insets: 0, 0, 0, 0;");
    }

    @FXML
    public void hoverOffMinBt(){

        // Revert to normal when not hovering on the button.
        buttonMin.setStyle("-fx-background-color: black;"+"-fx-border-insets: 0, 0, 0, 0;");
    }

    @FXML
    protected void handleMinAction() {

        // Get the scene of the stage.
        Stage stage = (Stage) buttonMin.getScene().getWindow();

        // Iconify the stage (Minimize).
        stage.setIconified(true);
    }
    // Helper method used in terminate process.
    public boolean displayModalPopup() {

        // Create a new Alert object of type WARNING.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Information to be displayed in warning.
        alert.setTitle("Unsaved data warning");
        alert.setContentText("There is unsaved data. Do you want to continue and lose data ?");

        // Returns true if user clicks OK.
        Optional<ButtonType> result = alert.showAndWait();
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }

    // Terminate process.
    @FXML
    public void handleCloseAction() {

        // First check for any unsaved changes
        if (unsavedChanges) {

            // Set the return of the Unsaved changes Alert to a boolean variable.
            boolean decision = displayModalPopup();

            // If user indeed wishes to close then close.
            if (decision) {
                Stage stage = (Stage) buttonClose.getScene().getWindow();
                stage.close();
            }
            // Else break from the close action.
            if (!decision)
                return;
        }

        // If there weren't any unsaved changes to begin with then just close.
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void handleClickAnchorPaneAction(MouseEvent event) {

        // Logic for clicking on the anchor pane. -> Will be used with handle Movement.
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }
    // 3-step process edit method: Select entry to be edited.
    // Edit selected entry using text box and date picker.
    // Submit the edit.
    @FXML
    private void editEntry(){

        // First check if there is a valid selection {
        if(validateSelected()) {

            // Get the index of the current table selection.
            int index = listMaster.indexOf(eventList.getSelectionModel().getSelectedItem());

            if (editModeToggle) {

                // Set three String variables to the trimmed values of the text fields.
                String description = descriptionTextField.getText().trim();
                LocalDate date = datePicker.getValue();

                // If the text fields are valid and dupCheck passes
                if (validateFields(validateTitle)) {

                    // Get the event strings and set them in their respective text fields.
                    descriptionTextField.setText(description);
                    datePicker.setValue(date);

                    // Set a LocalEvent edit with the given serial number and name values.
                    localEventEdit.setDescription(description);
                    localEventEdit.setDate(date);

                    // Set the boolean edit toggle to false.
                    editModeToggle = false;

                    // Set the edit button to say edit.
                    editButton.setText("Edit");

                    refresh();

                    // Set a boolean variable tracking changes being made to true.
                    unsavedChanges = true;
                }
            }
            else{
                // set the LocalEvent variable to list masters selected index.
                localEventEdit = listMaster.get(index);

                // Set the description Text fields again.
                descriptionTextField.setText(String.valueOf(localEventEdit.getDescription()));
                descriptionTextField.setStyle("-fx-border-color: blue;"+"-fx-background-color: #1b1b1c;"+"-fx-border-insets: 0, 0, 0, 0;"+
                        "-fx-border-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3, 3 3 3 3;"+"-fx-text-fill: white;");
                datePicker.setValue(localEventEdit.getDate());
                // Change edit boolean to true
                editModeToggle = true;

                // Set button text to save
                editButton.setText("Save");
            }
            // Set table to the master list.
            eventList.setItems(listMaster);
            // Refresh the table.
            eventList.refresh();
        }
    }

    // Styles the text field back to its original state.
    @FXML
    protected void handleClickTextField() {

        descriptionTextField.setStyle("-fx-border-color: blue;" + "-fx-background-color: #1b1b1c;" + "-fx-border-insets: 0, 0, 0, 0;" +
                "-fx-border-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3, 3 3 3 3;" + "-fx-text-fill: white;");
    }

    @FXML
    public void sortTasksByDate() {

        // Check if the master list isn't empty
        if (!listMaster.isEmpty()) {

            // if not empty then Create a Comparator variable of type LocalEvent
            // and set it to the comparison of all the dates.
            Comparator<LocalEvent> comparator = Comparator.comparing(LocalEvent::getDate);

            // Sort the master list with comparator variable.
            listMaster.sort(comparator);

            // Set the sorted list.
            eventList.setItems(listMaster);

            // Now since we affected the master list there are unsaved changes.
            unsavedChanges = true;
        }
    }
}

