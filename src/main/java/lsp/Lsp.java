package lsp;

public class Lsp {

    /**
     * Здесь нарушения: Класс Ostrich не может быть использован везде, где ожидается Bird,
     * поскольку он не поддерживает поведение, определенное в супер-классе.
     */
    class Bird {
        public void fly() {
            System.out.println("Bird is flying");
        }
    }

    class Ostrich extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Ostrich can't fly");
        }
    }

    /**
     * Здесь Класс Square не может быть использован вместо Rectangle,
     * так как его поведение изменяется и не соответствует ожиданиям, что getArea будет работать корректно.
     */

    class Rectangle {
        protected int width;
        protected int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getArea() {
            return width * height;
        }
    }

    class Square extends Rectangle {
        @Override
        public void setWidth(int width) {
            this.width = width;
            this.height = width;
        }

        @Override
        public void setHeight(int height) {
            this.height = height;
            this.width = height;
        }
    }

    /**
     * Здесь следующие нарушения: Класс Bicycle не может быть использован везде, где ожидается Vehicle,
     * так как он не поддерживает поведение, определенное в супер-классе.
     */

    class Vehicle {
        public void start() {
            System.out.println("Vehicle is starting");
        }
    }

    class Car extends Vehicle {
        @Override
        public void start() {
            System.out.println("Car is starting");
        }
    }

    class Bicycle extends Vehicle {
        // Предположим, что велосипед не имеет двигателя
        @Override
        public void start() {
            throw new UnsupportedOperationException("Bicycle doesn't start like a vehicle");
        }
    }
}
