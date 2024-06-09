package dev.ramimans.refsave.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book extends Reference{
    

    private int pageNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;
        return pageNumber == book.pageNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageNumber);
    }

    public Book () {
        super();
        setCategory(Category.BOOK);
    }

    public Book (String id, LocalDateTime created, String label, String title, String notes, int pageNumber) {
        super(id, created, label, title, notes);
        setCategory(Category.BOOK);
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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
            " pageNumber='" + getPageNumber() + "'" +
            "}";
    }

    
}
