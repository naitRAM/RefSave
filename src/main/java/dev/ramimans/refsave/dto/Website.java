package dev.ramimans.refsave.dto;

import java.net.URL;
import java.time.LocalDateTime;

public class Website extends Reference {
    
    private URL url;

    public Website () {
        super();
        setCategory(Category.WEBSITE);
    }

    public Website (String id, LocalDateTime created, String label, String title, String notes, URL url) {
        super(id, created, label, title, notes);
        setCategory(Category.WEBSITE);
        this.url = url;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(URL url) {
        this.url = url;
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
            " url='" + getUrl() + "'" +
            "}";
    }


}
