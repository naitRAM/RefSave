package dev.ramimans.refsave.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import dev.ramimans.refsave.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ramimans.refsave.dao.ReferenceDaoImpl;

@RequestMapping("/{username}/")
@RestController
public class ReferenceController {

    @Autowired
    ReferenceDaoImpl refDao;
    public ReferenceController(ReferenceDaoImpl refDao) {
        this.refDao = refDao;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reference> readReference(@PathVariable String username, @PathVariable String id) {
        return new ResponseEntity<Reference>(refDao.readReference(username, id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Reference>> readReferences(@PathVariable String username) {
        return new ResponseEntity<List<Reference>>(refDao.readReferences(username), HttpStatus.OK);
    }
    @GetMapping("/book")
    public ResponseEntity<List<Reference>> readBooks(@PathVariable String username) {
        Category book = Category.BOOK;
        return new ResponseEntity<List<Reference>>(refDao.readReferencesByCategory(username, book), HttpStatus.OK);
    }
    @GetMapping("/web")
    public ResponseEntity<List<Reference>> readWebsites(@PathVariable String username) {
        Category web = Category.WEBSITE;
        return new ResponseEntity<List<Reference>>(refDao.readReferencesByCategory(username, web), HttpStatus.OK);
    }
    @GetMapping("/tv")
    public ResponseEntity<List<Reference>> readTvs(@PathVariable String username) {
        Category tv = Category.TV;
        return new ResponseEntity<List<Reference>>(refDao.readReferencesByCategory(username, tv), HttpStatus.OK);
    }
    @GetMapping("/film")
    public ResponseEntity<List<Reference>> readFilms(@PathVariable String username) {
        Category film = Category.FILM;
        return new ResponseEntity<List<Reference>>(refDao.readReferencesByCategory(username, film), HttpStatus.OK);
    }
    @PostMapping("/book")
    public ResponseEntity<Book> createRef(@PathVariable String username, @RequestBody Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setCreated(LocalDateTime.now());
        Book createdBook = refDao.createRef(username, book);
        return new ResponseEntity<Book>(createdBook, HttpStatus.CREATED);
    }
    @PostMapping("/web")
    public ResponseEntity<Website> createRef(@PathVariable String username, @RequestBody Website website) {
        website.setId(UUID.randomUUID().toString());
        website.setCreated(LocalDateTime.now());
        Website createdWebsite = refDao.createRef(username, website);
        return new ResponseEntity<Website>(createdWebsite, HttpStatus.CREATED);
    }
    @PostMapping("/tv")
    public ResponseEntity<TV> createRef(@PathVariable String username, @RequestBody TV tv) {
        tv.setId(UUID.randomUUID().toString());
        tv.setCreated(LocalDateTime.now());
        TV createdTV = refDao.createRef(username, tv);
        return new ResponseEntity<TV>(createdTV, HttpStatus.CREATED);
    }
    @PostMapping("/film")
    public ResponseEntity<Film> createdRef(@PathVariable String username, @RequestBody Film film) {
        film.setId(UUID.randomUUID().toString());
        film.setCreated(LocalDateTime.now());
        Film createdFilm = refDao.createRef(username, film);
        return new ResponseEntity<Film>(createdFilm, HttpStatus.CREATED);
    }
    @PutMapping("/book")
    public ResponseEntity<Book> updateRef(@PathVariable String username, @RequestBody Book book) {
        Reference stored = refDao.readReference(username, book.getId());
        book.setCreated(stored.getCreated());
        refDao.updateRef(username, book);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/web") 
    public ResponseEntity<Website> updateRef(@PathVariable String username, @RequestBody Website website){
        Reference stored = refDao.readReference(username, website.getId());
        website.setCreated(stored.getCreated());
        refDao.updateRef(username, website);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/tv")
    public ResponseEntity<TV> updateRef(@PathVariable String username, @RequestBody TV tv) {
        Reference stored = refDao.readReference(username, tv.getId());
        tv.setCreated(stored.getCreated());
        refDao.updateRef(username, tv);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/film")
    public ResponseEntity<Film> updateRef(@PathVariable String username, @RequestBody Film film) {
        Reference stored = refDao.readReference(username, film.getId());
        film.setCreated(stored.getCreated());
        refDao.updateRef(username, film);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Reference> deleteRef(@PathVariable String username, @PathVariable String id) {
        Reference stored = refDao.readReference(username, id);
        refDao.deleteRef(username, stored);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    
}
