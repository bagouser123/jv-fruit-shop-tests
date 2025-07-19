package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.BalanceOperationForTests;
import core.basesyntax.service.operation.BalanceOperationForTestsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class BalanceOperationTest {
    private BalanceOperationForTests balanceOperationForTests = new BalanceOperationForTestsImpl();

    @Test
    void balance_NullFruit_NotOk() {
        assertThrows(ServiceShopForTestsException.class, () ->
                balanceOperationForTests.test(null, 100));
    }

    @Test
    void balance_ZeroBalance_NotOk() {
        String fruit = "banana";
        int amount = 0;
        assertThrows(ServiceShopForTestsException.class, () ->
                balanceOperationForTests.test(fruit, amount));
    }

    @Test
    void balance_EmptyFruit_NotOk() {
        String fruit = "";
        int amount = 40;
        assertThrows(ServiceShopForTestsException.class, () ->
                balanceOperationForTests.test(fruit, amount));
    }

    @Test
    void balance_BalanceFilledToAllParameters_Ok() {
        String fruit = "apple";
        int amount = 40;
        try {
            balanceOperationForTests.test(fruit, amount);
        } catch (ServiceShopForTestsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void balance_FruitNameStartedWithSpeciallySymbols_NotOk() {
        String fruit = "$%%#^#^@#^";
        int amount = 5;
        assertThrows(ServiceShopForTestsException.class, () ->
                balanceOperationForTests.test(fruit, amount));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
