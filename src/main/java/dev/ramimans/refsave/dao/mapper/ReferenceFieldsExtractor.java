package dev.ramimans.refsave.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import dev.ramimans.refsave.dto.Reference;

public class ReferenceFieldsExtractor {
    
    private static final String ID = "id";
    private static final String LABEL = "label";
    private static final String TITLE = "title";
    private static final String NOTES = "notes";
    private static final String CREATED = "created";
    public static final String TIMEPOINT = "timepoint";
    public static final String PAGE_NUMBER = "page_number";
    public static final String URL = "url";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static Map<String, String> getStringFields(ResultSet rs) throws SQLException {
        String[] keys = {ID, LABEL, TITLE, NOTES};
        Map<String, String> result = new HashMap<>();
        for (int i=0; i < keys.length; i++) {
            result.put(keys[i], rs.getString(keys[i]));
        }
        return result;
    }

    public static LocalDateTime getCreatedField(ResultSet rs) throws SQLException {
        return LocalDateTime.parse(rs.getString("created"), dateTimeFormatter);
    }

    public static Map<String, String> getNewRefFields(Reference ref) {
        String[] keys = {ID, LABEL, TITLE, NOTES, CREATED};
        String[] values = {ref.getId(), ref.getLabel(), ref.getTitle(), ref.getNotes(), ref.getCreated().format(dateTimeFormatter)};
        return getFieldsMap(keys, values);
    }

    public static Map<String, String> getUpdateRefFields(Reference ref) {
        String[] keys = {LABEL, TITLE, NOTES};
        String[] values = {ref.getLabel(), ref.getTitle(), ref.getNotes()};
        return getFieldsMap(keys, values);
    }

    private static Map<String, String> getFieldsMap(String[] keys, String[] values){
        Map<String, String> result = new HashMap<>();
        for (int i=0; i < keys.length; i++) {
            result.put(keys[i], values[i]);
        }
        return result;
    }


}
