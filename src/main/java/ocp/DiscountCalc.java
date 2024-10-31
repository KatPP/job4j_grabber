package ocp;

/**
 * Здесь в примере при добавлении нового типа клиента (например, "Gold") необходимо изменять метод calculateDiscount,
 * Это нарушает принцип OCP. Вместо этого можно использовать стратегию или полиморфизм для реализации различных типов скидок.
 */

class DiscountCalc {
    public double calculateDiscount(String customerType, double amount) {
        if (customerType.equals("Regular")) {
            return amount * 0.1;
        } else if (customerType.equals("VIP")) {
            return amount * 0.2;
        }
        return 0;
    }
}

