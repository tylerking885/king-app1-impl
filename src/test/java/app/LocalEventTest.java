package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LocalEventTest {

    @Test
    void testLocalEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = "2021-11-06";
        String description = "Test";
        LocalDate localDate = LocalDate.parse(date,formatter);
        LocalEvent localEvent = new LocalEvent(localDate,description, false);

        assertFalse(localEvent.isCompleted());
        localEvent.setCompleted();
        assertTrue(localEvent.isCompleted());
        localEvent.setIncomplete();
        assertFalse(localEvent.isCompleted());

        LocalEvent localEvent2 = new LocalEvent(localDate,description,true);
        assertTrue(localEvent2.isCompleted());

        assertEquals(description,localEvent.getDescription());
        localEvent.setDescription("Testing");
        assertEquals("Testing", localEvent.getDescription());

        assertEquals(localDate, localEvent.getDate());
        date = "2021-11-07";
        localDate = LocalDate.parse(date, formatter);
        localEvent.setDate(localDate);
        assertEquals(localDate, localEvent.getDate());
    }

    @Test
    void testExceptionThrowOfOpenFile() {
        GuiController test1 = new GuiController();
        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class,() ->{
            File file = new File("thisDoesNotExist");
            test1.openFile(file);
        });
    }

}