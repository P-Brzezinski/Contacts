package main.model.gender;

public enum Gender {

    F ("F"),
    M ("M"),
    NA("[no data]");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Gender genderFromString(String word){
        for (Gender value : Gender.values()) {
            if (value.getDescription().equals(word.toUpperCase())){
                return value;
            }
        }
        return null;
    }
}
