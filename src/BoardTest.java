import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void checkWinnerTest_1() {
        Ilayout.ID playersTurn = Ilayout.ID.X;
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O}
        };
        Board board = new Board(test, Ilayout.ID.X);
        boolean result = board.checkWinner(0, 0,playersTurn);
        assertTrue(result, "Expected a winner at (0,0)");
    }

    @Test
    void checkWinnerTest_2() {
        Ilayout.ID playersTurn = Ilayout.ID.X;
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.X},
                {Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.O, Ilayout.ID.Blank},
                {Ilayout.ID.Blank, Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.O}
        };
        Board board = new Board(test, Ilayout.ID.X);
        boolean result = board.checkWinner(0, 0,playersTurn);
        assertFalse(result, "Expected a winner at (0,0)");
    }

    @Test
    void checkWinnerTest_3() {
        Ilayout.ID playersTurn = Ilayout.ID.O;
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O, Ilayout.ID.O},
                {Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank},
                {Ilayout.ID.Blank, Ilayout.ID.X, Ilayout.ID.X, Ilayout.ID.Blank},
                {Ilayout.ID.X, Ilayout.ID.O, Ilayout.ID.X, Ilayout.ID.X}
        };
        Board board = new Board(test, Ilayout.ID.O);
        boolean result = board.checkWinner(0, 0,playersTurn);
        assertTrue(result, "Expected a winner at (0,0)");
    }

    @Test
    void checkWinnerTest_4() { //test all blanks

        Ilayout.ID playersTurn = Ilayout.ID.O;
        Ilayout.ID[][] test = new Ilayout.ID[][]{
                {Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank},
                {Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank},
                {Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank},
                {Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank}
        };
        Board board = new Board(test, Ilayout.ID.O);
        boolean result = board.checkWinner(0, 0,playersTurn);
        assertFalse(result, "Expected a winner at (0,0)");
    }
}
