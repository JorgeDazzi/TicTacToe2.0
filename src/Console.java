import AI.AI;
import player.Player;
import settings.Settings;

import java.util.Random;
import java.util.Scanner;


public class Console {

    /**
     * Player name Console, this will wait for player name and then return a Obj Player
     * @param playerId
     * @param sign
     * @return Player
     */
    public Player getPlayerName(int playerId, String sign){
        while(true){

            //System.out.println("\n");
            System.out.println("( Min 2 characters )");
            System.out.println("What`s player 1 name?");

            Scanner sc = new Scanner(System.in);
            String playerName = sc.nextLine();


            if(playerName.trim().length() >= 2) {
                System.out.println(String.format("Player %1$s %2$s has been set", playerId, playerName));
                System.out.println("\n");
                return new Player(playerId, playerName.trim(), sign);
            }else
                System.out.println("Please, the player name should be bigger than 1 character");
        }
    }

    private boolean getMoveValidation(String command){
            int max = Settings.BATTLEFIELD_SIZE - 1;
            return command.matches(String.format("[0-%1$s],[0-%1$s]",max));
    }

    private void annouceWinner(Player player){
        System.out.println(String.format("%1$s - Player %2$s wins!!!\nThank you :)", player.getName(), player.getId()));
        System.exit(0);
    }


    private String getPlayerMove(Player currentPlayer){
        System.out.println("\n");
        System.out.println("(Move instruction: Nº,Nº (Nº between 0 and "+(Settings.BATTLEFIELD_SIZE-1) +") ... ex 0,1)");
        System.out.println(String.format(
                "%1$s - Player Nº %2$s :: this is your turn, please make your move...",
                currentPlayer.getName(),
                currentPlayer.getId()
        ));

        Scanner sc = new Scanner(System.in);
        String moveCommand = sc.nextLine();

        return moveCommand;
    }
    /**
     * Main Console - Manage features >> game moves, check winner and turn
     * @param ticTacToe
     */
    public void initGameSession(TicTacToe ticTacToe, AI bot){
        Player currentPlayer;
        String moveCommand;

        while (true) {
            currentPlayer = ticTacToe.getCurrentPlayer();

            //console - player move
            if(currentPlayer.getId() != 3)
                 moveCommand = this.getPlayerMove(currentPlayer);
            else {

                moveCommand = bot.getBotMove();
                System.out.println("\n AI moves = "+moveCommand+"\n");
            }


            //Validate move commmand, if it is true, the field will be updated and the next turn set
            if(this.getMoveValidation(moveCommand)){

                //Move
                String[] axes = moveCommand.split(",");

                //try to set your sign : if these coordinates has already been set, the current has to play again because this shot was invalid
                if(ticTacToe.setMove(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]))) {

                    //Check and Report a winner
                    if (ticTacToe.getWinner())
                        this.annouceWinner(ticTacToe.getCurrentPlayer());

                    //New Turn
                    ticTacToe.setNextTurn();
                }


            }else
                 System.out.println(String.format("%1$s << this move command is not allowed!", moveCommand));



        }

    }
}