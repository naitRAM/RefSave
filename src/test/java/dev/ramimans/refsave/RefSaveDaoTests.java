package dev.ramimans.refsave;
import dev.ramimans.refsave.dao.ReferenceDao;
import dev.ramimans.refsave.dao.ReferenceDaoImpl;
import dev.ramimans.refsave.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
public class RefSaveDaoTests {
    @Autowired
    ReferenceDao referenceDao;
    private String TEST_USER="Majid";
    @BeforeEach
    public void setUp() {
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        for (Reference ref : allReferences) {
            referenceDao.deleteRef(TEST_USER, ref);
        }
    }
    @Test
    public void testAddGetBook() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "A Homer reference";
        String title = "The Odyssey of Homer";
        String notes = "That was epic!";
        int pageNumber = 195;
        Book book = new Book(id, created, label, title, notes, pageNumber);
        referenceDao.createRef(TEST_USER, book);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("book is not equal to retrieved reference", reference, book);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }
    @Test
    public void testAddGetTV() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "Mr Robot opening scene";
        String title = "Mr Robot S01E01";
        String notes = "this gave me goosebumps!";
        String timepoint = "00:00:00";
        TV tv = new TV(id, created, label, title, notes, LocalTime.parse(timepoint));
        referenceDao.createRef(TEST_USER, tv);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("tv is not equal to retrieved reference", reference, tv);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }
    @Test
    public void TestAddGetFilm() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "A Lord of the Ring reference";
        String title = "Fellowship of the Ring";
        String notes = "epic scene!";
        String timepoint = "02:04:38";
        Film film = new Film(id, created, label, title, notes, LocalTime.parse(timepoint));
        referenceDao.createRef(TEST_USER, film);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("film is not equal to retrieved reference", reference, film);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }
    @Test
    public void TestAddGetWebsite() throws MalformedURLException {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "A MySQL Docs reference";
        String title = null;
        String notes = "this is the coolest website ever!";
        URL url = new URL("https://dev.mysql.com/doc");
        Website website = new Website(id, created, label, title, notes, url);
        referenceDao.createRef(TEST_USER, website);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("website is not equal to retrieved reference", reference, website);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }
    @Test
    public void testGetAllReferences() throws MalformedURLException {
        String idBook = UUID.randomUUID().toString();
        String idTV = UUID.randomUUID().toString();
        String idFilm = UUID.randomUUID().toString();
        String idWebsite = UUID.randomUUID().toString();
        LocalDateTime createdBook = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdTV = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdFilm = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdWebsite = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String labelBook = "A Homer reference";
        String labelTV = "Mr Robot opening scene";
        String labelFilm = "A Lord of the Ring reference";
        String labelWebsite = "A MySQL Docs reference";
        String titleBook = "The Odyssey of Homer";
        String titleTV = "Mr Robot S01E01";
        String titleFilm = "The Fellowship of the Ring";
        String titleWebsite = null;
        String notesBook = "That was epic!";
        String notesTV = "this gave me goosebumps";
        String notesFilm = "epic scene!";
        String notesWebsite = "this is the coolest website ever!";
        int pageNumber = 195;
        String timepointTV = "00:00:00";
        String timepointFilm = "02:04:38";
        URL url = new URL("https://dev.mysql.com/doc");
        Book book = new Book(idBook, createdBook, labelBook, titleBook, notesBook, pageNumber);
        TV tv = new TV(idTV, createdTV, labelTV, titleTV, notesTV, LocalTime.parse(timepointTV));
        Film film = new Film(idFilm, createdFilm, labelFilm, titleFilm, notesFilm, LocalTime.parse(timepointFilm));
        Website website = new Website(idWebsite, createdWebsite, labelWebsite, titleWebsite, notesWebsite, url);
        referenceDao.createRef(TEST_USER, book);
        referenceDao.createRef(TEST_USER, tv);
        referenceDao.createRef(TEST_USER, film);
        referenceDao.createRef(TEST_USER, website);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("Size of allReferences is not 4", allReferences.size(), 4);
        assertTrue("allReferences does not contain book", allReferences.contains(book));
        assertTrue("allReferences does not contain tv", allReferences.contains(tv));
        assertTrue("allReferences does not contain film", allReferences.contains(film));
        assertTrue("allReferences does not contain website", allReferences.contains(website));
    }
    @Test
    public void TestGetByCategory() throws MalformedURLException {
        String idBook = UUID.randomUUID().toString();
        String idTV = UUID.randomUUID().toString();
        String idFilm = UUID.randomUUID().toString();
        String idWebsite = UUID.randomUUID().toString();
        LocalDateTime createdBook = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdTV = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdFilm = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdWebsite = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String labelBook = "A Homer reference";
        String labelTV = "Mr Robot opening scene";
        String labelFilm = "A Lord of the Ring reference";
        String labelWebsite = "A MySQL Docs reference";
        String titleBook = "The Odyssey of Homer";
        String titleTV = "Mr Robot S01E01";
        String titleFilm = "The Fellowship of the Ring";
        String titleWebsite = null;
        String notesBook = "That was epic!";
        String notesTV = "this gave me goosebumps";
        String notesFilm = "epic scene!";
        String notesWebsite = "this is the coolest website ever!";
        int pageNumber = 195;
        String timepointTV = "00:00:00";
        String timepointFilm = "02:04:38";
        URL url = new URL("https://dev.mysql.com/doc");
        Book book = new Book(idBook, createdBook, labelBook, titleBook, notesBook, pageNumber);
        TV tv = new TV(idTV, createdTV, labelTV, titleTV, notesTV, LocalTime.parse(timepointTV));
        Film film = new Film(idFilm, createdFilm, labelFilm, titleFilm, notesFilm, LocalTime.parse(timepointFilm));
        Website website = new Website(idWebsite, createdWebsite, labelWebsite, titleWebsite, notesWebsite, url);

        referenceDao.createRef(TEST_USER, book);
        referenceDao.createRef(TEST_USER, tv);
        referenceDao.createRef(TEST_USER, film);
        referenceDao.createRef(TEST_USER, website);

        List<Reference> books = referenceDao.readReferencesByCategory(TEST_USER, Category.BOOK);
        assertTrue("books does not contain book", books.contains(book));
        assertEquals("size of books is not 1", books.size(), 1);

        List<Reference> tvs = referenceDao.readReferencesByCategory(TEST_USER, Category.TV);
        assertTrue("tvs does not contain tv", tvs.contains(tv));
        assertEquals("size of tvs is not 1", tvs.size(), 1);

        List<Reference> films = referenceDao.readReferencesByCategory(TEST_USER, Category.FILM);
        assertTrue("films does not contain film", films.contains(film));
        assertEquals("size of films is not 1", films.size(), 1);

        List<Reference> websites = referenceDao.readReferencesByCategory(TEST_USER, Category.WEBSITE);
        assertTrue("websites does not contain website", websites.contains(website));
        assertEquals("size of websites is not 1", websites.size(), 1);
    }

    @Test
    public void testUpdateBook() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "A Homer reference";
        String title = "The Odyssey of Homer";
        String notes = "That was epic!";
        int pageNumber = 195;
        Book book = new Book(id, created, label, title, notes, pageNumber);
        referenceDao.createRef(TEST_USER, book);
        book.setNotes("That was an epic battle!");
        book.setPageNumber(145);
        referenceDao.updateRef(TEST_USER, book);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("book is not equal to updated reference", reference, book);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }

    @Test
    public void testUpdateTV() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "Mr Robot opening scene";
        String title = "Mr Robot S01E01";
        String notes = "this gave me goosebumps!";
        String timepoint = "00:00:00";
        TV tv = new TV(id, created, label, title, notes, LocalTime.parse(timepoint));
        referenceDao.createRef(TEST_USER, tv);
        tv.setLabel("Mr Robot season 1 finale");
        tv.setTitle("Mr Robot S01E10");
        tv.setNotes("Don't open the trunk!!!!");
        tv.setTimepoint(LocalTime.parse("00:48:29"));
        referenceDao.updateRef(TEST_USER, tv);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("tv is not equal to updated reference", reference, tv);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }

    @Test
    public void testUpdateFilm() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "A Lord of the Ring reference";
        String title = "Fellowship of the Ring";
        String notes = "epic scene!";
        String timepoint = "02:04:38";
        Film film = new Film(id, created, label, title, notes, LocalTime.parse(timepoint));
        referenceDao.createRef(TEST_USER, film);
        film.setNotes("epic scene, Boromir redeems himself!");
        film.setTimepoint(LocalTime.parse("03:04:38"));
        referenceDao.updateRef(TEST_USER, film);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("film is not equal to updated reference", reference, film);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }

    @Test
    public void testUpdateWebsite() throws MalformedURLException {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "A MySQL Docs reference";
        String title = null;
        String notes = "this is the coolest website ever!";
        URL url = new URL("https://dev.mysql.com/doc");
        Website website = new Website(id, created, label, title, notes, url);
        referenceDao.createRef(TEST_USER, website);
        website.setUrl(new URL("https://start.spring.io"));
        website.setLabel("Create a new Spring Boot project");
        website.setTitle("Spring Initializr");
        website.setNotes("this is actually the ultimate site");
        referenceDao.updateRef(TEST_USER, website);
        Reference reference = referenceDao.readReference(TEST_USER, id);
        assertEquals("website is not equal to updated reference", reference, website);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
    }

    @Test
    public void testDeleteReference() throws MalformedURLException {
        String idBook = UUID.randomUUID().toString();
        String idWebsite = UUID.randomUUID().toString();
        LocalDateTime createdBook = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime createdWebsite = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String labelBook = "A Homer reference";
        String labelWebsite = "A MySQL Docs reference";
        String titleBook = "The Odyssey of Homer";
        String titleWebsite = null;
        String notesBook = "That was epic!";
        String notesWebsite = "this is the coolest website ever!";
        int pageNumber = 195;
        URL url = new URL("https://dev.mysql.com/doc");
        Book book = new Book(idBook, createdBook, labelBook, titleBook, notesBook, pageNumber);
        Website website = new Website(idWebsite, createdWebsite, labelWebsite, titleWebsite, notesWebsite, url);
        referenceDao.createRef(TEST_USER, book);
        referenceDao.createRef(TEST_USER, website);
        List<Reference> allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 2", allReferences.size(), 2);
        assertTrue("allReferences does not contain book", allReferences.contains(book));
        assertTrue("allReferences does not contain website", allReferences.contains(website));
        referenceDao.deleteRef(TEST_USER, book);
        allReferences = referenceDao.readReferences(TEST_USER);
        assertEquals("size of allReferences is not 1", allReferences.size(), 1);
        assertFalse("allReferences contains book", allReferences.contains(book));
        assertTrue("allReferences does not contain website", allReferences.contains(website));
        referenceDao.deleteRef(TEST_USER, website);
        allReferences = referenceDao.readReferences(TEST_USER);
        assertTrue("allReferences is not empty", allReferences.isEmpty());
    }



}

