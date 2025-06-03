package org.iconia.persistence;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tournaments")
public class IntTournament {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(unique = true)
    private String name;

    @DatabaseField
    private String date; // Use a string or java.util.Date

    @DatabaseField
    private int maxSize;

    @DatabaseField
    private String description;



    // for the sql
    public IntTournament() {
    }

    public IntTournament(String name, String date, String description, int maxSize) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.maxSize = maxSize;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValid() {
        return name != null && !name.isEmpty() && date != null && !date.isEmpty() && maxSize > 0;
    }

}
