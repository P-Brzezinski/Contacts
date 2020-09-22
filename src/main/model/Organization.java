package main.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Organization extends Entity implements Serializable {

    private static final long serialVersionUID = -4184779018757712132L;
    private String organizationName;
    private String organizationAddress;

    public Organization(String phoneNumber, LocalDateTime timeCreated, LocalDateTime lastEdit, String organizationName, String organizationAddress) {
        super(phoneNumber, timeCreated, lastEdit);
        this.organizationName = organizationName;
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public static class Builder {
        private String organizationName;
        private String organizationAddress;
        private LocalDateTime timeCreated;
        private LocalDateTime lastEdit;
        private String phoneNumber;

        public Builder setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
            return this;
        }

        public Builder setOrganizationAddress(String organizationAddress) {
            this.organizationAddress = organizationAddress;
            return this;
        }

        public Builder setTimeCreated(LocalDateTime timeCreated) {
            this.timeCreated = LocalDateTime.now();
            return this;
        }

        public Builder setLastEdit(LocalDateTime lastEdit) {
            this.lastEdit = LocalDateTime.now();
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Organization build() {
            return new Organization(phoneNumber, timeCreated, lastEdit, organizationName, organizationAddress);
        }
    }

    @Override
    public String getFullName() {
        return this.organizationName;
    }

    @Override
    public String toString() {
        return "Organization name: " + organizationName + "\n" +
                "Address: " + organizationAddress + "\n" +
                "Number: " + phoneNumber + "\n" +
                "Time created: " + timeCreated.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm")) + "\n" +
                "Time last edit: " + lastEdit.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm"));
    }
}
