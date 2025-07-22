package core.basesyntax.service.operation;

import core.basesyntax.dao.FruitShopDao;
import core.basesyntax.dao.FruitShopDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class DataConverterForTestImpl implements DataConverterForTest {
    private FruitShopDao fruitShopDao = new FruitShopDaoImpl();

    @Override
    public void test(FruitTransaction fruitTransaction) {
        if (fruitTransaction.getFruit() == null) {
            throw new RuntimeException("Null fruit exception!");
        }
        if (fruitTransaction.getFruit().isEmpty()) {
            throw new RuntimeException("Fruit parameteres are empty!");
        }
        if (fruitTransaction.getAmount() <= 1) {
            throw new RuntimeException("Amount can't be less than 1!");
        }
        if (!Character.isLetter(fruitTransaction.getFruit().charAt(0))) {
            throw new RuntimeException("Fruit name should start from letters!");
        }
        if (fruitTransaction.getFruit().length() < 4) {
            throw new RuntimeException("Fruit name should be at least 4 letters!");
        }
        if (Storage.storage.getOrDefault(fruitTransaction.getFruit(), 0)
                - fruitTransaction.getAmount() < 0) {
            throw new RuntimeException("Fruit amount is not enough!");
        }
        fruitShopDao.add(fruitTransaction.getFruit(), fruitTransaction.getAmount());
    }
}
