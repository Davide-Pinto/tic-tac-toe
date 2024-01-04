import java.util.*;


/**
 * For playing in the console.
 */


public class Console {
    private Board board;
    private Scanner sc = new Scanner(System.in);

    private static List<Ilayout.ID> lista = new ArrayList<>(100);

    private static long Xtime = 0;
    private static long Otime = 0;

    private Console() {
        board = new Board();
    }

    /**
     * Game on
     */

    private void play () {
        while (true) {
            playMove();
            if (board.isGameOver()) {
                lista.add(board.getWinner());
                printWinner();
                //System.out.println("Expanded: " + XAgent.expanded_nodes);
                break;
            }
        }
    }

    
    /**
     * Handle the move to be played,(
     */
   
    private void playMove () {
    	int position;
        while (!board.isGameOver()) {

            if (board.getTurn() == Ilayout.ID.X) {
                //position=getHumanMove();
                  //position = Agent.play(board);
                long startTime = System.currentTimeMillis();
                position = Agent.play(board);
                  //position = XAgent.play(board);
                long totalTime = System.currentTimeMillis() - startTime;
                Xtime += totalTime;
                //position = RandomAgent.play(board);
                //System.out.println("X Move: " + position);
                board.move(position);

            } else {
                //position=getHumanMove();
                //position = OAgent.play(board);
                long startTime = System.currentTimeMillis();
                position = Agente2.play(board);
                long totalTime = System.currentTimeMillis() - startTime;
                Otime += totalTime;
                //position = RandomAgent.play(board);
               // System.out.println("O Move: " + position);
                board.move(position);
            }
        }
    }
 
    private void printGameStatus () {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name() + "'s turn.");
    }

    /**
     * For reading in and interpreting the move that the user types into the console.
     */
    private int getHumanMove() {
        printGameStatus ();
        //System.out.print("Index of move: ");

        int move = sc.nextInt();

        if (move < 0 || move >= Ilayout.N* Ilayout.M) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe index of the move must be between 0 and "
                    + (Ilayout.N * Ilayout.M - 1) + ", inclusive.");
        } else if (!board.isBlank(move)) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe selected index must be blank.");
        }
        return move;
    }

    
    private void printWinner () {
        Ilayout.ID winner = board.getWinner();

        System.out.println("\n" + board + "\n");

        if (winner == Ilayout.ID.Blank) {
            System.out.println("It's a draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

    private static void stats(int rep){ //execution stats
        int x_vic = 0,draw = 0, o_vic = 0;
        for (Ilayout.ID id : lista) {
            if (id == Ilayout.ID.X) {
                x_vic++;
            }
            if (id == Ilayout.ID.Blank) {
                draw++;
            }
            if (id == Ilayout.ID.O) {
                o_vic++;
            }
        }
        System.out.println("X_Victories: " + x_vic);
        System.out.println("Percentage of X_Victories: " + (double)x_vic/rep*100 + "%");
        System.out.println("O_Victories: " + o_vic);
        System.out.println("Percentage of O_Victories: " + (double)o_vic/rep*100 + "%");
        System.out.println("Draw: " + draw);
        System.out.println("Percentage of Draw: " + (double)draw/rep*100 + "%");

        System.out.println("Tempo por jogo de X: " + Xtime*1.0f/rep+ " milisecs");
        System.out.println("Tempo por jogo de O: " + Otime*1.0f/rep+ " milisecs");

    }

    

    public static void main(String[] args)  {
    	    final int repetitions=3;
    	    long times = 0;
    	    for(int i=0; i<repetitions; i++) {
    	    	Console game = new Console();
	    	    long startTime = System.currentTimeMillis();
	    	    game.play();      	
	    	    long totalTime = System.currentTimeMillis() - startTime;
	    	    times += totalTime;
    	    }
            System.out.println("Av Time: " + times*1.0f/repetitions+ " milisecs");
            stats(repetitions);
    }

}
