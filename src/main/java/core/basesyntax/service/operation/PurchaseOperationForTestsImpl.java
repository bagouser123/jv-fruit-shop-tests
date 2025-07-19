package core.basesyntax.service.operation;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ServiceShopForTestsException;

public class PurchaseOperationForTestsImpl implements PurchaseOperationForTests {
    private OperationHandler operationHandler = new PurchaseOperation();

    @Override
    public void test(String fruit, int amount) throws ServiceShopForTestsException {
        if (fruit == null) {
            throw new ServiceShopForTestsException("Null fruit exception!");
        }
        if (fruit.isEmpty()) {
            throw new ServiceShopForTestsException("Fruit paramateres are empty!");
        }
        if (amount <= 0) {
            throw new ServiceShopForTestsException("Amount can't be less than 1!");
        }
        if (!Character.isLetter(fruit.charAt(0))) {
            throw new ServiceShopForTestsException("Fruit name should start from letters!");
        }
        if (Storage.storage.getOrDefault(fruit, 0)
                - amount < 0) {
            throw new ServiceShopForTestsException("Fruit amount is not enough!");
        }
        operationHandler.updateNumberOffFruit(fruit, amount);
    }
}
