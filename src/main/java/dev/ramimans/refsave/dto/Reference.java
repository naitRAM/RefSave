package dev.ramimans.refsave.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reference {

    protected String id;
    protected LocalDateTime created;
    protected String label;
    protected String title;
    protected String notes;
    protected Category category;

    public Reference () {

    }

    public Reference(String id, LocalDateTime created, String label, String title, String notes) {
        this.id = id;
        this.created = created;
        this.label = label;
        this.title = title;
        this.notes = notes;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference reference)) return false;
        return Objects.equals(id, reference.id) && Objects.equals(label, reference.label) && Objects.equals(title, reference.title) && Objects.equals(notes, reference.notes) && category == reference.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, title, notes, category);
    }
}
