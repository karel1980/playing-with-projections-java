package playingWithProjections;

public class Main {
    public static void main(String[] args) {
        String file = FilePathFrom(args);

        CountEvents eventCountProjector = new CountEvents();
        CountPlayers playerCountProjector = new CountPlayers();
        CountPlayersPerMonth playersPerMonthProjector = new CountPlayersPerMonth();

        MostPopularQuizOne quizOne = new MostPopularQuizOne();

        new EventStore(
            eventCountProjector::projection,
            playerCountProjector::projection,
            playersPerMonthProjector::projection,
            quizOne::projection
        ).replay(file);

        System.out.printf("number of events: %d%n", eventCountProjector.getResult());
        System.out.printf("number of players: %d%n", playerCountProjector.getResult());
        System.out.printf("number of players per month: %s%n", playersPerMonthProjector.getResult());
        System.out.printf("challenge most popular quizes:%n%s%n", quizOne.getResult());
    }

    private static String FilePathFrom(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("Please specify a file to replay");
        return args[0];
    }
}
