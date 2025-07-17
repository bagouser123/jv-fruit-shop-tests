package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ServiceShopForTestsException;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.operation.ServiceShopForTests;
import core.basesyntax.service.operation.ServiceShopForTestsImpl;
import core.basesyntax.service.operation.SupplyOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class SupplyOperationTest {
    private ServiceShopForTests serviceShopForTests = new ServiceShopForTestsImpl();

    @Test
    void supply_EmptyFruit_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new SupplyOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit(""),
                fruitTransaction.setAmount(40));
        fruitTransaction.setOperation(FruitTransaction.Operation.RETURN);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void supply_ZeroBalance_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new SupplyOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("apple"),
                fruitTransaction.setAmount(0));
        fruitTransaction.setOperation(FruitTransaction.Operation.RETURN);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void supply_NullFruit_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new SupplyOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit(null),
                fruitTransaction.setAmount(100));
        fruitTransaction.setOperation(FruitTransaction.Operation.SUPPLY);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void supply_MinusAmount_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new SupplyOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("banana"),
                fruitTransaction.setAmount(-100));
        fruitTransaction.setOperation(FruitTransaction.Operation.SUPPLY);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @Test
    void supply_FruitWithSpeciallySymbols_NotOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        OperationHandler operationHandler = new SupplyOperation();
        operationHandler.updateNumberOffFruit(
                fruitTransaction.setFruit("$$#@$banana"),
                fruitTransaction.setAmount(5));
        fruitTransaction.setOperation(FruitTransaction.Operation.SUPPLY);
        assertThrows(ServiceShopForTestsException.class, () ->
                serviceShopForTests.shopTest(fruitTransaction));
    }

    @AfterEach
    public void afterEachTest() {
        Storage.storage.clear();
    }
}
