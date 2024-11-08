package productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ControlQuality {
    private final List<AbstractStore> stores;
    private final Consumer<Food> discounter;

    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
        this.discounter = new DefaultDiscounter();
    }

    public ControlQuality(List<AbstractStore> stores, Consumer<Food> discounter) {
        this.stores = stores;
        this.discounter = discounter;
    }

    public void distributeFood(List<Food> foods) {
        foods.forEach(food -> {
            var optionalStore = stores.stream()
                    .filter(s -> s.isSuitableFood(food))
                    .findFirst();
            if (optionalStore.isEmpty()) {
                throw new IllegalStateException(String.format("No suitable store for food: %s", food));
            }
            addFoodToStore(optionalStore.get(), food);
        });
    }

    public void resort() {
        List<Food> allFoods = new ArrayList<>();
        for (AbstractStore store : stores) {
            allFoods.addAll(store.getFoods());
            store.clear();
        }
        distributeFood(allFoods);
    }

    public List<AbstractStore> getStores() {
        return stores;
    }

    public Consumer<Food> getDiscounter() {
        return discounter;
    }

    private void addFoodToStore(AbstractStore store, Food food) {
        discounter.accept(food);
        store.addFood(food);
    }
}