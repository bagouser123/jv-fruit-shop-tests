package core.basesyntax.service.operation;

import core.basesyntax.model.FruitTransaction;

import java.util.List;

public interface DataConverterForTests {
    List<FruitTransaction> convertToTransaction(List<String> lines);
}
