package AI;

import player.Player;
import settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * The main goal here is, don't any player win
 */
public class AI {
    private String[][] battlefield;
    private Player ai;
    private List<Coordinate> histCoord = new ArrayList<Coordinate>();

    public AI(String[][] battlefield, Player ai) {
        this.battlefield = battlefield;
        this.ai = ai;
    }




    private boolean getCoordinatesValidation(int x , int y){
        try{
            if(this.battlefield[x][y].equals(Settings.EMPTY_POSITION))
                return true;
            else
                return false;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("please, contact your developer");
        }

        return false;
    }

    private boolean getHistoryValidation(int x, int y){


        if(this.histCoord.size() > 0){
            for(int i = 0; i < this.histCoord.size(); i++){
                if(this.histCoord.get(i).getX() == x && this.histCoord.get(i).getY() == y)
                    return false;
            }
        }

        return true;
    }

    public String getBotMove(){

            for (int x = 0; x < Settings.BATTLEFIELD_SIZE; x++) {
                for (int y = 0; y < Settings.BATTLEFIELD_SIZE; y++) {
                        if ((!this.battlefield[x][y].equals(Settings.EMPTY_POSITION)) && (!this.battlefield[x][y].equals(ai.getMark()))) {
                            if (this.getHistoryValidation(x, y)) {

                                this.histCoord.add(new Coordinate(x, y));
                                return this.getDefensePosition(x, y);
                            }
                        }

                }
            }


        return "0,0";
    }

    public String getDefensePosition(int x, int y){

        int addX = new Random().nextInt(2);
        int addY = new Random().nextInt(2);

        x = (x + addX) < Settings.BATTLEFIELD_SIZE-1 ? (x + 1) : 0;
        y = (y + addY) < Settings.BATTLEFIELD_SIZE-1 ? (y + 1) : 0;

        if(this.getCoordinatesValidation(x, y)){
            return String.format("%1$s,%2$s", x, y);
        }else{
            return this.getDefensePosition(x, y);
        }
    }


}
