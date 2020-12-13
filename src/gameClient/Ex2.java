package gameClient;
import GUI.MyFrame;
import Server.Game_Server_Ex2;
import api.*;

public class Ex2 implements Runnable{

    public static game_service _game;
    static Arena _ar;
    static MyFrame _gui;
    private static long id;
    private static int scenario_num;

    public static void main(String[] args) {
//        id = 203249073;
//        scenario_num = 23;
        id = Integer.parseInt(args[0]);
        scenario_num = Integer.parseInt(args[1]);
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

        int ind=0, dt=10;
        while(_game.isRunning()) {

                _ar.moveAgents();

            try {
                if(ind%1==0) {
                    _gui.repaint();
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
