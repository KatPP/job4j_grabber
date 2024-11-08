package productstorage;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        setPrice(price);
        this.discount = 1d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceAfterDiscount() {
        return price * discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(price, food.price) == 0
                && Double.compare(discount, food.discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(expiryDate);
        result = 31 * result + Objects.hashCode(createDate);
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + Double.hashCode(discount);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Food.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("expiryDate=" + expiryDate)
                .add("createDate=" + createDate)
                .add("price=" + price)
                .add("discount=" + discount)
                .toString();
    }
}
