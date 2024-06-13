package dev.ramimans.refsave.service;

import dev.ramimans.refsave.dto.*;

import java.util.List;

public interface ReferenceService {
    public List<Reference> readReferences(String username);
    public List<Reference> readReferencesByCategory(String username, Category category);
    public Reference readReference(String username, String refId);
    public TV createRef(String username, TV tv);
    public Film createRef(String username, Film film);
    public Book createRef(String username, Book book);
    public Website createRef(String username, Website website);
    public void updateRef(String username, TV tv);
    public void updateRef(String username, Film film);
    public void updateRef(String username, Book book);
    public void updateRef(String username, Website website);
    public void deleteRef(String username, Reference ref);
}
