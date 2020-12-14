package gameClient;
import GUI.MyFrame;
import Server.Game_Server_Ex2;
import api.*;

import static java.lang.Thread.sleep;

public class Ex2 implements Runnable{

    public static game_service _game;
    static Arena _ar;
    static MyFrame _gui;
    private static long id;
    private static int scenario_num;

    public static void main(String[] args) {
        id = Integer.parseInt(args[0]);
        scenario_num = Integer.parseInt(args[1]);
        id = 203249073;
        scenario_num = 17;
        Thread client = new Thread(new Ex2());
        client.start();
    }

    @Override
    public void run() {
        _game = Game_Server_Ex2.getServer(scenario_num);
        _game.login(id);
        _ar = new Arena(_game);
        _gui = new MyFrame(_ar);
        _game.startGame();

        int dt;
        while(_game.isRunning()) {
                _ar.moveAgents();
                _gui.repaint();
                dt = isCloseToPokemon()? 50 : 110;
            try {
                sleep(dt);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isCloseToPokemon() {
        for(Agent ag : _ar.JsonToAgents()){
            for(Pokemon p : _ar.getPokemons())
                if(ag.get_curr_edge() == p.get_edge())
                    return true;
        }
        return false;
    }


}
