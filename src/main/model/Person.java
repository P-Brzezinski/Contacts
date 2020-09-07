package main.model;

public class Person {

    private String name;
    private String surname;
    private String phoneNumber;

    private Person(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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

    public static class Builder {
        private String name;
        private String surname;
        private String phoneNumber;

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

        public Person build(){
            return new Person(name, surname, phoneNumber);
        }
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + phoneNumber;
    }
}
