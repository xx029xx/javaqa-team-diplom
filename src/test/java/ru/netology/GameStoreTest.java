package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() { //1. Наличие игры в каталоге после добавления

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddTwoGamesTest() { // 2. добавление 2 игр

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Игра2", "Аркады");

        assertTrue(store.containsGame(game2));
    }

    @Test
    public void shouldNotContainsGameTest() { // 3. покажет ли store игру, которая не
        // была добавлена через метод publishGame

        GameStore store = new GameStore();
        Game game = store.publishGame("Игра2", "Аркады");
        Game game2 = new Game("Нетология Баттл Онлайн", "Аркады", store);
        Game game3 = store.publishGame("Игра3", "Квест");

        assertFalse(store.containsGame(game2));
    }

    @Test
    public void shouldAddOneHourTest() { // 4. добавить время игры 1 час

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 1);

        String[] expected = {"Игрок1"};
        String[] actual = new String[]{store.getMostPlayer()};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotPlayerTest() { // 5. метод getMostPlayer, если игроки отсутствуют

        GameStore store = new GameStore();
        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldNotAddZeroTest() { // 6. добавление времени игры 0 часов

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 0);

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldNotAddHoursIfMinusTest() { // 7. добавление времени с отрицательным значением

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", -1);

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldAddHoursIfBigTest() { // 8. добавление времени игры 999 999 часов

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 999_999);
        store.addPlayTime("Игрок2", 100);
        store.addPlayTime("Игрок3", 20);

        String[] expected = {"Игрок1"};
        String[] actual = new String[]{store.getMostPlayer()};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddHoursTest() { // 9. добавление времени игры больше 1 часа

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 2);
        store.addPlayTime("Игрок2", 3);

        String[] expected = {"Игрок2"};
        String[] actual = new String[]{store.getMostPlayer()};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddNewHoursTest() { // 10. добавление нового времени для игры в которую уже играли

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 5);
        store.addPlayTime("Игрок1", 6);
        store.addPlayTime("Игрок2", 8);

        String[] expected = {"Игрок1"};
        String[] actual = new String[]{store.getMostPlayer()};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddNewHoursIfZeroTest() { // 11. добавление времени 0 для игры в которую уже играли

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 5);
        store.addPlayTime("Игрок1", 0);
        store.addPlayTime("Игрок2", 6);

        String[] expected = {"Игрок2"};
        String[] actual = new String[]{store.getMostPlayer()};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowPlayedTimeOnePlayerTest() { // 12. общее количество времени игры если 1 игрок


        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 5);

        int expected = 5;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowPlayedTimeTest() { // 13. общего количество времени игр игроков, больше 1

        GameStore store = new GameStore();
        store.addPlayTime("Игрок1", 4);
        store.addPlayTime("Игрок2", 6);
        store.addPlayTime("Игрок3", 0);

        int expected = 10;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowPlayedTimeZeroTest() { // 14. покажет ли метод getSumPlayedTime время, если игроков нет

        GameStore store = new GameStore();
        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }
}
