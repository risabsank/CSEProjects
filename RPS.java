/*
 * Name: Risab Sankar
 * Email: rsankar@ucsd.edu
 * PID: A17383972
 * Sources used: None
 * 
 * This file extends the RPSAbstract. It also is the primary file 
 * that is used to run the game with the user and determine the results 
 * of the game.
 */

import java.util.Scanner;

/**
 * This class runs the primary game functions that the user plays with the
 * CPU.
 */
public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED = "Game not yet implemented.";

    /**
     * Construct a new instance of RPS with the given possible moves.
     * 
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * determines the winner based on the moves of player and cpu
     * 
     * @param playerMove move of the player
     * @param cpuMove    move of CPU
     * @return outcome based on result of the game
     */
    public int determineWinner(String playerMove, String cpuMove) {
        // checks to see if move is valid
        if (isValidMove(playerMove) == false ||
                isValidMove(cpuMove) == false) {
            return INVALID_INPUT_OUTCOME;
        } else if (playerMove.equals(cpuMove)) {
            numTies++;
            return TIE_OUTCOME;
        } else {
            // finds the index of each player's move in the possibleMoves
            // array, calculates difference, and identifies
            int indexPlayerMove = -1;
            int indexCPUMove = -1;
            for (int i = 0; i < possibleMoves.length; i++) {
                if (playerMove.equals(possibleMoves[i])) {
                    indexPlayerMove = i;
                } else if (cpuMove.equals(possibleMoves[i])) {
                    indexCPUMove = i;
                }
            }
            int diffInIndex = indexPlayerMove - indexCPUMove;
            if (diffInIndex == -1
                    || diffInIndex == possibleMoves.length - 1) {
                numPlayerWins++;
                return PLAYER_WIN_OUTCOME;
            } else if (diffInIndex == 1
                    || diffInIndex == (possibleMoves.length - 1) * -1) {
                numCPUWins++;
                return CPU_WIN_OUTCOME;
            } else {
                numTies++;
                return TIE_OUTCOME;
            }
        }
    }

    /**
     * runs the primary game and gets input from the user
     * 
     * @param args used to input other possible moves
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        String playerMove = "";
        while (!(playerMove.equals("q"))) {
            System.out.println(PROMPT_MOVE);
            playerMove = in.next();
            String cpuMove = "";
            // checks to see if valid move, then runs the game
            if (game.isValidMove(playerMove)) {
                cpuMove = game.genCPUMove();
                String cpuPrint = String.format(CPU_MOVE, cpuMove);
                System.out.print(cpuPrint);
                game.playRPS(playerMove, cpuMove);
            } else if (playerMove.equals("q")) {
                break;
            } else {
                System.out.println(INVALID_INPUT);
            }
        }
        game.displayStats();
        in.close();
    }
}
