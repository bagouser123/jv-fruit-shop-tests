package core.basesyntax.service.operation;

import core.basesyntax.service.ServiceShopForTestsException;

public interface SupplyOperationForTests {
    void test(String fruit, int amount) throws ServiceShopForTestsException;
}
