package dev.ramimans.refsave.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tv extends Reference {

    private LocalTime timepoint;

    public Tv () {
        super();
        setCategory(Category.TV);
    }

    public Tv (String id, LocalDateTime created, String label, String title, String notes, LocalTime timepoint) {
        super(id, created, label, title, notes);
        setCategory(Category.TV);
        this.timepoint = timepoint;
    }


    public LocalTime getTimepoint() {
        return this.timepoint;
    }

    public void setTimepoint(LocalTime timepoint) {
        this.timepoint = timepoint;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", created='" + getCreated() + "'" +
            ", label='" + getLabel() + "'" +
            ", title='" + getTitle() + "'" +
            ", notes='" + getNotes() + "'" +
            ", category='" + getCategory() + "'" +
            " timepoint='" + getTimepoint() + "'" +
            "}";
    }
    

}
