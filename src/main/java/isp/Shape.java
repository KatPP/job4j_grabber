package isp;

/**
 * Интерфейс Shape включает метод rotate(), который не имеет смысла для всех форм, например, для круга.
 * Это приводит к тому, что класс Circle вынужден реализовывать метод, который не применим к нему, нарушая принцип ISP.
 */

interface Shape {
    void draw();

    void resize();

    void rotate();
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Рисование круга");
    }

    @Override
    public void resize() {
        System.out.println("Изменение размера круга");
    }

    @Override
    public void rotate() {

        throw new UnsupportedOperationException("Круг не может вращаться");
    }
}
