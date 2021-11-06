/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Tyler King
 */

package app;

import java.time.LocalDate;

public class LocalEvent {

    // Variables used in the To do list.
    private LocalDate date;
    private String description;
    private boolean completed = false;

    // Getters and Setters for date, description, and completed.
    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public boolean isCompleted(){

        return completed;
    }

    public void setCompleted() {

        this.completed= true;
    }

    public void setIncomplete() {

        this.completed = false;
    }

    // LocalEvent Constructor.
    public LocalEvent(LocalDate date, String description){

        this.setDate(date);
        this.setDescription(description);
    }
    // LocalEvent Constructor with boolean completed.
    public LocalEvent(LocalDate date, String description, boolean completed){

        this.setDate(date);
        this.setDescription(description);
        this.completed = completed;
    }

    // Overriding toString method so Display message is in a format that makes sense.
    @Override
    public String toString(){

        String output = "    Status: Incomplete";

        if (completed) {
             output = "    Status: Completed";
        }
        return "At: " + this.getDate() + "      " + this.getDescription() + output;
    }
}
