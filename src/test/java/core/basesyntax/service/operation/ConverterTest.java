package core.basesyntax.service.operation;

import core.basesyntax.service.FruitReaderImpl;
import core.basesyntax.service.Reader;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ConverterTest {
    public static final String FILE_FROM = "src/main/resources/database.csv";
    private DataConverter dataConverter = new DataConverterImpl();

    @Test
    void dataConverter_BalanceBanana_Ok() {
        Reader fruitReader = new FruitReaderImpl();
        List<String> inputReport = fruitReader.read(FILE_FROM);
            dataConverter.convertToTransaction(inputReport);
        }
    }
