package core.basesyntax.service.operation;

import core.basesyntax.service.ServiceShopForTestsException;

public interface BalanceOperationForTests {
    void test(String fruit, int amount) throws ServiceShopForTestsException;
}
