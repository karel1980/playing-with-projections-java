package playingWithProjections;

public class Main {
    public static void main(String[] args) {
        String file = FilePathFrom(args);
        CountEvents eventCountProjector = new CountEvents();
        CountPlayers playerCountProjector = new CountPlayers();
        CountPlayersPerMonth playersPerMonthProjector = new CountPlayersPerMonth();
        new EventStore(eventCountProjector::projection, playerCountProjector::projection, playersPerMonthProjector::projection)
                .replay(file);

        System.out.printf("number of events: %d%n", eventCountProjector.getResult());
        System.out.printf("number of players: %d%n", playerCountProjector.getResult());
        System.out.printf("number of players per month: %s%n", playersPerMonthProjector.getResult());
    }

    private static String FilePathFrom(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("Please specify a file to replay");
        return args[0];
    }
}
