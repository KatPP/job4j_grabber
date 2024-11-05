package productstorage;

public class Shop extends AbstractStore {
    public Shop() {
        super(food -> {
            var p = Calculator.shelfLifeInPercents(food);
            return p <= 75 && p > 0;
        });
    }
}
