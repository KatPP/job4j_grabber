package productstorage;

public class Shop extends AbstractStore {
    public Shop() {
        super(food -> {
            var p = Calculator.shelfLifeInPercents(food);
            return p <= MAX_SUITABLE_PERCENT && p > MIN_DISCOUNT_PERCENT;
        });
    }
}