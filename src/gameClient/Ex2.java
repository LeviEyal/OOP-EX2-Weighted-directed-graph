package gameClient;
import GUI.MyFrame;
import Server.Game_Server_Ex2;
import api.*;

import static java.lang.Thread.sleep;

public class Ex2 implements Runnable{

    public static game_service _game;
    static Arena _ar;
    static MyFrame _mainFrame;
    public static long id = -1;
    public static int level = 0;
    public static boolean isClicked = true;

    public static void main(String[] args) {
        try {
            id = Integer.parseInt(args[0]);
            level = Integer.parseInt(args[1]);
        }
        catch(Exception e) {
            id = -1;
            level = 0;
            isClicked = false;
        }
        Thread client = new Thread(new Ex2());
        client.start();
    }

    @Override
    public void run() {
        _mainFrame = new MyFrame();
        _mainFrame.showPanel(0);
        _mainFrame.pack();

        while (!isClicked) {
            Thread.onSpinWait();
        }

        _game = Game_Server_Ex2.getServer(level);
        _ar = new Arena(_game);
        _mainFrame.InitGamePanel(_ar);
        _mainFrame.showPanel(1);
//        ent.setVisible(false);
        _mainFrame.pack();

        _game.login(id);
        _game.startGame();
        int dt;
        while(_game.isRunning()) {
                _ar.moveAgents();
                _mainFrame.repaint();
                dt = isCloseToPokemon()? 10 : 130;
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
            for(Pokemon p : _ar.getPokemons()) {
                if (ag.get_curr_edge() == p.get_edge() &&
                        ag.getLocation().distance(p.getLocation()) < 0.001 * ag.getSpeed())
                    return true;
            }
        }
        return false;
    }
}
