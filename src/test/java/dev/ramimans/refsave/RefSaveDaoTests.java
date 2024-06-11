package dev.ramimans.refsave;
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
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class RefSaveDaoTests {
    @Autowired
    ReferenceDaoImpl referenceDaoImpl;
    private String TEST_USER="Majid";
    @BeforeEach
    public void setUp() {
        List<Reference> allReferences = referenceDaoImpl.readReferences(TEST_USER);
        for (Reference ref : allReferences) {
            referenceDaoImpl.deleteRef(TEST_USER, ref);
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
        referenceDaoImpl.createRef(TEST_USER, book);
        Reference reference = referenceDaoImpl.readReference(TEST_USER, id);
        assertEquals("book is not equal to retrieved reference", reference, book);
        List<Reference> allReferences = referenceDaoImpl.readReferences(TEST_USER);
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
        referenceDaoImpl.createRef(TEST_USER, tv);
        Reference reference = referenceDaoImpl.readReference(TEST_USER, id);
        assertEquals("tv is not equal to retrieved reference", reference, tv);
        List<Reference> allReferences = referenceDaoImpl.readReferences(TEST_USER);
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
        referenceDaoImpl.createRef(TEST_USER, film);
        Reference reference = referenceDaoImpl.readReference(TEST_USER, id);
        assertEquals("film is not equal to retrieved reference", reference, film);
        List<Reference> allReferences = referenceDaoImpl.readReferences(TEST_USER);
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
        referenceDaoImpl.createRef(TEST_USER, website);
        Reference reference = referenceDaoImpl.readReference(TEST_USER, id);
        assertEquals("website is not equal to retrieved reference", reference, website);
        List<Reference> allReferences = referenceDaoImpl.readReferences(TEST_USER);
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
        String labelWebsite = "A Lord of the Ring reference";
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
        referenceDaoImpl.createRef(TEST_USER, book);
        referenceDaoImpl.createRef(TEST_USER, tv);
        referenceDaoImpl.createRef(TEST_USER, film);
        referenceDaoImpl.createRef(TEST_USER, website);
        List<Reference> allReferences = referenceDaoImpl.readReferences(TEST_USER);
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
        String labelWebsite = "A Lord of the Ring reference";
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

        referenceDaoImpl.createRef(TEST_USER, book);
        referenceDaoImpl.createRef(TEST_USER, tv);
        referenceDaoImpl.createRef(TEST_USER, film);
        referenceDaoImpl.createRef(TEST_USER, website);

        List<Reference> books = referenceDaoImpl.readReferencesByCategory(TEST_USER, Category.BOOK);
        assertTrue("books does not contain book", books.contains(book));
        assertEquals("size of books is not 1", books.size(), 1);

        List<Reference> tvs = referenceDaoImpl.readReferencesByCategory(TEST_USER, Category.TV);
        assertTrue("tvs does not contain tv", tvs.contains(tv));
        assertEquals("size of tvs is not 1", tvs.size(), 1);

        List<Reference> films = referenceDaoImpl.readReferencesByCategory(TEST_USER, Category.FILM);
        assertTrue("films does not contain film", films.contains(film));
        assertEquals("size of films is not 1", films.size(), 1);

        List<Reference> websites = referenceDaoImpl.readReferencesByCategory(TEST_USER, Category.WEBSITE);
        assertTrue("websites does not contain website", websites.contains(website));
        assertEquals("size of websites is not 1", websites.size(), 1);
    }


}

