package productstorage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculator {
    public static int shelfLifeInPercents(Food food) {
        var rsl = 100;
        if (food.getCreateDate().isBefore(LocalDate.now())) {
            var fullLife = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
            var actualLife = ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());
            rsl = (int) (actualLife / (fullLife / 100));
        }
        return rsl;
    }
}
