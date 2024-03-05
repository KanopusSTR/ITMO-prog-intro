package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        //System.out.println("Current position");
        //System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        int rowF = 0;
        int colF = 0;
        while (true) {
            try {
                // :NOTE: обработка входных данных
                rowF = in.nextInt() - 1;
                colF = in.nextInt() - 1;
                in = new Scanner(System.in);
            } catch (NoSuchElementException e) {
                System.out.println("Incorrect move, please write more numbers");
                in = new Scanner(System.in);
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect move, please write numbers");
            }
            Move aa = new Move(rowF, colF, position.getTurn());
            if (position.isValid(aa)) {
                return new Move(rowF, colF, position.getTurn());
            } else {
                System.out.println("Incorrect move, please write correct numbers");
            }
        }
    }
}
