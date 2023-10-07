package com.tdd.grupo5.medallero;
import com.tdd.grupo5.medallero.entities.Time;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimeTests {

    Time correct_time = new Time(1, 25, 40);
    Time incorrect_time_minutes = new Time(1, 75, 40);
    Time incorrect_time_seconds = new Time(1, 25, 80);

    @Test
    void test01ACorrectNewTimeHasAPositiveAmountOfHours() {
        assert correct_time.getHours() > 0;
    }

    @Test
    void test02ACorrectNewTimeHasAPositiveAmountOfMinutes() {
        assert correct_time.getMinutes() > 0;
    }

    @Test
    void test03ACorrectNewTimeHasAPositiveAmountOfSeconds() {
        assert correct_time.getSeconds() > 0;
    }

    @Test
    void test04AnIncorrectAmountOfMinutesGetsRepurposedIntoHours() {
        assert incorrect_time_minutes.getHours() == 2;
    }

    @Test
    void test05AnIncorrectAmountOfMinutesGetsItsMinutesCorrected() {
        assert incorrect_time_minutes.getMinutes() == 15;
    }

    @Test
    void test06AnIncorrectAmountOfSecondsGetsRepurposedIntoMinutes() {
        assert incorrect_time_seconds.getMinutes() == 26;
    }

    @Test
    void test07AnIncorrectAmountOfSecondsGetsItsSecondsCorrected() {
        assert incorrect_time_seconds.getSeconds() == 20;
    }

    @Test
    void test08SettingANewAmountOfHoursOnlyChangesSaidTimeAmountOfHours() {
        correct_time.setNewTime(2, 0, 0);
        assert correct_time.getHours() == 2;
    }

    @Test
    void test09SettingTheAmountOfMinutesToZeroForAnExistingTimeDoesNotChangeSaidTimeAmountOfMinutes() {
        correct_time.setNewTime(2, 0, 0);
        assert correct_time.getMinutes() == 25;
    }

    @Test
    void test10SettingTheAmountOfSecondsToZeroForAnExistingTimeDoesNotChangeSaidTimeAmountOfSeconds() {
        correct_time.setNewTime(2, 0, 0);
        assert correct_time.getSeconds() == 40;
    }

    @Test
    void test11SettingANewAmountOfMinutesOnlyChangesSaidTimeAmountOfMinutes() {
        correct_time.setNewTime(0, 50, 0);
        assert correct_time.getMinutes() == 50;
    }

    @Test
    void test12SettingTheAmountOfHoursToZeroForAnExistingTimeDoesNotChangeSaidTimeAmountOfHours() {
        correct_time.setNewTime(0, 50, 0);
        assert correct_time.getHours() == 1;
    }

    @Test
    void test13SettingANewAmountOfSecondsOnlyChangesSaidTimeAmountOfSeconds() {
        correct_time.setNewTime(0, 0, 10);
        assert correct_time.getSeconds() == 10;
    }

    @Test
    void test14SettingTheAmountOfSecondsTo60ResetsTheNewAmountOfSecondsToZero() {
        correct_time.setNewTime(0, 0, 60);
        assert correct_time.getSeconds() == 0;
    }

    @Test
    void test15SettingTheAmountOfSecondsTo60AddsAnotherMinuteToThatTime() {
        correct_time.setNewTime(0, 0, 60);
        assert correct_time.getMinutes() == 26;
    }

    @Test
    void test16SettingTheAmountOfMinutesTo60ResetsTheNewAmountOfMinutesToZero() {
        correct_time.setNewTime(0, 60, 0);
        assert correct_time.getMinutes() == 0;
    }

    @Test
    void test17SettingTheAmountOfMinutesTo60AddsAnotherHourToThatTime() {
        correct_time.setNewTime(0, 60, 0);
        assert correct_time.getHours() == 2;
    }

    @Test
    void test18DeletingAnExistingTimeResetsItsAmountOfHoursToZero() {
        correct_time.delete();
        assert correct_time.getHours() == 0;
    }

    @Test
    void test19DeletingAnExistingTimeResetsItsAmountOfMinutesToZero() {
        correct_time.delete();
        assert correct_time.getMinutes() == 0;
    }

    @Test
    void test20DeletingAnExistingTimeResetsItsAmountOfSecondsToZero() {
        correct_time.delete();
        assert correct_time.getSeconds() == 0;
    }
}
