package core.basesyntax.service.operation;

import core.basesyntax.service.ServiceShopForTestsException;

public class SupplyOperationForTestsImpl implements SupplyOperationForTests {
    private OperationHandler operationHandler = new SupplyOperation();

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
        operationHandler.updateNumberOffFruit(fruit, amount);
    }
}
