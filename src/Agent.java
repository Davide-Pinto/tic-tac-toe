import java.util.LinkedList;
import java.util.List;

/**
 * Implementação de um agente O usando o algoritmo Minimax com cortes alfa-beta para um jogo de tabuleiro.
 *
 * @author Davide Pinto
 *
 */
public class Agent {
    private static int X_WINS;
    private static int O_WINS;
    private static int X_Line;
    private static int O_Line;
    private static final int maxDepth = 3;

    public static void player(Board board){
        Ilayout.ID player =  board.getTurn();
        if(player == Ilayout.ID.O){
            X_WINS = -100;
            O_WINS = 100;
            X_Line = -1;
            O_Line = 1;
        }
        else{
            X_WINS = 100;
            O_WINS = -100;
            X_Line = 1;
            O_Line = -1;
        }
    }

    /**
     * Escolhe a melhor jogada para o agente usando o algoritmo Minimax com cortes alfa-beta.
     *
     * @param board O tabuleiro atual do jogo.
     * @return A melhor jogada a ser feita.
     */



    public static int play(Board board) {
        List<Integer> availableMoves = new LinkedList<>(board.getAvailableMoves());
        player(board);

        int bestMove = -1;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int move : availableMoves) {
            Board tempBoard = (Board) board.clone();
            tempBoard.move(move);

            int score = minimax(tempBoard, 0, false, alpha, beta);

            if (score > alpha) {
                alpha = score;
                bestMove = move;
            }
        }

        return bestMove;
    }
    /**
     * Algoritmo Minimax com cortes alfa-beta para avaliar as possíveis jogadas.
     *
     * @param board            O Layout atual do tabuleiro.
     * @param depth            A profundidade da busca.
     * @param maximizingPlayer Define se é o turno do max ou do min.
     * @param alpha            Melhor valor para o agente.
     * @param beta             Melhor valor para o oponente.
     * @return avaliaçao de layouts.
     */


    static int minimax(Board board, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (board.isGameOver() || depth == maxDepth) {
            return evaluate(board);
        }

        List<Ilayout> children = board.children();
        int bestValue;

        if (maximizingPlayer) {
            bestValue = Integer.MIN_VALUE;

            for (Ilayout child : children) {
                int value = minimax((Board) child, depth + 1, false, alpha, beta);
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(alpha, bestValue);

                if (beta <= alpha) {
                    break; // Beta cutoff
                }
            }
        } else {
            bestValue = Integer.MAX_VALUE;

            for (Ilayout child : children) {
                int value = minimax((Board) child, depth + 1, true, alpha, beta);
                bestValue = Math.min(bestValue, value);
                beta = Math.min(beta, bestValue);

                if (beta <= alpha) {
                    break; // Alpha cutoff
                }
            }
        }

        return bestValue;
    }
    /**
     * Avalia o tabuleiro num determinado estado.
     *
     * @param board O layout que está a ser avaliado.
     * @return O valor de avaliação do tabuleiro.
     */

    public static int evaluate(Board board) {
        int score = 0;

        // Check rows and columns
        for (int i = 0; i < Board.N; i++) {
            score += evaluateLine(board.getBoard()[i]); // Rows
            score += evaluateLine(getColumn(board.getBoard(), i)); // Columns
        }

        // Check diagonals
        score += evaluateLine(getDiagonal(board.getBoard(), true)); // Main diagonal
        score += evaluateLine(getDiagonal(board.getBoard(), false)); // Anti-diagonal

        return score;
    }
    /**
     * Avalia uma linha do tabuleiro para verificar se há um vencedor ou uma potencial jogada vencedora futura.
     *
     * @param line A linha a ser avaliada.
     * @return O valor de avaliação da linha.
     */

    private static int evaluateLine(Ilayout.ID[] line) {
        int xCount = 0;
        int oCount = 0;
        int blankCount = 0;

        for (Ilayout.ID cell : line) {
            if (cell == Ilayout.ID.X) {
                xCount++;
            } else if (cell == Ilayout.ID.O) {
                oCount++;
            }
            else
                blankCount++;
        }

        if (xCount == Board.N) {
            return X_WINS; // X wins
        } else if (oCount == Board.N) {
            return O_WINS; // O wins
        } else {

            if (xCount > 0 && oCount == 0 && blankCount > 0) {
                return X_Line; // X has a potential future line
            } else if (xCount == 0 && oCount > 0 && blankCount > 0) {
                return O_Line; // O has a potential future line
            } else {
                return 0; // No winner or potential future line
            }
        }
    }

    /**
     * Obtém uma coluna específica do tabuleiro.
     *
     * @param board O tabuleiro atual do jogo.
     * @param col   O índice da coluna desejada.
     * @return Um array representando a coluna desejada.
     */
    private static Ilayout.ID[] getColumn(Ilayout.ID[][] board, int col) {
        Ilayout.ID[] column = new Ilayout.ID[Board.N];
        for (int i = 0; i < Board.N; i++) {
            column[i] = board[i][col];
        }
        return column;
    }

    /**
     * Obtém a diagonal principal ou anti-diagonal do tabuleiro.
     *
     * @param board O tabuleiro atual do jogo.
     * @param main  Define se é a diagonal principal ou anti-diagonal.
     * @return Um array representando a diagonal principal ou anti-diagonal.
     */
    private static Ilayout.ID[] getDiagonal(Ilayout.ID[][] board, boolean main) {
        Ilayout.ID[] diagonal = new Ilayout.ID[Board.N];
        for (int i = 0; i < Board.N; i++) {
            diagonal[i] = main ? board[i][i] : board[i][Board.N - 1 - i];
        }
        return diagonal;
    }

}
