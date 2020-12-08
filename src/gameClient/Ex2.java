package gameClient;
import GUI.MyFrame;
import Server.Game_Server_Ex2;
import api.*;
import java.util.List;

public class Ex2 implements Runnable{

    public static game_service _game;
    static Arena _ar;
    static MyFrame _gui;

    public static void main(String[] args) {
        Thread client = new Thread(new Ex2());
        client.start();
    }

    @Override
    public void run() {
        int id = 203249073;
        int scenario_num = 10;

        _game = Game_Server_Ex2.getServer(scenario_num);
        _game.login(id);

        _ar = new Arena(_game);
        _gui = new MyFrame(_ar);

        _game.startGame();
        System.out.println(_game.timeToEnd());
        int ind=0, dt=100;
        while(_game.isRunning()) {

//            _ar.moveAgents();

            try {
                if(ind%1==0) {
                    _gui.repaint();
                }
                if(ind%10 == 0){
                    System.out.println(_game.timeToEnd()/1000);
                }
                Thread.sleep(dt);
                ind++;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


}
