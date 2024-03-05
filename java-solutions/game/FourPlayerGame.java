package game;

import java.io.UncheckedIOException;

public class FourPlayerGame {
    private final Board board;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    int count = 3;

    public FourPlayerGame(Board board, Player player1, Player player2, Player player3, Player player4, int count) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.count = count;
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = makeMove(player1, 1, log);
            if (result1 == -2) {
                System.out.println("First player loose");
                player1 = new NullPlayer();
            }
            if (result1 != -1)  {
                if (result1 == 0 || count == 1) {
                    return result1;
                }
                count--;
                System.out.println("First player win");
                player1 = new NullPlayer();
            }
            final int result2 = makeMove(player2, 2, log);
            if (result2 == -2) {
                System.out.println("Second player loose");
                player2 = new NullPlayer();
            }
            if (result2 != -1)  {
                if (result2 == 0 || count == 1) {
                    return result2;
                }
                count--;
                System.out.println("Second player win");
                player2 = new NullPlayer();
            }
            final int result3 = makeMove(player3, 3, log);
            if (result3 == -2) {
                System.out.println("Third player loose");
                player3 = new NullPlayer();
            }
            if (result3 != -1)  {
                if (result3 == 0 || count == 1) {
                    return result3;
                }
                count--;
                System.out.println("Third player win");
                player3 = new NullPlayer();
            }
            final int result4 = makeMove(player4, 3, log);
            if (result4 == -2) {
                System.out.println("Fourth player loose");
                player4 = new NullPlayer();
            }
            if (result4 != -1)  {
                if (result4 == 0 || count == 1) {
                    return result4;
                }
                count--;
                System.out.println("Fourth player win");
                player4 = new NullPlayer();
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        try {
            final Move move = player.makeMove(board.getPosition());
            if (move == null) {
                board.makeMove(null);
                return -1;
            }
            final GameResult result = board.makeMove(move);
            if (log) {
                System.out.println();
                System.out.println("Player: " + no);
                System.out.println(move);
                System.out.println(board);
                System.out.println("Result: " + result);
            }
            switch (result) {
                case WIN:
                    return no;
                case DRAW:
                    return 0;
                case UNKNOWN:
                    return -1;
                default:
                    throw new AssertionError("Unknown makeMove result " + result);
            }
        } catch (UncheckedIOException e) {
            return -2;
        }
    }
}
