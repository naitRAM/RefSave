package dev.ramimans.refsave.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import dev.ramimans.refsave.dao.mapper.ReferenceMapper;
import dev.ramimans.refsave.dto.Book;
import dev.ramimans.refsave.dto.Category;
import dev.ramimans.refsave.dto.Film;
import dev.ramimans.refsave.dto.Reference;
import dev.ramimans.refsave.dto.Tv;
import dev.ramimans.refsave.dto.Website;
import static dev.ramimans.refsave.dao.mapper.ReferenceFieldsExtractor.*;

@Component
public class ReferenceDaoImpl {
    @Autowired
    JdbcTemplate jdbc;

    public ReferenceDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final String GET_ALL_USER_REFS = "Select Ref.id AS id, Ref.created AS created, Ref.label AS label,"
                + " Ref.title AS title, Ref.notes AS notes, Ref.url AS url, Ref.timepoint AS timepoint,"
                + " Ref.page_number AS pageNumber, UPPER(Category.title) As category FROM Ref Left Join Users ON Ref.user_id = Users.id"
                + " Left Join Category ON Ref.category_id = category.id WHERE Users.username = ? ";
    private final String CREATE_PREFIX = "INSERT INTO Ref (id, label, title, notes, created, ";
    private final String CREATE_SUFFIX = ", category_id, user_id) " + 
        "VALUES (?,?,?,?,?,?,?,(SELECT id FROM Users WHERE username = ?));";
    private final String UPDATE_PREFIX = "UPDATE Ref SET label = ?, title = ?, notes = ?, ";
    private final String UPDATE_SUFFIX = " = ? WHERE id = ? AND user_id = (SELECT user_id from users WHERE username = ?);";

    public List<Reference> readReferences(String username) {
        String query = GET_ALL_USER_REFS;
        List<Reference> userRefs = jdbc.query(query, new ReferenceMapper(), username);
        return userRefs;
    }

    public List<Reference> readReferencesByCategory(String username, Category category) {
        String query = GET_ALL_USER_REFS + "AND UPPER(Category.title) = ?"; 
        List<Reference> userRefs = jdbc.query(query, new ReferenceMapper(), username, category.name());
        return userRefs;
    }

    public Reference readReference(String username, String refId) {
        String query = GET_ALL_USER_REFS + "AND Ref.id = ?";
        Reference userRef = jdbc.queryForObject(query, new ReferenceMapper(), username, refId);
        return userRef;
    }

    public Tv createRef(String username, Tv tv) {
        Map<String, String> fields = getNewRefFields(tv);
        String query = CREATE_PREFIX + TIMEPOINT + CREATE_SUFFIX; 
        jdbc.update(query, fields.get("id"), fields.get("label"), fields.get("title"), 
            fields.get("notes"), fields.get("created"), 
            tv.getTimepoint().format(timeFormatter), 
            Category.TV.getId(), username);
        return tv;
    }

    public Film createRef(String username, Film film) {
        Map<String, String> fields = getNewRefFields(film);
        String query = CREATE_PREFIX + TIMEPOINT + CREATE_SUFFIX;
        jdbc.update(query, fields.get("id"), fields.get("label"), fields.get("title"), fields.get("notes"), 
            fields.get("created"), film.getTimepoint().format(timeFormatter), 
            Category.FILM.getId(),  username);
        return film;
    }

    public Book createRef(String username, Book book) {
        Map<String, String> fields = getNewRefFields(book);
        String query = CREATE_PREFIX + PAGE_NUMBER + CREATE_SUFFIX;
        jdbc.update(query, fields.get("id"), fields.get("label"), fields.get("title"), fields.get("notes"), 
            fields.get("created"), book.getPageNumber(), Category.BOOK.getId(), username);
        return book;
    }

    public Website createRef(String username, Website website) {
        Map<String, String> fields = getNewRefFields(website);
        String query = CREATE_PREFIX + URL + CREATE_SUFFIX;
        String url = website.getUrl().toString();
        jdbc.update(query, fields.get("id"), fields.get("label"), fields.get("title"), fields.get("notes"), 
            fields.get("created"), url, Category.WEBSITE.getId(), username);
        return website;
    }

    public void updateRef(String username, Tv tv) {
        Map<String, String> fields = getNewRefFields(tv);
        String query = UPDATE_PREFIX + TIMEPOINT + UPDATE_SUFFIX;
        jdbc.update(query, fields.get("label"), fields.get("title"), fields.get("notes"), tv.getTimepoint(),
            tv.getId(), username);
    }

    public void updateRef(String username, Film film) {
        Map<String, String> fields = getNewRefFields(film);
        String query = UPDATE_PREFIX + TIMEPOINT + UPDATE_SUFFIX;
        jdbc.update(query, fields.get("label"), fields.get("title"), fields.get("notes"), film.getTimepoint(),
            film.getId(), username);
    }

    public void updateRef(String username, Book book) {
        Map<String, String> fields = getNewRefFields(book);
        String query = UPDATE_PREFIX + PAGE_NUMBER + UPDATE_SUFFIX;
        jdbc.update(query, fields.get("label"), fields.get("title"), fields.get("notes"), book.getPageNumber(),
            book.getId(), username);
    }

    public void updateRef(String username, Website website) {
        Map<String, String> fields = getNewRefFields(website);
        String query = UPDATE_PREFIX + URL + UPDATE_SUFFIX;
        jdbc.update(query, fields.get("label"), fields.get("title"), fields.get("notes"), website.getUrl().toString(), 
            website.getId(), username);
    }

    public void deleteRef(String username, Reference ref) {
        final String query ="DELETE FROM Ref WHERE user_id = (SELECT ID FROM Users WHERE username = ?) AND id = ?";
        jdbc.update(query, username, ref.getId());
    }



}
