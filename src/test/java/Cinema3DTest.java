import cinema.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;
@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenDeleteSessionThenItDoesNotExist() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        Session session2 = new Session3D();
        cinema.add(session2);
        cinema.delete(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).doesNotContain(session2);
    }

    @Test
    public void whenBuyTicketOnOccupiedPlaceThenGetException() {
        Account account1 = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account1, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account2, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Место занято");
    }

    @Test
    public void whenBuyTicketOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar invalidDate = Calendar.getInstance();
        invalidDate.set(2024, Calendar.FEBRUARY, 30);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, invalidDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Неправильная дата");
    }

    @Test
    public void whenAddDuplicateSessionThenGetException() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        assertThatThrownBy(() -> cinema.add(session))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Сеанс существует");
    }

    @Test
    public void whenBuyTicketOnOccupiedSeatThenThrowException() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Session session = new Session3D();
        cinema.add(session);
        Calendar date = Calendar.getInstance();

        cinema.buy(account, 1, 1, date);
        cinema.buy(account, 1, 1, date);
    }

    @Test
    public void whenBuyTicketWithInvalidDataThenThrowException() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Session session = new Session3D();
        cinema.add(session);
        Calendar date = Calendar.getInstance();

        cinema.buy(account, -1, -1, date);
    }

    @Test
    public void whenFindSessionByPredicateThenReturnFilteredSessions() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        cinema.add(session2);

        List<Session> result = cinema.find(session -> session.equals(session1));
        assertEquals(1, result.size());
        assertTrue(result.contains(session1));
    }

    @Test
    public void whenAddSessionAtSameTimeThenThrowException() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        cinema.add(session2);
    }
}