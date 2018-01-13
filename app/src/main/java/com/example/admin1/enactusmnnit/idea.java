package com.example.admin1.enactusmnnit;

/**
 * Created by admin1 on 14-05-2017.
 */
public class idea {
    String Name;
    String Year;
    String Idea;

    public idea() {
    }

    public idea(String name, String year, String idea) {
        this.Name = name;
        this.Year = year;
        this.Idea = idea;
    }

    public String getName() {
        return Name;
    }

    public String getYear() {
        return Year;
    }

    public String getIdea() {
        return Idea;
    }
}
