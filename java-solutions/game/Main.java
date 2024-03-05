package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many players?");
        int pl = 0;
        while (true) {
            try {
                pl = in.nextInt();
                while (pl < 2 || pl > 4) {
                    System.out.println("Incorrect player number, please write numbers again");
                    pl = in.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please, write numbers");
                in = new Scanner(System.in);
            }
        }
        System.out.println("Please, specify the size of the board");
        int m = 0;
        int n = 0;
        while (true) {
            try {
                // :NOTE: обработка входных данных
                m = in.nextInt();
                n = in.nextInt();
                while (m < 0 || n < 0) {
                    System.out.println("Please, write right size of the board");
                    // :NOTE: обработка входных данных
                    m = in.nextInt();
                    n = in.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please, write numbers");
                in = new Scanner(System.in);
            }
        }
        System.out.println("Please, specify how many must go");
        int k = 0;
        while (true) {
            try {
                k = in.nextInt();
                while (k < 1) {
                    System.out.println("Please, write right how many must go");
                    // :NOTE: обработка входных данных
                    k = in.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please, write numbers");
                in = new Scanner(System.in);
            }
        }
        int result = 0;
        // :NOTE: читать конфигурацию игры из входных данных
        if (pl == 4) {
            result = new FourPlayerGame(
                    new TicTacToeBoardFour(m, n, k),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n)
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)), pl - 1
            ).play(true);
        } else if (pl == 3) {
            result = new FourPlayerGame(
            new TicTacToeBoardFour(m, n, k),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n)
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)),
                    new NullPlayer(), pl - 1
            ).play(true);
        } else {
            result = new FourPlayerGame(
                    new TicTacToeBoardFour(m, n, k),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n),
                    //                new RandomPlayer(m, n)
                    new HumanPlayer(new Scanner(System.in)),
                    new HumanPlayer(new Scanner(System.in)),
                    new NullPlayer(),
                    new NullPlayer(), pl - 1
            ).play(true);
        }
        switch (result) {
            case 1:
                System.out.println("First player win");
                break;
            case 2:
                System.out.println("Second player win");
                break;
            case 3:
                System.out.println("Third player win");
                break;
            case 0:
                System.out.println("Draw");
                break;
            case -2:
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
    }
}
