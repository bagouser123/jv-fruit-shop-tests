package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.SupplyOperationForTests;
import core.basesyntax.service.operation.SupplyOperationForTestsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class SupplyOperationTest {

    private SupplyOperationForTests supplyOperationForTests = new SupplyOperationForTestsImpl();

    @Test
    void supply_EmptyFruit_NotOk() {
        String fruit = "";
        int amount = 40;
        assertThrows(ServiceShopForTestsException.class, () ->
                supplyOperationForTests.test(fruit, amount));
    }

    @Test
    void supply_ZeroBalance_NotOk() {
        String fruit = "banana";
        int amount = 0;
        assertThrows(ServiceShopForTestsException.class, () ->
                supplyOperationForTests.test(fruit, amount));
    }

    @Test
    void supply_ReturnFilledToAllParameters_Ok() {
        String fruit = "orange";
        int amount = 40;
        try {
            supplyOperationForTests.test(fruit, amount);
        } catch (ServiceShopForTestsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void supply_NullFruit_NotOk() {
        String fruit = null;
        int amount = 200;
        assertThrows(ServiceShopForTestsException.class, () ->
                supplyOperationForTests.test(fruit, amount));
    }

    @Test
    void supply_MinusAmount_NotOk() {
        String fruit = "pineapple";
        int amount = -100;
        assertThrows(ServiceShopForTestsException.class, () ->
                supplyOperationForTests.test(fruit, amount));
    }

    @Test
    void supply_FruitNameStartedWithSpeciallySymbols_NotOk() {
        String fruit = "$%%#^#^@#^";
        int amount = 120;
        assertThrows(ServiceShopForTestsException.class, () ->
                supplyOperationForTests.test(fruit, amount));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
