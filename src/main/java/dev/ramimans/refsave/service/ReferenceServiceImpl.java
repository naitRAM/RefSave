package dev.ramimans.refsave.service;

import dev.ramimans.refsave.dao.ReferenceDao;
import dev.ramimans.refsave.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReferenceServiceImpl implements ReferenceService{
    @Autowired
    ReferenceDao referenceDao;

    public ReferenceServiceImpl (ReferenceDao referenceDao){
        this.referenceDao = referenceDao;
    }

    public List<Reference> readReferences(String username) {
        return referenceDao.readReferences(username);
    }

    public List<Reference> readReferencesByCategory(String username, Category category) {
        return referenceDao.readReferencesByCategory(username, category);
    }

    public Reference readReference(String username, String refId){
        return referenceDao.readReference(username, refId);
    }

    public TV createRef(String username, TV tv) {
        return referenceDao.createRef(username, tv);
    }

    public Film createRef(String username, Film film) {
        return referenceDao.createRef(username, film);
    }

    public Book createRef(String username, Book book) {
        return referenceDao.createRef(username, book);
    }

    public Website createRef(String username, Website website) {
        return referenceDao.createRef(username, website);
    }

    public void updateRef(String username, TV tv) {
        referenceDao.updateRef(username, tv);
    }

    public void updateRef(String username, Film film) {
        referenceDao.updateRef(username, film);
    }

    public void updateRef(String username, Book book) {
        referenceDao.updateRef(username, book);
    }

    public void updateRef(String username, Website website) {
        referenceDao.updateRef(username, website);
    }

    public void deleteRef(String username, Reference ref) {
        referenceDao.deleteRef(username, ref);
    }

}
