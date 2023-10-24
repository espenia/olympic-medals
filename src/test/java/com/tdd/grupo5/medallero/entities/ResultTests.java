package com.tdd.grupo5.medallero.entities;

import com.tdd.grupo5.medallero.entities.Event;
import com.tdd.grupo5.medallero.entities.EventType;
import com.tdd.grupo5.medallero.entities.Person;
import com.tdd.grupo5.medallero.entities.Result;
import com.tdd.grupo5.medallero.entities.Time;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResultTests {

    Person participant = new Person("Michael Phelps", 1985L);
    Person non_participant = new Person("Lionel Messi", 1985L);
    EventType marathon = new EventType("Marathon", 100);
    Event event = new Event(marathon);
    Time time_result = new Time(0, 2, 55);
    Time time_null = new Time(0, 0, 0);
    Result result = new Result(participant, event, 1, time_result);
    Result result_non_participant = new Result(non_participant, event, 0, time_null);
    Result result_wrong_standing = new Result(participant, event, 0, time_result);
    Result result_wrong_time = new Result(participant, event, 2, time_null);

    @Test
    void test01CreatedResultBelongsToAPerson() {
        assert result.getAthlete() != null;
    }

    @Test
    void test02AnAthleteWithAValidStandingAndValidTimeResultFinishedTheRace() {
        assert result.athleteFinishedTheRace();
    }

    @Test
    void test03AnAthleteWithAZeroTimedResultAndStandingMeansItDidNotFinishTheRace() {
        assert !result_non_participant.athleteFinishedTheRace();
    }

    @Test
    void test04AnAthleteWithAZeroStandingAndPositiveTimeActsLikeAnEntryWhoDidNotFinishTheRace() {
        assert !result_wrong_standing.athleteFinishedTheRace();
    }

    @Test
    void test05AnAthleteWithAPositiveStandingAndZeroTimeActsLikeAnEntryWhoDidNotFinishTheRace() {
        assert !result_wrong_time.athleteFinishedTheRace();
    }

    @Test
    void test06CreatedResultCorrespondsToAnEvent() {
        assert result.getEvent() != null;
    }

}
