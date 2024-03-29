package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.time.Year;

public class PlayerTest {

    @Test
    public void testShouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }


    @Test
    public void testShouldSumGenreIfThreeGamesOfTheSameGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология I", "Аркады");
        Game game2 = store.publishGame("Нетология II", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);

        player.play(game, 3);
        player.play(game1, 2);
        player.play(game2, 5);

        int expected = 10;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldSumTheGenreIfTheGamesAreOfDifferentGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология I", "Аркады");
        Game game2 = store.publishGame("Counter-Strike", "шутер");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);

        player.play(game, 3);
        player.play(game1, 2);
        player.play(game2, 5);

        int expected = 5;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldShowTheGameThatWasPlayedMoreWithDifferentGenres() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология I", "Аркады");
        Game game2 = store.publishGame("Counter-Strike", "шутер");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);

        player.play(game, 3);
        player.play(game1, 2);
        player.play(game2, 5);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldShowTheGameThatWasPlayedMoreWithSomeGenres() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология I", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);

        player.play(game, 3);
        player.play(game1, 2);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void theTestShouldShowNullIfTheGenreHasNotBeenPlayed() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология I", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);

        player.play(game, 3);
        player.play(game1, 2);

        Game actual = player.mostPlayerByGenre("Стратегии");
        assertEquals(null, actual);
    }

    @Test
    public void testShouldCountHoursWhenGameInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        int expected = 3;
        int actual = player.play(game, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void testErrorIfTheGameIsNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> player.play(game, 3));
    }

    @Test
    public void testWhereThereIsNoClockInTheGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        assertThrows(RuntimeException.class, () -> player.play(game, 0));
    }

    @Test
    public void testWhereIsTheNegativeClockInTheGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        assertThrows(RuntimeException.class, () -> player.play(game, -1));
    }

    @Test
    public void testCheckName() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        assertEquals("Petya", player.getName());

    }

    @Test
    public void testShouldSumGenreIfOneGameAndTwoPlayers() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        Player player1 = new Player("Roman");
        player.installGame(game);
        player1.installGame(game);

        player.play(game, 3);
        player1.play(game, 3);

        int expected = 3;
        int actual = player1.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }


    @Test
    public void testShouldAddedGetTitle() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertEquals("Нетология Баттл Онлайн", game.getTitle());
    }

}