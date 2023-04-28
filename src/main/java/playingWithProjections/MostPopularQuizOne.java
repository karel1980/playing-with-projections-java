package playingWithProjections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MostPopularQuizOne {

    private final Map<String, String> quizTitleByQuizId = new HashMap<>();
    private final Map<String, String> quizIdByGameId = new HashMap<>();
    private final Map<String, Integer> countByQuizId = new HashMap<>();

    public void projection(Event event) {
        Map<String, String> payload = event.getPayload();
        if ("QuizWasCreated".equals(event.getType())) {
            quizTitleByQuizId.put(payload.get("quiz_id"), payload.get("quiz_title"));
        }

        if ("GameWasOpened".equals(event.getType())) {
            quizIdByGameId.put(payload.get("game_id"), payload.get("quiz_id"));
        }

        if ("GameWasFinished".equals(event.getType())) {
            String gameId = payload.get("game_id");
            String quizId = quizIdByGameId.get(gameId);
            countByQuizId.put(quizId, 1 + countByQuizId.getOrDefault(quizId,0));
        }
    }

    public String getResult() {
        return countByQuizId.entrySet().stream()
            .sorted(Comparator.comparing(entry -> -entry.getValue()))
            .limit(10)
            .map(entry -> "%s, %s".formatted(quizTitleByQuizId.get(entry.getKey()), entry.getKey()))
            .collect(Collectors.joining("\n"));
    }
}
