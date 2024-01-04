import java.util.*;

/**
 * Represents a board.
 */
public class Board implements Ilayout,Cloneable{
    private ID[][] board;
    private ID playersTurn;
    private ID winner;
    private HashSet<Integer> movesAvailable;

    private int moveCount;
    protected boolean gameOver;


    Board() {
        board = new ID[N][M];
        movesAvailable = new HashSet<>();
        reset();
    }

    Board(ID[][] initialBoard, ID player) {
        this.board = new ID[initialBoard.length][];
        for (int i = 0; i < initialBoard.length; i++) {
            this.board[i] = Arrays.copyOf(initialBoard[i], initialBoard[i].length);
        }
        playersTurn = player;
    }

    /**
     * Set the cells to be blank and load the available moves (all the moves are
     * available at the start of the game).
     */
    private void initialize () {
        for (int row = 0; row < N; row++)
            for (int col = 0; col < M; col++) {
                board[row][col] = ID.Blank;
            }
        movesAvailable.clear();

        for (int i = 0; i < N*M; i++) {
            movesAvailable.add(i);
        }
    }

    /**
     * Restart the game with a new blank board.
     */
    public void reset() {
        moveCount = 0;
        gameOver = false;
        playersTurn = ID.X;
        winner = ID.Blank;
        initialize();
    }

    /**
     * Places an X or an O on the specified index depending on whose turn it is.
     * @param index     position starts in 0 and increases from left to right and from top to bottom
     * @return          true if the move has not already been played
     */
    public boolean move (int index) {
        return move(index% M, index/M);
    }

    /**
     * Places an X or an O on the specified location depending on who turn it is.
     * @param x         the x coordinate of the location
     * @param y         the y coordinate of the location
     * @return          true if the move has not already been played
     */
    public boolean move (int x, int y) {
        if (gameOver) {
            throw new IllegalStateException("Game over. No more moves can be played.");
        }

        if (board[y][x] == ID.Blank) {
            board[y][x] = playersTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove(y * N + x);


        // The game is a draw.
        if (moveCount == N * M ) {
            winner = ID.Blank;
            gameOver = true;
        }

        // Check for a winner.
        if(checkWinner(x,y, playersTurn)){
            //System.out.println("x: " + x +  " y: " + y);
            winner = playersTurn;
            gameOver = true;
        }

        playersTurn = (playersTurn == ID.X) ? ID.O : ID.X;
        return true;
    }

    /**
     * Verifica se há um vencedor após a última jogada.
     *
     * @param y           A coordenada y da última jogada
     * @param x           A coordenada x da última jogada
     * @param playersTurn O jogador atual
     * @return            True se há um vencedor após a última jogada
     */

    public boolean checkWinner(int y, int x, ID playersTurn){
        //vertical check
        for(int i = 0;  i < N; i++){
            if(board[i][y] != playersTurn){break;}
            if(i == N - 1){
                return true;
            }
        }
        //horizontal check
        for(int i = 0; i < M; i++){
            if(board[x][i] != playersTurn){break;}
            if (i == M-1){
                return true;
            }
        }
        //Diagonal check
        for(int i = 0; i < M; i++){
            if(board[i][i] != playersTurn){break;}
            if (i == M-1){
                return true;
            }
        }
        //Anti-Diagonal Check
        if(x + y == M - 1){
            for (int i = 0; i < M; i++){
                if(board[i][(M-1)-i] != playersTurn){break;}
                if(i == M - 1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retorna o tabuleiro.
     *
     * @return Matrix de IDs do tabuleiro
     */

    public ID[][] getBoard(){
        return this.board;
    }

    /**
     * Check to see if the game is over (if there is a winner or a draw).
     * @return          true if the game is over
     */
    public boolean isGameOver () {
        return gameOver;
    }



    /**
     * Check to see whose turn it is.
     * @return          the player whose turn it is
     */
    public ID getTurn () {
        return playersTurn;
    }

    /**
     * @return          the player who won (or Blank if the game is a draw)
     */
    public ID getWinner () {
        if (!gameOver) {
            throw new IllegalStateException("Not over yet!");
        }
        return winner;
    }

    /**
     * Get the indexes of all the positions on the board that are empty.
     * @return          the empty cells
     */
    public HashSet<Integer> getAvailableMoves () {
        return movesAvailable;
    }



    /**
     * @return  a deep copy of the board
     */
    @Override
    public Object clone() {
        try {
            Board b = (Board) super.clone();

            b.board = new ID[N][M];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    b.board[i][j] = this.board[i][j];

            b.playersTurn = this.playersTurn;
            b.winner = this.winner;
            b.movesAvailable = new HashSet<>(this.movesAvailable); // Use copy constructor
            b.moveCount = this.moveCount;
            b.gameOver = this.gameOver;
            return b;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (board[y][x] == ID.Blank) {
                    sb.append("-");
                } else {
                    sb.append(board[y][x].name());
                }
                sb.append(" ");
            }
            if (y != N -1) {
                sb.append("\n");
            }
        }
        return new String(sb);
    }

    /**
     *
     * @return the children of the receiver.
     */
    public List<Ilayout> children() {
        List<Ilayout> moves = new ArrayList<>();
        for (Integer item : getAvailableMoves()) {
            Board temp = (Board) this.clone();
            temp.move(item);
            moves.add(temp);
        }
        return moves;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Board that = (Board) other;

        for (int x=0; x<N; x++)
            for (int y=0; y<M; y++)
                if (board[x][y] != that.board[x][y]) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }

    public boolean isBlank (int index) {
        int x=index/M;
        int y=index%M;
        return (board[x][y] == ID.Blank);
    }
}
