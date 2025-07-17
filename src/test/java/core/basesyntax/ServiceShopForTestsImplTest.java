package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.ServiceShopForTests;
import core.basesyntax.service.operation.ServiceShopForTestsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ServiceShopForTestsImplTest {
    private ServiceShopForTests serviceShopForTests = new ServiceShopForTestsImpl();

    @Test
    void service_FruitWithNullParameters_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.BALANCE);
        fruitTransaction.setAmount(200);
        fruitTransaction.setFruit(null);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_OperationWithNullParameters_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(null);
        fruitTransaction.setAmount(200);
        fruitTransaction.setFruit("banana");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_OperationParametersContainsOneLetter_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.mapToOperation("b"));
        fruitTransaction.setAmount(50);
        fruitTransaction.setFruit("banana");
        Storage.storage.put(fruitTransaction.getFruit(), fruitTransaction.getAmount());
        try {
            serviceShopForTests.shopTest(fruitTransaction);
        } catch (ServiceShopForTestsException e) {
            fail("something had wrong parameters! ", e);
        }
    }

    @Test
    void service_FruitWithEmptyParameters_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.BALANCE);
        fruitTransaction.setAmount(200);
        fruitTransaction.setFruit("");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_AmountLessThanOne_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.BALANCE);
        fruitTransaction.setAmount(0);
        fruitTransaction.setFruit("apple");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_NewFruitToTheStorage_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.BALANCE);
        fruitTransaction.setAmount(50);
        fruitTransaction.setFruit("orange");
        Storage.storage.put(fruitTransaction.getFruit(), fruitTransaction.getAmount());
        try {
            serviceShopForTests.shopTest(fruitTransaction);
        } catch (ServiceShopForTestsException e) {
            throw new RuntimeException("invalid fruit typing! ", e);
        }
    }

    @Test
    void service_FruitNameStartedWithNumbers_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.BALANCE);
        fruitTransaction.setAmount(2);
        fruitTransaction.setFruit("123");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_FruitNameStartedWithSpeciallySymbols_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.RETURN);
        fruitTransaction.setAmount(2);
        fruitTransaction.setFruit("%$#!*$*#%&@$");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_ReturnMinusParameters_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.RETURN);
        fruitTransaction.setAmount(-5);
        fruitTransaction.setFruit("apple");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void service_FruitNameShouldHaveAtLeastFourLetters_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setOperation(FruitTransaction.Operation.RETURN);
        fruitTransaction.setAmount(10);
        fruitTransaction.setFruit("abc");
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
