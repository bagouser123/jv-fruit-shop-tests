package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.service.FruitReaderImpl;
import core.basesyntax.service.Reader;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DataConverterTest {
    public static final String FILE_FROM = "src/main/resources/FORTESTS.csv";
    private DataConverterForTests dataConverter = new DataConverterForTestsImpl();

    @Test
    void dataConverter_EmptyFruit_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        for (int i = 1; i < 2; i++) {
            List<String> forData = Collections.singletonList(inputReport.get(i));
            assertThrows(RuntimeException.class, () ->
                    dataConverter.convertToTransaction(forData));
        }
    }

    @Test
    void dataConverter_ZeroBalance_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        for (int i = 2; i < 3; i++) {
            List<String> forData = Collections.singletonList(inputReport.get(i));
            assertThrows(RuntimeException.class, () ->
                    dataConverter.convertToTransaction(forData));
        }
    }

    @Test
    void dataConverter_BalanceBanana_Ok() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        for (int i = 3; i < 4; i++) {
            List<String> forData = Collections.singletonList(inputReport.get(i));
            Storage.storage.put("banana", 100);
            dataConverter.convertToTransaction(forData);
        }
    }

    @Test
    void dataConverter_ReturnBanana_Ok() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        for (int i = 4; i < 5; i++) {
            List<String> forData = Collections.singletonList(inputReport.get(i));
            Storage.storage.put("banana", 100);
            dataConverter.convertToTransaction(forData);
        }
    }

    @Test
    void dataConverter_PurchaseTooMuchBananas_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        for (int i = 5; i < 6; i++) {
            List<String> forData = Collections.singletonList(inputReport.get(i));
            assertThrows(RuntimeException.class, () ->
                    dataConverter.convertToTransaction(forData));
        }
    }

    @Test
    void dataConverter_FruitNameStartedNotWithLetter_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        for (int i = 6; i < 7; i++) {
            List<String> forData = Collections.singletonList(inputReport.get(i));
            assertThrows(RuntimeException.class, () ->
                    dataConverter.convertToTransaction(forData));
        }
    }
}
