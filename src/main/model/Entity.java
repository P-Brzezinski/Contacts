package main.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -238420333375239616L;
    protected String phoneNumber;
    protected LocalDateTime timeCreated;
    protected LocalDateTime lastEdit;

    public Entity(String phoneNumber, LocalDateTime timeCreated, LocalDateTime lastEdit) {
        this.phoneNumber = phoneNumber;
        this.timeCreated = timeCreated;
        this.lastEdit = lastEdit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

    public abstract String getFullName();
}
