package productstorage;


public class Warehouse extends AbstractStore {
    public Warehouse() {
        super(food -> Calculator.shelfLifeInPercents(food) > 75);
    }
}
