package com.example.kpp7.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LibraryViewModel {



    SimpleStringProperty name;
    SimpleStringProperty priority;
    SimpleStringProperty state;
    SimpleStringProperty timeStamp;
    SimpleIntegerProperty id;

    SimpleDateFormat sdf;

    public LibraryViewModel(String name, String priority, String state, int id) {
        this.name = new SimpleStringProperty(name);
        this.priority = new SimpleStringProperty(priority);
        this.state = new SimpleStringProperty(state);
        this.sdf = new SimpleDateFormat("HH:mm:ss");
        this.timeStamp = new SimpleStringProperty(sdf.format(new Date()));
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPriority() {
        return priority.get();
    }

    public SimpleStringProperty priorityProperty() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
        this.timeStamp = new SimpleStringProperty(sdf.format(new Date()));
    }

    public String getTimeStamp() {
        return timeStamp.get();
    }

    public SimpleStringProperty timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp() {

//        this.timeStamp.set(timeStamp);
        this.timeStamp = new SimpleStringProperty(sdf.format(new Date()));
    }

    public int getId(){
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id){
        this.id.set(id);
    }
}
