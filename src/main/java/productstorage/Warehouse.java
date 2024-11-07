package productstorage;

public class Warehouse extends AbstractStore {
    public Warehouse() {
        super(food -> Calculator.shelfLifeInPercents(food) > MAX_SUITABLE_PERCENT);
    }
}