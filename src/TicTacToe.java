import AI.AI;
import player.Player;
import settings.Settings;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe {

    private String[][] battlefield;
    private List<Player> players;
    private List<Integer> turn = new ArrayList<>();

    public TicTacToe(List<Player> players) {
        this.players = players;

        this.initBattlefield();
        this.getBattlefield();

        //init Turn - the this Id is the first play
        this.turn.add(this.getRandomPlayerId());

        new AI(this.battlefield, this.players.get(2));
    }

    public String[][] getTicTacToeBoard(){
        return this.battlefield;
    }

    /**
     * Return the current player ID (0-2)
     * 0:player one
     * 1:player two
     * 2:Player AI
     *
     * @return player ID
     */
    public int getCurrentPlayerId(){
        return this.turn.get( this.turn.size() - 1 );
    }

    /**
     * Return Current Player Obj
     * @return Player Obj
     */
    public Player getCurrentPlayer(){
        return this.players.get(this.getCurrentPlayerId());
    }

    /**
     * generate a random number between 0 and 2
     * @return random ID
     */
    private int getRandomPlayerId(){
        return new Random().nextInt(3);
    }

    private void initBattlefield(){

        this.battlefield = new String[Settings.BATTLEFIELD_SIZE][Settings.BATTLEFIELD_SIZE];

        for(int x = 0; x < Settings.BATTLEFIELD_SIZE; x++)
            for(int y = 0; y < Settings.BATTLEFIELD_SIZE; y++)
                this.battlefield[x][y] = Settings.EMPTY_POSITION;


    }

    /**
     * Display the Tic Tac Toe field in console
     */
    public void getBattlefield(){
        String line = "   ";
        int cols;

        //cols index
        for(int y = 0; y < Settings.BATTLEFIELD_SIZE; y++) {
            line += " " + y;
            if (y < Settings.BATTLEFIELD_SIZE-1)
                line += " .";
        }

        System.out.println(line);

        int row = 0;

        //display the field
        for (String[] positions : this.battlefield) {
            cols = 0;
            line = row + " .";

            for (String y : positions){
                line += " "+ y +" ";

                if (cols < Settings.BATTLEFIELD_SIZE - 1)
                    line += "|";

                cols++;
            }

            row++;
            System.out.println(line);

            if(row < Settings.BATTLEFIELD_SIZE) {
                line = "   ";
                for(int x = 0; x < Settings.BATTLEFIELD_SIZE-1; x++){
                    line += "---+";
                }
                line += "---";
                System.out.println(line);
            }

        }
    }

    /**
     * Create a New Turn and get who is going to play
     */
    public void setNextTurn(){
        int currentPlayer = this.getCurrentPlayerId();

        currentPlayer = currentPlayer < 2 ? currentPlayer + 1 : 0;

        this.turn.add(currentPlayer);
    }

    /**
     * signs in tic tac toe, based in x and y coordinators
     * @param x is column
     * @param y is row
     */
    public boolean setMove(int x, int y){
        if(this.battlefield[x][y].equals(Settings.EMPTY_POSITION)) {

            this.battlefield[x][y] = this.players.get(this.getCurrentPlayerId()).getMark();
            this.getBattlefield();
            return true;
        }else {
            System.out.println("This coordinate has already been set, try again");
            return false;
        }
    }

    /**
     * check if this game has a winner
     * @return boolean - it has a winner or not
     */
    public boolean getWinner(){

        int count;


        //Row
            for(int x = 0; x < Settings.BATTLEFIELD_SIZE; x++) {
                count = 0;

                for (int y = 0; y < Settings.BATTLEFIELD_SIZE; y++) {
                    if (this.battlefield[x][y].equals(this.getCurrentPlayer().getMark())) {
                        count++;
                    }
                }
                //report a winner
                if(count == Settings.BATTLEFIELD_SIZE)
                    return true;
            }

        //Column
            for(int y = 0; y < Settings.BATTLEFIELD_SIZE; y++){
                count = 0;
                for(int x = 0; x < Settings.BATTLEFIELD_SIZE; x++){
                    if (this.battlefield[x][y].equals(this.getCurrentPlayer().getMark())) {
                        count++;
                    }
                }
                //report a winner
                if(count == Settings.BATTLEFIELD_SIZE)
                    return true;
            }

        //Cross
            count = 0;
            for(int x = 0; x < Settings.BATTLEFIELD_SIZE; x++){
                if (this.battlefield[x][x].equals(this.getCurrentPlayer().getMark())) {
                    count++;
                }
            }
            //report a winner
            if(count == Settings.BATTLEFIELD_SIZE)
                return true;

        //Inverted Cross
        count = 0;
        int y = Settings.BATTLEFIELD_SIZE - 1;
            for(int x = 0; x < Settings.BATTLEFIELD_SIZE; x++){
                if (this.battlefield[x][y-x].equals(this.getCurrentPlayer().getMark())) {
                    count++;
                }
            }
            //report a winner
            if(count == Settings.BATTLEFIELD_SIZE)
                return true;



        //Draw
        count = 0;
        for(int x = 0; x < Settings.BATTLEFIELD_SIZE; x++){
            for(y = 0; y < Settings.BATTLEFIELD_SIZE; y++){
                if(!this.battlefield[x][y].equals(Settings.EMPTY_POSITION))
                    count++;
            }
        }

        //report Draw and exit
        if(count == Settings.BATTLEFIELD_SIZE * Settings.BATTLEFIELD_SIZE){
            System.out.println(String.format("\nThere are no winners, the game ended in a draw. :/\n"));
            System.exit(0);
            return false;
        }

        return false;
    }
}
