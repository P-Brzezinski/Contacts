package main.model;

import main.model.gender.Gender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person extends Entity implements Serializable {

    private static final long serialVersionUID = -8820228665294889973L;
    private String name;
    private String surname;
    private String dateOfBirth;
    private Gender gender;

    public Person(String phoneNumber, LocalDateTime timeCreated, LocalDateTime lastEdit, String name, String surname, String dateOfBirth, Gender gender) {
        super(phoneNumber, timeCreated, lastEdit);
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static class Builder {
        private String name;
        private String surname;
        private String phoneNumber;
        private String dateOfBirth;
        private Gender gender;
        private LocalDateTime timeCreated;
        private LocalDateTime lastEdit;

        public Builder() {
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setDateOfBirth(String dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setGender(Gender gender){
            this.gender = gender;
            return this;
        }

        public Builder setTimeCreated(LocalDateTime timeCreated){
            this.timeCreated = LocalDateTime.now();
            return this;
        }

        public Builder setLastEdit(LocalDateTime lastEdit){
            this.lastEdit = LocalDateTime.now();
            return this;
        }

        public Person build(){
            return new Person(phoneNumber, timeCreated, lastEdit, name, surname, dateOfBirth, gender);
        }
    }

    @Override
    public String getFullName() {
        return this.name + " " + this.surname;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + dateOfBirth + "\n" +
                "Gender: " + gender.getDescription() + "\n" +
                "Number: " + phoneNumber + "\n" +
                "Time created: " + timeCreated.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm")) + "\n" +
                "Time last edit: " + lastEdit.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm"));
    }
}
