package game;

// :NOTE: добавить SequentialPlayer
public interface Player {
    // :NOTE: makeMove может бросать unchecked exception
    Move makeMove(Position position);
}