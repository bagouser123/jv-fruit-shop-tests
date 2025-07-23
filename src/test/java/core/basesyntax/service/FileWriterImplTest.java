package core.basesyntax.service;

import org.junit.jupiter.api.Test;

public class FileWriterImplTest {
    private static final String FILE_TO = "src/main/resources/TestDataBase.csv";
    private Writer writer = new FileWriterImpl();

    @Test
    void checkFileWriter_Ok() {
        String report = "banana,100";
        writer.write(report, FILE_TO);
    }
}
