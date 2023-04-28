package playingWithProjections;

public class CountPlayers {

    private int counter = 0;

    int getResult() {
        return counter;
    }

    public void projection(Event event) {
        if ("PlayerHasRegistered".equals(event.getType())) {
            counter++;
        }
    }
}
