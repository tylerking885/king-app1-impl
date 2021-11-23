package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

class GuiControllerTest {

    @Test
    void testExceptionThrowOfOpenFile() {
        GuiController test1 = new GuiController();
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            File file = new File("thisDoesNotExist");
            test1.openFile(file);
        });
    }
}