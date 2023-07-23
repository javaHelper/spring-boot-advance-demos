package com.example.speldemoxml.data;

public class User {

    private String name;

    private int age;

    private String country;

    private String language;
    private String timeZone;

    public User( String country, String language) {
        this.country = country;
        this.language = language;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) { //setter method wiring
        this.timeZone = timeZone;
    }
}
