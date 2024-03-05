package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoardFour implements Board, Position {

    private final Cell[][] field;
    private Cell turn;
    int m;
    int n;
    int k;

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0",
            Cell.M, "-",
            Cell.S, "|"
    );

    public TicTacToeBoardFour(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (move == null) {
            // :NOTE: выделить в функцию, убрать тернарные операторы
            scroll();
            return GameResult.UNKNOWN;
        }
        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin()) {
            scroll();
            return GameResult.WIN;
        }
        if (checkDraw()) {
            scroll();
            return GameResult.DRAW;
        }
        scroll();
        return GameResult.UNKNOWN;
    }

    private void scroll() {
        if (turn == Cell.X) {
            turn = Cell.O;
        } else if(turn == Cell.O) {
            turn = Cell.M;
        } else if(turn == Cell.M) {
            turn = Cell.S;
        } else {
            turn = Cell.X;
        }
    }

    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (field[r][c] == Cell.E) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return false;
    }

    private boolean checkWin() {
        // :NOTE: выделить общий код в функции
        for (int c = 0; c < n; c++) {
            int count2 = 0;
            for (int r = 0; r < m; r++) {
                if (field[r][c] == turn) {
                    count2++;
                    if(count2 == k) {
                        return true;
                    }
                } else {
                    count2 = 0;
                }
            }
        }
        for (int r = 0; r < m; r++) {
            int count1 = 0;
            for (int c = 0; c < n; c++) {
                if (field[r][c] == turn) {
                    count1++;
                    if(count1 == k) {
                        return true;
                    }
                } else {
                    count1 = 0;
                }
                int count3 = 0;
                for (int x = 0; x < Math.min(m - r, n - c); x++) {
                    if (field[r + x][c + x] == turn) {
                        count3++;
                        if (count3 == k) {
                            return true;
                        }
                    } else {
                        count3 = 0;
                    }
                }
                int count4 = 0;
                for (int x = 0; x < Math.min(m - r, c + 1); x++) {
                    if (field[r + x][c - x] == turn) {
                        count4++;
                        if (count4 == k) {
                            return true;
                        }
                    } else {
                        count4 = 0;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        char[] white = new char[Integer.toString(n + 1).length()];
        Arrays.fill(white, ' ');
        final StringBuilder sb = new StringBuilder(new String(white) + " ");
        for (int i = 0; i < n; i++) {
            sb.append(i + 1);
            sb.append(" ".repeat(Math.max(0, Integer.toString(n + 1).length() - Integer.toString(i + 1).length() + 1)));
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < m; r++) {
            sb.append(r + 1);
            sb.append(" ");
            sb.append(" ".repeat(Math.max(0, Integer.toString(n + 1).length() - Integer.toString(r + 1).length())));
            for (Cell cell : field[r]) {
                String qq = " ".repeat(Integer.toString(n + 1).length());
                sb.append(CELL_TO_STRING.get(cell)).append(qq);
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
