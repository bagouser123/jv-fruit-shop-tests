package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.operation.PurchaseOperation;
import core.basesyntax.service.operation.ServiceShopForTests;
import core.basesyntax.service.operation.ServiceShopForTestsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class PurchaseOperationTest {
    private ServiceShopForTests serviceShopForTests = new ServiceShopForTestsImpl();

    @Test
    void purchase_FruitContainsNull_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new PurchaseOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit(null),
                fruitTransaction.setAmount(200));
        fruitTransaction.setOperation(FruitTransaction.Operation.PURCHASE);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void purchase_BalanceIsNull_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new PurchaseOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("banana"),
                fruitTransaction.setAmount(0));
        fruitTransaction.setOperation(FruitTransaction.Operation.PURCHASE);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void purchase_EmptyFruit_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new PurchaseOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit(""),
                fruitTransaction.setAmount(40));
        fruitTransaction.setOperation(FruitTransaction.Operation.PURCHASE);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void purchase_PurchaseGood_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new PurchaseOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("apple"),
                fruitTransaction.setAmount(20));
        fruitTransaction.setOperation(FruitTransaction.Operation.PURCHASE);
        Storage.storage.put(fruitTransaction.getFruit(), fruitTransaction.getAmount());
        try {
            serviceShopForTests.shopTest(fruitTransaction);
        } catch (ServiceShopForTestsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void purchase_NotEnoughQuality_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new PurchaseOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("apple"),
                fruitTransaction.setAmount(200));
        fruitTransaction.setOperation(FruitTransaction.Operation.PURCHASE);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void purchase_FruitWithSpeciallySymbols_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new PurchaseOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("$%%#^#^@#^"),
                fruitTransaction.setAmount(5));
        fruitTransaction.setOperation(FruitTransaction.Operation.PURCHASE);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
