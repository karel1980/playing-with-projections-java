package playingWithProjections;

import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CountPlayersPerMonth {

    private HashMap<YearMonth, Integer> map = new HashMap<>();

    Map<YearMonth, Integer> getResult() {
        return map;
    }

    public void projection(Event event) {
        if ("PlayerHasRegistered".equals(event.getType())) {
            ZonedDateTime timestamp = event.getTimestamp();
            YearMonth yearMonth = YearMonth.from(timestamp);
            map.put(yearMonth, 1 + map.getOrDefault(yearMonth, 0));
        }
    }
}
