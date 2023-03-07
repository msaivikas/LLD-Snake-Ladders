import Model.Board;
import Model.Player;
import Services.Dice;

import java.util.Deque;
import java.util.LinkedList;
public class Game {
    Board board;
    Dice dice;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;
    public Game(){
        initializeGame();
    }
    private void initializeGame(){
        board = new Board(10,5,3);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers(){
        Player player1 = new Player("Alice", 0);
        Player player2 = new Player("Bob", 0);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame(){
        while(winner == null){
            Player currentPlayer = findPlayerTurn();
            System.out.println("Current player to roll dice : " + currentPlayer.getId() + "; Current position : "+currentPlayer.getCurrentPosition());
            int diceCount = dice.rollDice();
            int newPosition = currentPlayer.getCurrentPosition()+diceCount;

            playerJump(currentPlayer.getCurrentPosition(), newPosition, currentPlayer);
            currentPlayer.setCurrentPosition(newPosition);
            if(newPosition>=board.getWinningPosition()){
                winner = currentPlayer;
                System.out.println("Hooray!!! " + currentPlayer.getId() + " won ");
            }

        }


    }
    private Player findPlayerTurn(){
        Player currentPlayer = playersList.removeFirst();
        playersList.addLast(currentPlayer);
        return currentPlayer;
    }
    private void playerJump(int oldPosition, int newPosition, Player currentPlayer){
        if(oldPosition>newPosition) System.out.println("Oops! Snake has eaten you.");
        else if(newPosition>oldPosition) System.out.println("Woah! You climbed a ladder. ");
        System.out.println(currentPlayer.getId()+" moved to "+newPosition);
    }
}
