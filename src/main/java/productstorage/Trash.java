package productstorage;

public class Trash extends AbstractStore {
    public Trash() {
        super(food -> Calculator.shelfLifeInPercents(food) <= 0);
    }
}
