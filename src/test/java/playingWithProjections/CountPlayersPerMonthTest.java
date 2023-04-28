package playingWithProjections;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class CountPlayersPerMonthTest {

    Instant REFERENCE_TIME = Instant.parse("2022-05-27T00:00:00Z");

    @Test
    public void countWhenPlayerRegistered() {
        CountPlayersPerMonth countPlayers = new CountPlayersPerMonth();

        countPlayers.projection(playerRegistrationEvent(REFERENCE_TIME));
        countPlayers.projection(playerRegistrationEvent(REFERENCE_TIME.plus(1, ChronoUnit.DAYS)));
        countPlayers.projection(playerRegistrationEvent(REFERENCE_TIME.minus(30, ChronoUnit.DAYS)));

        HashMap<YearMonth, Integer> expected = new HashMap<>();
        expected.put(YearMonth.parse("2022-05"), 2);
        expected.put(YearMonth.parse("2022-04"), 1);
        assertThat(countPlayers.getResult())
            .isEqualTo(expected);
    }

    private static Event playerRegistrationEvent(Instant timestamp) {
        Event event = new Event();
        event.setType("PlayerHasRegistered");
        event.setTimestamp(timestamp.atZone(ZoneOffset.UTC));
        return event;
    }
}
