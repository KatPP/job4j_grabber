package productstorage;


import java.util.function.Consumer;

public class DefaultDiscounter implements Consumer<Food> {
    @Override
    public void accept(Food food) {
        var remaining = Calculator.shelfLifeInPercents(food);
        if (remaining > 0 && remaining < 25) {
            food.setDiscount(0.8d);
        }
    }
}
