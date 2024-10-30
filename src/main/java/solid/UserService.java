package solid;

/**
 * Здесь нарушение SRP: Класс UserService отвечает как за регистрацию пользователей,
 * так и за логирование действий. Это приводит к смешению ответственности,
 * так как логирование — это отдельная задача.
 */

public class UserService {

    public void registerUser(String username, String password) {
        System.out.println("Регистрация пользователя: " + username);
        log("Пользователь " + username + " зарегистрирован.");
    }

    private void log(String message) {
        System.out.println("Лог: " + message);
    }
}
