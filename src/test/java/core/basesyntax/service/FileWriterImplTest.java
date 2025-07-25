package core.basesyntax.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class FileWriterImplTest {
    private static final String FILE_TO = "src/main/resources/TestDataBase.csv";
    private Writer writer;

    @BeforeEach
    void BeforeEach() {
        writer = new FileWriterImpl();
    }

    @Test
    void checkFileWriter_Ok() {
        String report = "banana,100";
        writer.write(report, FILE_TO);
    }

    @Test
    void checkFileWriter_NotExitsFile_NotOk() {
        String report = "banana,100";
        assertThrows(RuntimeException.class, () ->
                writer.write(report, ""));
    }

    @Test
    void checkFileWriter_NullReport_NotOk() {
        assertThrows(RuntimeException.class, () ->
                writer.write(null, FILE_TO));
    }
}
