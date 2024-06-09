package dev.ramimans.refsave.dao.mapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import dev.ramimans.refsave.dto.Book;
import dev.ramimans.refsave.dto.Category;
import dev.ramimans.refsave.dto.Film;
import dev.ramimans.refsave.dto.Reference;
import dev.ramimans.refsave.dto.Tv;
import dev.ramimans.refsave.dto.Website;
import static dev.ramimans.refsave.dao.mapper.ReferenceFieldsExtractor.*;

public class ReferenceMapper implements RowMapper<Reference>{

    @Override
    @Nullable
    public Reference mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Map<String, String> stringFields = getStringFields(rs);
        String id = stringFields.get("id");
        String label = stringFields.get("label");
        String title = stringFields.get("title");
        String notes = stringFields.get("notes");
        LocalDateTime created = getCreatedField(rs);
        String categoryString = rs.getString("category");
        Category category = Category.valueOf(Category.class, categoryString);
        String timepoint = rs.getString("timepoint");
        LocalTime timePoint;
        
        switch (category) {
            case TV:
                timePoint = LocalTime.parse(timepoint, timeFormatter);
                Tv tv = new Tv(id, created, label, title, notes, timePoint);
                return tv;
            case FILM:
                timePoint = LocalTime.parse(timepoint, timeFormatter);
                Film film = new Film(id, created, label, title, notes, timePoint);      
                return film;
            case BOOK:
                int pageNumber = rs.getInt("pageNumber");
                Book book = new Book(id, created, label, title, notes, pageNumber);
                return book;
            case WEBSITE:
                try {
                    String urlString = rs.getString("url");
                    URL url = new URL(urlString);
                    Website website = new Website(id, created, label, title, notes, url);
                    return website;
                } catch (MalformedURLException e) {
                    return new Reference(id, created, label, title, notes);
                }
            default:
                return new Reference(id, created, label, title, notes);
        }
    }
}
    
    

