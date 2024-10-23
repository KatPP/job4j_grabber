package fizzbuzz;

import java.util.Scanner;

public class FizzBuzzGame {

    private static final int MAX_NUMBER = 100;
    private final Scanner input = new Scanner(System.in);
    private int currentNumber = 1;

    public void start() {
        System.out.println("Игра FizzBuzz.");
        while (currentNumber < MAX_NUMBER) {
            String expectedAnswer = getExpectedAnswer(currentNumber);
            System.out.println(expectedAnswer);
            String playerAnswer = input.nextLine();
            if (!isCorrectAnswer(expectedAnswer, playerAnswer)) {
                System.out.println("Ошибка. Начинай снова.");
                currentNumber = 1;
            } else {
                currentNumber++;
            }
        }
    }

    public String getExpectedAnswer(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(number);
        }
    }

    private boolean isCorrectAnswer(String expected, String actual) {
        return expected.equals(actual);
    }

    public static void main(String[] args) {
        FizzBuzzGame game = new FizzBuzzGame();
        game.start();
    }
}
