package org.iconia.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tournaments")
public class IntTournament {

        @DatabaseField(generatedId = true)
        private int id;

        @DatabaseField
        private String name;

        @DatabaseField
        private String date; // Use a string or java.util.Date

        @DatabaseField
        private int maxSize;


        // for the sql
        public IntTournament() {}

        public IntTournament(String name, String date) {
                this.name = name;
                this.date = date;
        }

        // Getters and setters
        public int getId() { return id; }

        public void setId(int id) { this.id = id; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }


        public String getDate() { return date; }

        public void setDate(String date) { this.date = date; }


    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
