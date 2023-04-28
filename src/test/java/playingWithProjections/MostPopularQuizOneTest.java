package playingWithProjections;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MostPopularQuizOneTest {
    private final MostPopularQuizOne mostPopularQuizOne = new MostPopularQuizOne();

    @Test
    public void mostPopularQuiz() {

        mostPopularQuizOne.projection(quizWasCreatedWithTitle("quiz1", "Daan zijn quiz"));
        mostPopularQuizOne.projection(gameWasOpened("game1", "quiz1"));
        mostPopularQuizOne.projection(gameFinished("game1"));

        assertThat(mostPopularQuizOne.getResult())
            .isEqualTo("Daan zijn quiz, quiz1");
    }

    private static Event quizWasCreatedWithTitle(String quizId, String quizTitle) {
        Event event = new Event();
        event.setType("QuizWasCreated");
        //{"id":"606cf2b8-d97e-4a9d-872e-47a78ef936da","type":"QuizWasCreated","timestamp":"2019-08-02T02:54:30Z","payload":{"quiz_title":"transmitting Oliver Sutton","quiz_id":"1bdc990d-92d9-41ea-a3c6-043f6045597d","owner_id":"627cc7a4-1f43-4ae7-8599-f511df5c89cd"}}
        event.setPayload(payloadWithQuiz(quizId, quizTitle));
        return event;
    }

    private static Event gameFinished(String gameId) {
        Event event = new Event();
        event.setType("GameWasFinished");
        HashMap<String, String> payload = new HashMap<>();
        payload.put("game_id", gameId);
        event.setPayload(payload);
        return event;
    }

    private static Event gameWasOpened(String gameId, String quizId) {
        Event event = new Event();
        event.setType("GameWasOpened");
        HashMap<String, String> payload = new HashMap<>();
        payload.put("quiz_id", quizId);
        payload.put("game_id", gameId);
        event.setPayload(payload);
        return event;
    }

    private static Map<String, String> payloadWithQuiz(String quizId, String quizTitle) {
        Map<String, String> payload = new java.util.HashMap<>();
        payload.put("quiz_id", quizId);
        payload.put("quiz_title", quizTitle);
        return payload;
    }
}
