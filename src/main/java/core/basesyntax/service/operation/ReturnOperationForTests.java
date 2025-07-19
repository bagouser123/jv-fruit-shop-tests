package core.basesyntax.service.operation;

import core.basesyntax.service.ServiceShopForTestsException;

public interface ReturnOperationForTests {
    void test(String fruit, int amount) throws ServiceShopForTestsException;

}
