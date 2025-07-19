package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.ReturnOperationForTests;
import core.basesyntax.service.operation.ReturnOperationForTestsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ReturnOperationTest {
    private ReturnOperationForTests returnOperationForTests = new ReturnOperationForTestsImpl();

    @Test
    void return_EmptyFruit_NotOk() {
        String fruit = "";
        int amount = 40;
        assertThrows(ServiceShopForTestsException.class, () ->
                returnOperationForTests.test(fruit, amount));
    }

    @Test
    void return_ZeroBalance_NotOk() {
        String fruit = "banana";
        int amount = 0;
        assertThrows(ServiceShopForTestsException.class, () ->
                returnOperationForTests.test(fruit, amount));
    }

    @Test
    void return_ReturnFilledToAllParameters_Ok() {
        String fruit = "orange";
        int amount = 40;
        try {
            returnOperationForTests.test(fruit, amount);
        } catch (ServiceShopForTestsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void return_NullFruit_NotOk() {
        String fruit = null;
        int amount = 200;
        assertThrows(ServiceShopForTestsException.class, () ->
                returnOperationForTests.test(fruit, amount));
    }

    @Test
    void return_MinusAmount_NotOk() {
        String fruit = "pineapple";
        int amount = -100;
        assertThrows(ServiceShopForTestsException.class, () ->
                returnOperationForTests.test(fruit, amount));
    }

    @Test
    void return_FruitNameStartedWithSpeciallySymbols_NotOk() {
        String fruit = "$%%#^#^@#^";
        int amount = 120;
        assertThrows(ServiceShopForTestsException.class, () ->
                returnOperationForTests.test(fruit, amount));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
