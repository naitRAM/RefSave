package dev.ramimans.refsave;
import dev.ramimans.refsave.dao.ReferenceDaoImpl;
import dev.ramimans.refsave.dto.Book;
import dev.ramimans.refsave.dto.Reference;
import dev.ramimans.refsave.dto.Tv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Reference reference = referenceDaoImpl.readReference(TEST_USER, id);
        assertEquals(reference, book);
    }

    @Test
    public void testAddGetTv() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "Mr Robot Opening Scene";
        String title = "Mr Robot S01E01";
        String notes = "this gave me goosebumps!";
        String timepoint = "00:00:00";
        Tv tv = new Tv(id, created, label, title, notes, LocalTime.parse(timepoint));
        referenceDaoImpl.createRef(TEST_USER, tv);
        Reference reference = referenceDaoImpl.readReference(TEST_USER, id);
        assertEquals(reference, tv);
    }

    @Test
    public void TestAddGetFilm() {
        String id = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String label = "Mr Robot Opening Scene";
        String title = "Mr Robot S01E01";
        String notes = "this gave me goosebumps!";
        String timepoint = "00:00:00";
    }
}

