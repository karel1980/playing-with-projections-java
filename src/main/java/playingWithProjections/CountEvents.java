package playingWithProjections;

class CountEvents {
    private int counter = 0;

    int getResult() {
        return counter;
    }

    void projection(Event event) {
        counter++;
    }
}
