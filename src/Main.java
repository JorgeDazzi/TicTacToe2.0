import AI.AI;
import player.Player;
import settings.LoadSettings;
import settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {



    public static void main(String[] args) {

        LoadSettings config = new LoadSettings();
        Console c = new Console();

        //Create Players
        List<Player> players = new ArrayList<>();
            players.add(c.getPlayerName(1, Settings.PLAYER_MARKS[0]));
            players.add(c.getPlayerName(2, Settings.PLAYER_MARKS[1]));
            players.add(new Player(3, "AI",Settings.PLAYER_MARKS[2]));

        //init Game
        TicTacToe ticTacToe = new TicTacToe(players);
            //Init Bot
            AI bot = new AI(ticTacToe.getTicTacToeBoard(), players.get(2));

            //Game UI
            c.initGameSession(ticTacToe, bot);


        System.exit(0);
    }

}
