package gameClient;
import Server.Game_Server_Ex2;
import api.*;
import com.google.gson.Gson;

import gameClient.util.Point3D;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Ex2 implements Runnable{

    private static game_service _game;
    private static directed_weighted_graph _graph;
    private static dw_graph_algorithms _ga;
    private static GameGUI _gui;
    private static ArrayList<Agent> _agents = new ArrayList<>();
    private static ArrayList<Pokemon> _pokemons = new ArrayList<>();

    private static String _gameServiceJSON;
    private static String _pokemonsJSON;
    private static String _agentsJSON;
    private static String _graphJSON;

    public static void main(String[] args) {
        Thread client = new Thread(new Ex2());
        client.start();
    }

    @Override
    public void run() {
        int id = 203249073;
        int scenario_num = 23;
        _game = Game_Server_Ex2.getServer(scenario_num);
        _game.login(id);
        _ga = new DWGraph_Algo();

        _gameServiceJSON = _game.toString();
        _pokemonsJSON = _game.getPokemons();
        _graphJSON = _game.getGraph();


        exportJsonToFile("graph1",_game.getGraph());
        _ga.load("jsonsFiles/graph1.json");
        _graph = _ga.getGraph();

        importPokemonsFromJSON();
        importAgentsFromJSON();
        _agentsJSON = _game.getAgents();
//        placeAgents();
        drawGraph();
        System.out.println(_agents);
        System.out.println("_agentsJSON = " + _agentsJSON);
        exportJsonToFile("agents",_agentsJSON);
        System.out.println(_pokemons);
        _game.startGame();
    }

    private void importAgentsFromJSON() {
        try{
            JSONObject object = new JSONObject(_gameServiceJSON);
            JSONObject gameserver = object.getJSONObject("GameServer");
            int numberOfAgents = gameserver.getInt("agents");
            for (int i = 0; i < numberOfAgents; i++) {
                Agent agent;
                int startNode;
                try{
                    startNode =_pokemons.get(i).get_edge().getDest();
                }catch(Exception e){
                    startNode = 0;
                }
                agent = new Agent(_graph, startNode);
                _agents.add(agent);
                _game.addAgent(startNode);
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        }

    }

    private void importPokemonsFromJSON() {
        try {
            JSONObject ttt = new JSONObject(_pokemonsJSON);
            JSONArray ags = ttt.getJSONArray("Pokemons");
            for(int i=0;i<ags.length();i++) {
                JSONObject pp = ags.getJSONObject(i);
                JSONObject pk = pp.getJSONObject("Pokemon");
                int t = pk.getInt("type");
                double v = pk.getDouble("value");
                String p = pk.getString("pos");
                Pokemon f = new Pokemon(new Point3D(p), t, v, 0, null);
                _pokemons.add(f);
            }
        }
        catch (JSONException e) {
            System.out.println(e.toString());
        }

        _pokemons.sort(new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                if(o1.getValue() > o2.getValue())
                    return 1;
                else if(o1.getValue() < o2.getValue())
                    return -1;
                else return 0;
            }
        });
    }

    private void drawGraph() {

    }

    private void exportJsonToFile(String path, String Json) {
        try {
            File file = new File("jsonsFiles/" + path + ".json");
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(Json);
            myWriter.close();
        }catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
