package productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore {
    protected static final int MIN_DISCOUNT_PERCENT = 0;
    protected static final int MAX_DISCOUNT_PERCENT = 25;
    protected static final int MAX_SUITABLE_PERCENT = 75;

    private final List<Food> foods;
    private final Predicate<Food> foodConditions;

    public AbstractStore(Predicate<Food> foodConditions) {
        this.foods = new ArrayList<>();
        this.foodConditions = foodConditions;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public boolean isSuitableFood(Food food) {
        return foodConditions.test(food);
    }

    public void clear() {
        foods.clear();
    }
}