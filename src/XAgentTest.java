import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XAgentTest {

    //Warning: tests were run in a 4x4
    @Test
    void evaluation_vertical_win_x() {
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.Blank, Ilayout.ID.O},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O}
        };
        Board board = new Board(test, Ilayout.ID.X);
        int result = XAgent.evaluate(board);
        assertEquals(98, result);
    }

    @Test
    void evaluation_vertical_win_O() {
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O},
                {Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O}
        };
        Board board = new Board(test, Ilayout.ID.O);
        int result = OAgent.evaluate(board);
        assertEquals(100, result);
    }

    //Testes Diagonal
    @Test
    void evaluation_diagonal_win_X() {
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O},
                {Ilayout.ID.Blank, Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.X}
        };
        Board board = new Board(test, Ilayout.ID.X);
        int result = XAgent.evaluate(board);
        assertEquals(101, result);
    }

    @Test
    void evaluation_diagonal_win_O() {
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O},
                {Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O}
        };
        Board board = new Board(test, Ilayout.ID.O);
        int result = XAgent.evaluate(board);
        assertEquals(-202, result);
    }

    @Test
    void evaluation_neither_X_O_wins_test_1() {
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O},
                {Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O}
        };
        Board board = new Board(test, Ilayout.ID.O);
        int result = XAgent.evaluate(board);
        assertEquals(-1, result);
    }

    @Test
    public void testMinimaxXWins() {
        // X wins scenario
        Board board = new Board();
        board.move(0);
        board.move(4);
        board.move(1);
        board.move(5);
        board.move(2);
        board.move(6);
        board.move(3);

        int result = OAgent.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

        assertEquals(-100, result);
    }



    @Test
    public void testMinimaxNoWinner() {
        // No winner scenario
        Board board = new Board();
        board.move(0);
        board.move(4);
        board.move(1);
        board.move(5);
        board.move(8);

        int result = XAgent.minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

        assertEquals(-1, result);
    }

}