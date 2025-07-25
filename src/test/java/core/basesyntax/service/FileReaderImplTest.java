package core.basesyntax.service;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class FileReaderImplTest {
    private static final String FILE_TO = "src/main/resources/TestDataBase.csv";
    private Reader reader = new FruitReaderImpl();

    @Test
    void FileReader_readFile_Ok() {
        reader.read(FILE_TO);
    }
    @Test
    void FileReader_readEmptyFile_NotOk() {
        assertThrows(RuntimeException.class, () ->
                reader.read(""));
    }
}

