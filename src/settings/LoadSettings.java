package settings;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LoadSettings {

    private String configFile = "../config";
    private List<Setting> fileSettings;

    public LoadSettings() {
        this.fileSettings = this.getConfigFromFile();

        //get players sign from file, any issue the default will be set
        this.getMark(1);
        this.getMark(2);
        this.getMark(3);

        //get battlefield size from file, any issue the default will be set
        this.getBattlefieldSize();
    }


    private List<Setting> getConfigFromFile(){
        List<Setting> settings = new ArrayList<>();

        try ( Stream<String> stream = Files.lines( Paths.get( getClass().getResource(this.configFile).getPath() ) ) ) {


            stream.forEach(line->{

                String[] keyValue = line.split(":");

                //System.out.println(keyValue.length);
                if(keyValue.length == 2) {
                    settings.add(new Setting(keyValue[0], keyValue[1]));
                }

            });

            return settings;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean getDuplicatedValidation(String configValue){
        for (String value : Settings.PLAYER_MARKS) {

            if (configValue.equals(value))
                return false;

        }

        return true;
    }

    private void getMark(int playerId){
        String player;

        try {

            switch(playerId){
                case 1:
                    player = "playerOneSymbol";
                    break;

                case 2:
                    player = "playerTwoSymbol";
                    break;

                case 3:
                    player = "playerAiSymbol";
                    break;
                default:
                    player = null;
                    break;
            }


            //looking for setting
            for (Setting setting : this.fileSettings) {

                if (setting.getKey().trim().equals(player)) {

                    if (setting.getValue().length() == 1) {

                        if (this.getDuplicatedValidation(setting.getValue().trim()))
                            Settings.PLAYER_MARKS[playerId - 1] = setting.getValue().trim();
                        else
                            System.out.println("Player" + playerId + " mark cannot be initialised, due Duplicated mark then default has been applied");

                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void getBattlefieldSize(){
        int value;

        try {
            for (Setting setting : this.fileSettings) {

                if (setting.getKey().trim().equals("battlefieldSize")) {

                    if (setting.getValue().length() >= 1) {

                        value = Integer.parseInt(setting.getValue());

                        if(value >= 3 && value <= 10)
                            Settings.BATTLEFIELD_SIZE = value;
                        else
                            throw new NumberFormatException();
                    }


                }
            }
        }catch (NumberFormatException e){
            System.out.println("Please use numbers between 3 and 10, otherwise the default will be used");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
