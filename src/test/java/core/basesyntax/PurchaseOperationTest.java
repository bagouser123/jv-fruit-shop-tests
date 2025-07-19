package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.PurchaseOperationForTests;
import core.basesyntax.service.operation.PurchaseOperationForTestsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class PurchaseOperationTest {
    private PurchaseOperationForTests purchaseOperationForTests = new
            PurchaseOperationForTestsImpl();

    @Test
    void purchase_FruitContainsNull_NotOk() {
        String fruit = null;
        int amount = 200;
        assertThrows(ServiceShopForTestsException.class, () ->
                purchaseOperationForTests.test(fruit, amount));
    }

    @Test
    void purchase_BalanceIsNull_NotOk() {
        String fruit = "banana";
        int amount = 0;
        assertThrows(ServiceShopForTestsException.class, () ->
                purchaseOperationForTests.test(fruit, amount));
    }

    @Test
    void purchase_EmptyFruit_NotOk() {
        String fruit = "";
        int amount = 40;
        assertThrows(ServiceShopForTestsException.class, () ->
                purchaseOperationForTests.test(fruit, amount));
    }

    @Test
    void purchase_PurchaseGood_Ok() {
        String fruit = "apple";
        int amount = 20;
        Storage.storage.put(fruit, amount);
        try {
            purchaseOperationForTests.test(fruit, amount);
        } catch (ServiceShopForTestsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void purchase_NotEnoughQuality_NotOk() {
        String fruit = "apple";
        int amount = 200;
        assertThrows(ServiceShopForTestsException.class, () ->
                purchaseOperationForTests.test(fruit, amount));
    }

    @Test
    void purchase_FruitWithSpeciallySymbols_NotOk() {
        String fruit = "$%%#^#^@#^";
        int amount = 40;
        assertThrows(ServiceShopForTestsException.class, () ->
                purchaseOperationForTests.test(fruit, amount));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
