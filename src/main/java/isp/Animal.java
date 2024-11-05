package isp;

/**
 * Здесь нарушение связано с тем что интерфейс Animal содержит методы, которые не применимы для всех животных.
 * Например, собака не может летать, и реализация метода fly()  вызывает исключение. Это нарушает принцип ISP,
 * так как класс Dog вынужден реализовывать метод, который ему не нужен.
 */

interface Animal {
    void eat();

    void sleep();

    void fly();

    void swim();
}

class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("Собака ест");
    }

    @Override
    public void sleep() {
        System.out.println("Собака спит");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException("Собака не может летать");
    }

    @Override
    public void swim() {
        System.out.println("Собака плавает");
    }
}
