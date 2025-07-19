package core.basesyntax.service.operation;

import core.basesyntax.service.ServiceShopForTestsException;

public interface PurchaseOperationForTests {
    void test(String fruit, int amount) throws ServiceShopForTestsException;
}
