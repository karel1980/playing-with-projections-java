package playingWithProjections;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CountPlayersTest {

    @Test
    public void countWhenPlayerRegistered() {
        CountPlayers countPlayers = new CountPlayers();

        countPlayers.projection(event());

        assertThat(countPlayers.getResult())
            .isEqualTo(1);
    }

    private static Event event() {
        Event event = new Event();
        event.setType("PlayerHasRegistered");
        return event;
    }
}
