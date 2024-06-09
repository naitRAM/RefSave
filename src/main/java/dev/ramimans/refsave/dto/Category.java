package dev.ramimans.refsave.dto;

public enum Category {
    TV(1),
    FILM(2),
    BOOK(3),
    WEBSITE(4);

    private int id;

    private Category(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
