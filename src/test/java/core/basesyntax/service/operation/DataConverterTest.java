package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitReaderImpl;
import core.basesyntax.service.Reader;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DataConverterTest {
    public static final String FILE_FROM = "src/main/resources/FORTESTS.csv";
    private DataConverterForTest dataConverterForTest = new DataConverterForTestImpl();

    @Test
    void dataConverter_FruitWithSpeciallySymbols_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        DataConverter dataConverter = new DataConverterImpl();
        final List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        for (int i = 0; i < 1; i++) {
            FruitTransaction fruitTransaction = transactions.get(i);
            assertThrows(RuntimeException.class, () ->
                    dataConverterForTest.test(fruitTransaction));
        }
    }

    @Test
    void dataConverter_ZeroBalance_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        DataConverter dataConverter = new DataConverterImpl();
        final List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        for (int i = 1; i < 2; i++) {
            FruitTransaction fruitTransaction = transactions.get(i);
            assertThrows(RuntimeException.class, () ->
                    dataConverterForTest.test(fruitTransaction));
        }
    }

    @Test
    void dataConverter_BalanceBanana_Ok() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        DataConverter dataConverter = new DataConverterImpl();
        final List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        for (int i = 2; i < 3; i++) {
            FruitTransaction fruitTransaction = transactions.get(i);
            Storage.storage.put(fruitTransaction.getFruit(), fruitTransaction.getAmount());
            dataConverterForTest.test(fruitTransaction);
        }
    }

    @Test
    void dataConverter_ReturnBanana_Ok() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        DataConverter dataConverter = new DataConverterImpl();
        final List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        for (int i = 3; i < 4; i++) {
            FruitTransaction fruitTransaction = transactions.get(i);
            Storage.storage.put(fruitTransaction.getFruit(), fruitTransaction.getAmount());
            dataConverterForTest.test(fruitTransaction);
        }
    }

    @Test
    void dataConverter_PurchaseTooMuchBananas_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        DataConverter dataConverter = new DataConverterImpl();
        final List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        for (int i = 4; i < 5; i++) {
            FruitTransaction fruitTransaction = transactions.get(i);
            assertThrows(RuntimeException.class, () ->
                    dataConverterForTest.test(fruitTransaction));
        }
    }

    @Test
    void dataConverter_FruitNameStartedNotWithLetter_NotOk() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
        DataConverter dataConverter = new DataConverterImpl();
        final List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        for (int i = 5; i < 6; i++) {
            FruitTransaction fruitTransaction = transactions.get(i);
            assertThrows(RuntimeException.class, () ->
                    dataConverterForTest.test(fruitTransaction));
        }
    }
}
