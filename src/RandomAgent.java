
/**
 * Representa um agente de demonstração reativo que joga de forma aleatória.
 */
public class RandomAgent {
    /**
     * Retorna um movimento aleatório válido com base nos movimentos disponíveis no tabuleiro fornecido.
     *
     * @param board o tabuleiro no qual o agente deve jogar.
     * @return Um movimento aleatório válido no tabuleiro.
     */
    static int play(Ilayout board) {
        // Cria um array para armazenar os movimentos disponíveis no tabuleiro
        int[] moves = new int[board.getAvailableMoves().size()];
        // Converte os movimentos disponíveis em um array de inteiros para facilitar o acesso
        int index = 0;
        for (Integer item : board.getAvailableMoves()) {
            moves[index++] = item;
        }
        // Seleciona aleatoriamente um movimento válido a partir dos movimentos disponíveis
        int randomMove = moves[new java.util.Random().nextInt(moves.length)];
        return randomMove;
    }
}
